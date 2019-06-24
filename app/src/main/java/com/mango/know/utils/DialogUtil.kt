package com.mango.know.utils

import android.app.DatePickerDialog
import android.app.Dialog
import android.app.ProgressDialog
import android.content.Context
import android.content.DialogInterface
import android.support.v7.app.AlertDialog
import android.text.TextUtils
import java.util.*


/**
 * Created by chenxz on 2018/6/9.
 * 对话框辅助类,需要自己调用show方法
 */
object DialogUtil {


    fun showHitDialog(context: Context, title: String, content: String, impl: DialogImpl?): Dialog {
        val builder = android.support.v7.app.AlertDialog.Builder(context)
        builder.setTitle(title)
        builder.setMessage(content)
        //        builder.setNegativeButton("取消", null);
        builder.setPositiveButton("确定") { dialog, which -> impl?.onOk() }
        return builder.show()
    }

    fun showDefaultDialog(
        context: Context,
        title: String,
        content: String,
        ok: String,
        cancel: String,
        listener: DialogImpl
    ) {
        val builder = android.support.v7.app.AlertDialog.Builder(context)
        builder.setTitle(title)
        builder.setMessage(content)
        builder.setNegativeButton(cancel) { _, _ -> listener.onCancel() }
        builder.setPositiveButton(ok) { _, _ -> listener.onOk() }
        builder.show()
    }


    /**
     * 底部菜单选择
     *
     * @param context
     * @param menu1_str
     * @param menu2_str
     * @param impl
     */
//    fun showBottomMenuDialog(
//        context: Context,
//        title: String,
//        menu1_str: String,
//        menu2_str: String,
//        impl: BottomMenuDialogImpl
//    ) {
//        val mBottomSheetDialog = Dialog(context, R.style.MaterialDialogSheet)
//        val view = View.inflate(context, R.layout.dialog_bottom_tow_menu_sheet, null)
//        val title_tv = view.findViewById<TextView>(R.id.title_tv)
//        title_tv.text = title
//        val menu1 = view.findViewById<TextView>(R.id.menu1)
//        val menu2 = view.findViewById<TextView>(R.id.menu2)
//        menu1.text = menu1_str
//        menu2.text = menu2_str
//        val cancel = view.findViewById<TextView>(R.id.cancel)
//
//        menu1.setOnClickListener { v ->
//            mBottomSheetDialog.dismiss()
//            impl.onMenu1()
//        }
//        menu2.setOnClickListener { v ->
//            mBottomSheetDialog.dismiss()
//            impl.onMenu2()
//        }
//
//        cancel.setOnClickListener { v -> mBottomSheetDialog.dismiss() }
//
//        mBottomSheetDialog.setContentView(view)
//        mBottomSheetDialog.setCancelable(true)
//        mBottomSheetDialog.window!!.setLayout(
//            LinearLayout.LayoutParams.MATCH_PARENT,
//            LinearLayout.LayoutParams.WRAP_CONTENT
//        )
//        mBottomSheetDialog.window!!.setGravity(Gravity.BOTTOM)
//        mBottomSheetDialog.show()
//    }


    /**
     * 获取一个Dialog
     *
     * @param context
     * @return
     */
    fun getDialog(context: Context): AlertDialog.Builder {
        return AlertDialog.Builder(context)
    }

    /**
     * 获取一个耗时的对话框 ProgressDialog
     *
     * @param context
     * @param message
     * @return
     */
    fun getWaitDialog(context: Context, message: String): ProgressDialog {
        val waitDialog = ProgressDialog(context)
        if (!TextUtils.isEmpty(message)) {
            waitDialog.setMessage(message)
        }
        return waitDialog
    }

    /**
     * 获取一个信息对话框,注意需要自己手动调用show方法
     *
     * @param context
     * @param message
     * @param onClickListener
     * @return
     */
    @JvmOverloads
    fun getMessageDialog(context: Context, message: String,
                         onClickListener: DialogInterface.OnClickListener): AlertDialog.Builder {
        val builder = getDialog(context)
        builder.setMessage(message)
        builder.setPositiveButton("确定", onClickListener)
        return builder
    }

    fun getConfirmDialog(context: Context, message: String,
                         onClickListener: DialogInterface.OnClickListener): AlertDialog.Builder {
        val builder = getDialog(context)
        builder.setMessage(message)
        builder.setPositiveButton("确定", onClickListener)
        builder.setNegativeButton("取消", null)
        return builder
    }

    fun getConfirmDialog(context: Context, message: String,
                         onOKClickListener: DialogInterface.OnClickListener, onCancleClickListener: DialogInterface.OnClickListener): AlertDialog.Builder {
        val builder = getDialog(context)
        builder.setMessage(message)
        builder.setPositiveButton("确定", onOKClickListener)
        builder.setNegativeButton("取消", onCancleClickListener)
        return builder
    }

    fun getSelectDialog(context: Context, title: String, arrays: Array<String>,
                        onClickListener: DialogInterface.OnClickListener): AlertDialog.Builder {
        val builder = getDialog(context)
        builder.setItems(arrays, onClickListener)
        if (!TextUtils.isEmpty(title)) {
            builder.setTitle(title)
        }
        builder.setNegativeButton("取消", null)
        return builder
    }

    fun getSelectDialog(context: Context, arrays: Array<String>,
                        onClickListener: DialogInterface.OnClickListener): AlertDialog.Builder {
        return getSelectDialog(context, "", arrays, onClickListener)
    }

    /**
     * 获取一个单选的对话框
     *
     * @param context
     * @param title
     * @param arrays
     * @param selectIndex
     * @param onClickListener
     * @param onOKClickListener
     * 点击确定的回调接口
     * @param onCancleClickListener
     * 点击取消的回调接口
     * @return
     */
    fun getSingleChoiceDialog(context: Context, title: String, arrays: Array<String>,
                              selectIndex: Int, onClickListener: DialogInterface.OnClickListener,
                              onOKClickListener: DialogInterface.OnClickListener, onCancleClickListener: DialogInterface.OnClickListener? = null): AlertDialog.Builder {
        val builder = getDialog(context)
        builder.setSingleChoiceItems(arrays, selectIndex, onClickListener)
        if (!TextUtils.isEmpty(title)) {
            builder.setTitle(title)
        }
        builder.setPositiveButton("确定", onOKClickListener)
        builder.setNegativeButton("取消", onCancleClickListener)
        return builder
    }

    fun getSingleChoiceDialog(context: Context, title: String, arrays: Array<String>,
                              selectIndex: Int, onClickListener: DialogInterface.OnClickListener): AlertDialog.Builder {
        val builder = getDialog(context)
        builder.setSingleChoiceItems(arrays, selectIndex, onClickListener)
        if (!TextUtils.isEmpty(title)) {
            builder.setTitle(title)
        }
        builder.setPositiveButton("取消", null)
        return builder
    }

    fun getSingleChoiceDialog(context: Context, arrays: Array<String>, selectIndex: Int,
                              onClickListener: DialogInterface.OnClickListener, onOKClickListener: DialogInterface.OnClickListener,
                              onCancleClickListener: DialogInterface.OnClickListener): AlertDialog.Builder {
        return getSingleChoiceDialog(context, "", arrays, selectIndex, onClickListener, onOKClickListener,
                onCancleClickListener)
    }

    /**
     * 获取一个多选的对话框
     *
     * @param context
     * @param title
     * @param arrays
     * @param checkedItems
     * @param onMultiChoiceClickListener
     * @param onOKClickListener
     * 点击确定的回调接口
     * @param onCancleListener
     * 点击取消的回调接口
     * @return
     */
    fun getMultiChoiceDialog(context: Context, title: String, arrays: Array<String>,
                             checkedItems: BooleanArray, onMultiChoiceClickListener: DialogInterface.OnMultiChoiceClickListener,
                             onOKClickListener: DialogInterface.OnClickListener, onCancleListener: DialogInterface.OnClickListener): AlertDialog.Builder {
        val builder = getDialog(context)
        builder.setMultiChoiceItems(arrays, checkedItems, onMultiChoiceClickListener)
        if (!TextUtils.isEmpty(title)) {
            builder.setTitle(title)
        }
        builder.setPositiveButton("确定", onOKClickListener)
        builder.setNegativeButton("取消", onCancleListener)
        return builder
    }

    fun showDatePickerDialog(activity: Context, listener: DatePickerDialog.OnDateSetListener, calendar: Calendar) {
        // Calendar 需要这样来得到
        // Calendar calendar = Calendar.getInstance();
        // 直接创建一个DatePickerDialog对话框实例，并将它显示出来
        val datePickerDialog = DatePickerDialog(
            activity,
            // 绑定监听器(How the parent is notified that the date is set.)
            listener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)
        )// 设置初始日期
        val date = Date()//当前时间
        val time = date.time
        val datePicker = datePickerDialog.datePicker
        datePicker.maxDate = time//设置最大日期
        datePickerDialog.show()
    }

    interface DialogImpl {
        fun onOk() {}

        fun onCancel() {}
    }

    interface BottomMenuDialogImpl {
        fun onMenu1()

        fun onMenu2()
    }


}

