package com.mango.know.utils

import android.text.TextUtils


/**
 * @author Milk (QQ: 249828165) 字符串处理类
 */
object StringUtil {


    /**
     * 讲电话号码变为******
     *
     * @return phone
     */
    fun PhoneToPass(phone: String): String {
        var phone = phone
        if (!TextUtils.isEmpty(phone)) {
            phone = phone.replace("(\\d{3})\\d{4}(\\d{4})".toRegex(), "$1****$2")// 正则表达式
        }
        return phone
    }


    /**
     * 保留double类型小数后两位，不四舍五入，直接取小数后两位 比如：10.1269 返回：10.12
     *
     * @param doubleValue
     * @return
     */
    fun calculateProfit(doubleValue: Double): String {
        // 保留4位小数
        val df = java.text.DecimalFormat("#.0000")
        var result = df.format(doubleValue)

        // 截取第一位
        val index = result.substring(0, 1)

        if ("." == index) {
            result = "0$result"
        }

        // 获取小数 . 号第一次出现的位置
        val inde = firstIndexOf(result, ".")

        // 字符串截断
        return result.substring(0, inde + 3)

    }


    /**
     * 查找字符串pattern在str中第一次出现的位置
     *
     * @param str
     * @param pattern
     * @return
     */
    private fun firstIndexOf(str: String, pattern: String): Int {
        for (i in 0 until str.length - pattern.length) {
            var j = 0
            while (j < pattern.length) {
                if (str[i + j] != pattern[j])
                    break
                j++
            }
            if (j == pattern.length)
                return i
        }
        return -1
    }

     fun formatDouble(value: Double): String {
        return if (value >= 10000) {
            if (value >= 100000000) {
                val n = value / 100000000
                StringUtil.calculateProfit(n) + " 亿"
            } else {
                val n = value / 10000
                StringUtil.calculateProfit(n) + "万"
            }
        } else {
            StringUtil.calculateProfit(value)
        }
    }
}
