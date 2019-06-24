package com.mango.know.ui.activity.web

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.KeyEvent
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.LinearLayout
import com.mango.know.R
import com.mango.know.app.App
import com.mango.know.base.BaseActivity
import com.mango.know.constant.Constant
import com.mango.know.constant.Constant.KEY_WEBURL
import com.mango.know.ext.loge
import com.just.agentweb.AgentWeb
import kotlinx.android.synthetic.main.activity_web.*
import kotlinx.android.synthetic.main.title_right_layout.*
import android.graphics.Color
import com.google.gson.Gson
import com.just.agentweb.PermissionInterceptor
import com.mango.know.base.IView
import com.mango.know.ext.showToast
import com.mango.know.utils.DialogUtil


class WebViewActivity : BaseActivity(), IView {

    private val mDialog by lazy {
        DialogUtil.getWaitDialog(this, "上传中")
    }

    override fun showLoading() {
        mDialog.show()
    }

    override fun hideLoading() {
        mDialog.dismiss()
    }

    override fun showDefaultMsg(msg: String) {
        showToast(msg)
    }

    override fun showMsg(msg: String) {
        // 上传图片后的返回地址
        mAgentWeb.jsAccessEntrace.quickCallJs("setData", msg)
    }

    override fun showError(errorMsg: String) {
        showToast(errorMsg)
    }


    private var actionWechatPay: Boolean = false
    private lateinit var mAgentWeb: AgentWeb
    /**
     * 用于方便打印测试
     */
    private val mGson = Gson()
    private val jsInterfaceHandler = JsInterfaceHandler(this)
    private val mWebViewClient = object : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
            return super.shouldOverrideUrlLoading(view, request)
        }

    }
    private val mWebChromeClient = object : WebChromeClient() {
        override fun onProgressChanged(view: WebView, newProgress: Int) {
            //do you work
            Log.i("Info", "onProgress:$newProgress")
        }

        override fun onReceivedTitle(view: WebView, title: String) {
            super.onReceivedTitle(view, title)
            if (tv_title != null) {
                tv_title.text = title
            }
        }
    }


    private var mPermissionInterceptor: PermissionInterceptor =
        PermissionInterceptor { url, permissions, action ->
            /**
             * PermissionInterceptor 能达到 url1 允许授权， url2 拒绝授权的效果。
             * @param url
             * @param permissions
             * @param action
             * @return true 该Url对应页面请求权限进行拦截 ，false 表示不拦截。
             */
            Log.i("mango", "mUrl:" + url + "  permission:" + mGson.toJson(permissions) + " action:" + action)
            false
        }


    companion object {
        /**
         * 不带参数的地址
         *
         * @param context
         * @param url
         */
        fun startActivity(context: Context?, url: String) {
            if (context == null) return
            val intent = Intent(context, WebViewActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.putExtra(Constant.KEY_WEBURL, url)
            context.startActivity(intent)
        }
    }


    override fun attachLayoutRes(): Int {
        return R.layout.activity_web
    }

    override fun initData() {

    }

    override fun initView() {
        val p = System.currentTimeMillis()
        mAgentWeb = AgentWeb.with(this)
            .setAgentWebParent(container, LinearLayout.LayoutParams(-1, -1))
            .useDefaultIndicator()
            .setWebChromeClient(mWebChromeClient)
            .setWebViewClient(mWebViewClient)
            .setPermissionInterceptor(mPermissionInterceptor) //权限拦截 2.0.0 加入。
            .setSecurityType(AgentWeb.SecurityType.STRICT_CHECK)
            .createAgentWeb()
            .ready()
            .go(getUrl())
        mAgentWeb.jsInterfaceHolder.addJavaObject("app", JavaScriptInterface(jsInterfaceHandler))
        val frameLayout = mAgentWeb.webCreator.webParentLayout
        frameLayout.setBackgroundColor(Color.BLACK)
    }


    fun getUrl(): String {
        var url = intent.getStringExtra(Constant.KEY_WEBURL)
        if (!url.contains("http")) {
            url = App.get_do_main() + intent.getStringExtra(KEY_WEBURL)
        }
        url = if (url.contains("?")) {
            "$url&key=$token&isBack=true"
        } else {
            "$url?key=$token&isBack=true"
        }
        loge("mango", "网页请求地址：$url")
        return url
    }


    override fun start() {

    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {

        return if (mAgentWeb.handleKeyEvent(keyCode, event)) {
            true
        } else super.onKeyDown(keyCode, event)
    }

    override fun onPause() {
        mAgentWeb.webLifeCycle.onPause()
        super.onPause()

    }

    override fun onResume() {
        mAgentWeb.webLifeCycle.onResume()
        super.onResume()
    }


    override fun onDestroy() {
        super.onDestroy()
        //mAgentWeb.destroy();
        mAgentWeb.webLifeCycle.onDestroy()
    }


}
