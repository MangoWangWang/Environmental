package com.mango.know.utils

import java.util.*

object TimeUtils {
    val dateSx: String
        get() {
            val cal = Calendar.getInstance()
            val hour = cal.get(Calendar.HOUR_OF_DAY)
            return if (hour >= 6 && hour < 8) {
                "早上好"
            } else if (hour >= 8 && hour < 11) {
                "上午好"
            } else if (hour >= 11 && hour < 13) {
                "中午好"
            } else if (hour >= 13 && hour < 18) {
                "下午好"
            } else {
                "晚上好"
            }
        }
}
