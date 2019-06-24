package com.mango.know.help

import android.app.Activity
import android.content.Context
import android.support.v7.app.AlertDialog
import android.text.TextUtils
import com.mango.know.R
import com.mango.know.ext.showToast
import com.yanzhenjie.permission.AndPermission
import com.yanzhenjie.permission.Permission


class PermissionHelper {

    interface PermissionCallBack {
        fun onSuccess()
        fun onFail()
    }

    /**
     * 申请权限
     * @param context context 一定是activity
     * @param permissionsParams
     */
    fun getPermission(context: Context, callBack: PermissionCallBack, permissionsParams: String) {
        AndPermission.with(context)
            .runtime()
            .permission(permissionsParams)
            .onGranted { permissions -> callBack.onSuccess() }
            .onDenied { permissions ->
                callBack.onFail()
                context.showToast("权限申请失败")
                if (AndPermission.hasAlwaysDeniedPermission(context, permissions)) {
                    showSettingDialog(context, permissions)
                }
            }.start()
    }

    /**
     * 申请权限组
     * @param context context 一定是activity
     * @param groups
     */
    fun getPermission(context: Activity, callBack: PermissionCallBack, vararg groups: Array<String>) {
        AndPermission.with(context)
            .runtime()
            .permission(*groups)
            .onGranted { permissions -> callBack.onSuccess() }
            .onDenied { permissions ->
                callBack.onFail()
                context.showToast("权限申请失败")
                if (AndPermission.hasAlwaysDeniedPermission(context, permissions)) {
                    showSettingDialog(context, permissions)
                }
            }.start()
    }

    /**
     * //     * Display setting dialog.
     * //     //用户始终禁止权限 弹出设置提示框
     * //     <string name="message_permission_always_failed">请到应用设置同意权限申请:\n\n%1$s</string>
     * //      */
    private fun showSettingDialog(context: Context, permissions: List<String>) {
        val permissionNames = Permission.transformText(context, permissions)
        val message =
            context.getString(R.string.message_permission_always_failed, TextUtils.join("\n", permissionNames))
        AlertDialog.Builder(context)
            .setCancelable(false)
            .setTitle("提示")
            .setMessage(message)
            .setPositiveButton(R.string.setting) { dialog, which -> setPermission(context) }
            .setNegativeButton(R.string.cancel) { dialog, which -> }
            .show()
    }

    /**
     * Set permissions.
     */
    private fun setPermission(context: Context) {
        AndPermission.with(context)
            .runtime()
            .setting()
            .start(100)
    }

    companion object {
        internal var helper: PermissionHelper? = null
        val instance: PermissionHelper
            get() {
                if (helper == null) {
                    helper = PermissionHelper()
                }
                return helper as PermissionHelper
            }
    }

}
