package com.mango.know.base

import android.content.Intent
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.billy.android.loading.Gloading
import com.mango.know.constant.Constant
import com.mango.know.event.NetworkChangeEvent
import com.mango.know.utils.Preference
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * Created by chenxz on 2018/4/21.
 */
abstract class BaseFragment : Fragment() {

    /**
     * check login
     */
    protected var isLogin: Boolean by Preference(Constant.LOGIN_KEY, false)


    /**
     * token
     */
    var token: String by Preference(Constant.TOKEN_KEY, "")

    /**
     * 缓存上一次的网络状态
     */
    protected var hasNetwork: Boolean by Preference(Constant.HAS_NETWORK_KEY, true)

    /**
     * 视图是否加载完毕
     */
    private var isViewPrepare = false
    /**
     * 数据是否加载过了
     */
    private var hasLoadData = false

//    /**
//     * 多种状态的 View 的切换
//     */
//    protected var mLayoutStatusView: MultipleStatusView? = null

    /**
     * 多种状态的 View 的切换
     */
    var holder: Gloading.Holder? = null

    /**
     * 加载布局
     */
    @LayoutRes
    abstract fun attachLayoutRes(): Int

    /**
     * 初始化 View
     */
    abstract fun initView(view: View)

    /**
     * 懒加载
     */
    abstract fun lazyLoad()

    /**
     * 是否使用 EventBus
     */
    open fun useEventBus(): Boolean = true

    /**
     * 无网状态—>有网状态 的自动重连操作，子类可重写该方法
     */
    open fun doReConnected() {
        lazyLoad()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val views = inflater?.inflate(attachLayoutRes(), null)
        val holder = Gloading.getDefault().wrap(views)
        this.holder = holder
        return holder.wrapper
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            lazyLoadDataIfPrepared()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (useEventBus()) {
            EventBus.getDefault().register(this)
        }
        isViewPrepare = true
        initView(view)
        lazyLoadDataIfPrepared()
    }

    private fun lazyLoadDataIfPrepared() {
        if (userVisibleHint && isViewPrepare && !hasLoadData) {
            lazyLoad()
            hasLoadData = true
        }
    }

    open val mRetryClickListener: View.OnClickListener = View.OnClickListener {
        lazyLoad()
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onNetworkChangeEvent(event: NetworkChangeEvent) {
        if (event.isConnected) {
            doReConnected()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (useEventBus()) {
            EventBus.getDefault().unregister(this)
        }
//        activity?.let { App.getRefWatcher(it)?.watch(activity) }
    }


    fun startActivity(clz: Class<*>) {
        startActivity(Intent(context, clz))
    }

}