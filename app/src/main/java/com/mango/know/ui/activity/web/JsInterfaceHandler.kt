package com.mango.know.ui.activity.web

import android.app.Activity
import android.content.Intent
import android.os.Handler
import android.os.Message
import com.mango.know.constant.Constant

import java.lang.ref.WeakReference
import com.mango.know.constant.Constant.BACK
import com.mango.know.constant.Constant.LOGIN
import com.mango.know.constant.Constant.PAY
import com.mango.know.ui.activity.login.LoginActivity
import com.mango.know.utils.Preference

class JsInterfaceHandler(private val context: WebViewActivity) : Handler() {
    private val weakRefActivity: WeakReference<Activity> = WeakReference(context)
    /**
     * token
     */
    var token: String by Preference(Constant.TOKEN_KEY, "")

    override fun handleMessage(msg: Message) {
        val activity = weakRefActivity.get()
        if (activity != null) {
            when (msg.what) {
                BACK -> activity.finish()
                PAY -> {
                    val payInfo = msg.obj as String
                    val split = payInfo.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                    val payType = split[0]  //
                    val paySn = split[1]
                }
                LOGIN -> {
                    val intent = Intent(context, LoginActivity::class.java)
                    activity.startActivity(intent)
                }

            }
        }
    }
}

