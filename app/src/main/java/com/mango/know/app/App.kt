package com.mango.know.app


import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.util.Log
import com.mango.know.BuildConfig
import com.mango.know.base.BaseActivity
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import com.squareup.leakcanary.RefWatcher
import kotlin.properties.Delegates

/**
 * Created by chenxz on 2018/4/21.
 */
class App : Application() {


    private var refWatcher: RefWatcher? = null

    private val activitys = arrayListOf<BaseActivity>()

    companion object {
        private val TAG = "App"

        var context: Context by Delegates.notNull()
            private set

        lateinit var instance: Application

        lateinit var do_main_url: String

        fun getRefWatcher(context: Context): RefWatcher? {
            val app = context.applicationContext as App
            return app.refWatcher
        }

        fun getActivitys(context: Context): List<BaseActivity>? {
            val app = context.applicationContext as App
            return app.activitys
        }

        fun get_do_main(): String? {
            return do_main_url
        }

        fun set_do_main(url: String) {
            do_main_url = url
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        context = applicationContext
//        refWatcher = setupLeakCanary()
        initConfig()
        registerActivityLifecycleCallbacks(mActivityLifecycleCallbacks)
    }



    /**
     * 初始化配置
     */
    private fun initConfig() {
        val formatStrategy = PrettyFormatStrategy.newBuilder()
            .showThreadInfo(true)  // 隐藏线程信息 默认：显示
            .methodCount(0)         // 决定打印多少行（每一行代表一个方法）默认：2
            .methodOffset(7)        // (Optional) Hides internal method calls up to offset. Default 5
            .tag("hao_zz")   // (Optional) Global tag for every log. Default PRETTY_LOGGER
            .build()
        Logger.addLogAdapter(object : AndroidLogAdapter(formatStrategy) {
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return BuildConfig.DEBUG
            }
        })
    }

    private val mActivityLifecycleCallbacks = object : Application.ActivityLifecycleCallbacks {
        override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
            Log.d(TAG, "onCreated: " + activity.componentName.className)
        }

        override fun onActivityStarted(activity: Activity) {
            Log.d(TAG, "onStart: " + activity.componentName.className)
        }

        override fun onActivityResumed(activity: Activity) {

        }

        override fun onActivityPaused(activity: Activity) {

        }

        override fun onActivityStopped(activity: Activity) {

        }

        override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {

        }

        override fun onActivityDestroyed(activity: Activity) {
            Log.d(TAG, "onDestroy: " + activity.componentName.className)
        }
    }

    //全局记录activity
    fun addActivity(context: BaseActivity) {
        activitys.add(context)
    }

    fun removeActivity(context: BaseActivity) {
        activitys.remove(context)
    }

    fun finishAllActivity() {
        var activity: Activity?
        for (i in activitys.indices.reversed()) {
            activity = activitys.get(i)
            if (activity != null && !activity!!.isFinishing) {
                activity!!.finish()
            }
        }
    }

    fun finishTopActivity(number: Int) {
        var activity: Activity?
        for (i in activitys.size - 1 downTo activitys.size - number) {
            activity = activitys.get(i)
            if (activity != null && !activity!!.isFinishing) {
                activity!!.finish()
            }
        }
    }


}