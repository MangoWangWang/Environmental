package com.mango.know.utils

import android.annotation.TargetApi
import android.app.Activity
import android.content.Context
import android.os.Build
import android.view.View
import android.view.inputmethod.InputMethodManager

/**
 * @author MaTianyu(http://litesuits.com) on 2015-06-01
 */
@TargetApi(Build.VERSION_CODES.CUPCAKE)
object InputMethodUtils {

    fun toggleSoftInput(context: Context?) {
        if (context == null) return
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS)
    }

    fun showSoftInput(view: View?): Boolean {
        if (view == null) return false
        val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        return imm.showSoftInput(view, InputMethodManager.SHOW_FORCED)
    }

    fun showSoftInput(activity: Activity?): Boolean {
        if (activity == null) return false
        val view = activity.currentFocus
        if (view != null) {
            val imm = view.context.getSystemService(
                Context.INPUT_METHOD_SERVICE
            ) as InputMethodManager
            return imm.showSoftInput(view, InputMethodManager.SHOW_FORCED)
        }
        return false
    }

    fun hideSoftInput(view: View?): Boolean {
        if (view == null) return false
        val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        return imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun hideSoftInput(activity: Activity?): Boolean {
        if (activity == null) return false
        if (activity.currentFocus != null) {
            val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            return imm.hideSoftInputFromWindow(activity.currentFocus!!.windowToken, 0)
        }
        return false
    }

    fun isActive(context: Context?): Boolean {
        if (context == null) return false
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        return imm.isActive
    }


}
