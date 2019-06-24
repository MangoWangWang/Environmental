package com.mango.know.rx


import com.mango.know.app.App
import com.mango.know.base.IView
import com.mango.know.http.exception.ErrorStatus
import com.mango.know.http.exception.ExceptionHandle
import com.mango.know.bean.BaseBean
import com.mango.know.utils.NetWorkUtil
import io.reactivex.subscribers.ResourceSubscriber

/**
 * Created by chenxz on 2018/6/11.
 */
abstract class BaseSubscriber<T : BaseBean>(view: IView? = null) : ResourceSubscriber<T>() {

    private var mView = view

    abstract fun onSuccess(t: T)

    override fun onComplete() {
        mView?.hideLoading()
    }

    override fun onStart() {
        super.onStart()
        mView?.showLoading()
        if (!NetWorkUtil.isNetworkConnected(App.instance)) {
            mView?.showDefaultMsg("网络连接不可用,请检查网络设置!")
            onComplete()
        }
    }

    override fun onNext(t: T) {
        when {
            t.code == ErrorStatus.SUCCESS -> onSuccess(t)
            t.code == ErrorStatus.TOKEN_INVAILD -> {
                // Token 过期，重新登录
            }
            else -> mView?.showDefaultMsg(t.message)
        }
    }

    override fun onError(t: Throwable) {
        mView?.hideLoading()
        ExceptionHandle.handleException(t)
    }

}