package com.mango.know.utils

object DoubleClickExit {
    var mLastClick = 0L
    private val THRESHOLD = 2000

    fun check(): Boolean {
        val now = System.currentTimeMillis()
        val b = now - mLastClick < THRESHOLD
        mLastClick = now
        return b
    }
}
