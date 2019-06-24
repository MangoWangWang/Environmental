package com.mango.know.utils


import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText

/**
 * 描述   ：金额输入字体监听类，限制小数点后输入位数
 *
 *
 * 默认限制小数点2位
 * 默认第一位输入小数点时，转换为0.
 * 如果起始位置为0,且第二位跟的不是".",则无法后续输入
 *
 *
 * 作者   ：Created by DuanRui on 2017/9/28.
 */
class MoneyTextWatcher(private val editText: EditText, private val anInterface: CountAccountAndChargeInterface) :
    TextWatcher {
    private var digits = 2

    fun setDigits(d: Int): MoneyTextWatcher {
        digits = d
        return this
    }


    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        var s = s
        //删除“.”后面超过2位后的数据
        if (s.toString().contains(".")) {
            if (s.length - 1 - s.toString().indexOf(".") > digits) {
                s = s.toString().subSequence(
                    0,
                    s.toString().indexOf(".") + digits + 1
                )
                editText.setText(s)
                editText.setSelection(s.length) //光标移到最后
            }
        }
        //如果"."在起始位置,则起始位置自动补0
        if (s.toString().trim { it <= ' ' }.substring(0) == ".") {
            s = "0$s"
            editText.setText(s)
            editText.setSelection(2)
        }

        //如果起始位置为0,且第二位跟的不是".",则无法后续输入
        if (s.toString().startsWith("0") && s.toString().trim { it <= ' ' }.length > 1) {
            if (s.toString().substring(1, 2) != ".") {
                editText.setText(s.subSequence(0, 1))
                editText.setSelection(1)
                return
            }
        }
    }

    override fun afterTextChanged(s: Editable) {
        Log.e("afterTextChanged", "afterTextChanged: $s")
        anInterface.afterTextChang(s.toString())
    }

    interface CountAccountAndChargeInterface {
        fun afterTextChang(s: String)
    }
}


