package com.mango.know.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.GregorianCalendar

/**
 * @author Milk (QQ: 249828165)
 * 时间日期处理类
 */
object DateUtil {
    val TAG = "DateUtil"
    val WEEK_DAYS = arrayOf("星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六")
    val DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss"

    val unixTime: Long
        get() = System.currentTimeMillis() / 1000L


    fun startOfDay(time: String): Long {
        return startOfDay(strToTime(time))
    }

    fun startOfDay(time: Long): Long {
        val dCal = Calendar.getInstance()
        dCal.timeInMillis = time
        dCal.set(Calendar.HOUR_OF_DAY, 0)
        dCal.set(Calendar.MINUTE, 0)
        dCal.set(Calendar.SECOND, 0)
        dCal.set(Calendar.MILLISECOND, 0)

        return dCal.timeInMillis
    }

    @JvmOverloads
    fun startOfDay(date: Date = Date()): Long {
        val dCal = Calendar.getInstance()
        dCal.time = date
        dCal.set(Calendar.HOUR_OF_DAY, 0)
        dCal.set(Calendar.MINUTE, 0)
        dCal.set(Calendar.SECOND, 0)
        dCal.set(Calendar.MILLISECOND, 0)

        return dCal.timeInMillis
    }

    fun strToTime_2(ts: String): Long {
        val mSimpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm")
        try {
            return mSimpleDateFormat.parse(ts).time
        } catch (e: Exception) {
            e.printStackTrace()
            return 0
        }

    }

    fun strToTime(ts: String): Long {
        var ts = ts
        var format = ""
        if (ts.length == 21 && ts.endsWith(".0")) { //2012-10-13 10:08:01.0
            format = DEFAULT_FORMAT
            ts = ts.substring(0, ts.length - 2)
        } else if (ts.length == 19) {
            format = DEFAULT_FORMAT
        } else if (ts.length == 10) {
            format = "yyyy-MM-dd"
        } else {
            return 0
        }
        //        else throw new Exception("TimeUtils::strToTime无法转换时间：" + ts);

        val mSimpleDateFormat = SimpleDateFormat(format)
        try {
            return mSimpleDateFormat.parse(ts).time
        } catch (e: Exception) {
            e.printStackTrace()
            return 0
        }

    }


    fun getDateStr(ts: String): String {
        // 处理2012-01-01 01:01:01.0的情况
        return if (ts.endsWith(".0")) {
            ts.substring(0, ts.length - 2)
        } else {
            ts
        }
    }


    @Throws(Exception::class)
    fun getMonday(ts: String): String {
        return if ("" == ts || "0000-00-00" == ts)
            "0000-00-00"
        else
            getMonday(strToTime(ts))
    }

    //日期转时间戳
    fun getTime(timeString: String, format: String): String? {//
        var timeStamp: String? = null
        val sdf = SimpleDateFormat(format)
        val d: Date
        try {
            d = sdf.parse(timeString)
            val l = d.time
            timeStamp = l.toString()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return timeStamp
    }

    //日期转时间戳
    fun getTimeToSeconds(timeString: String, format: String): String? {//
        var timeStamp: String? = null
        val sdf = SimpleDateFormat(format)
        val d: Date
        try {
            d = sdf.parse(timeString)
            val l = d.time
            timeStamp = (l / 1000).toString()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return timeStamp
    }

    /**
     * 获取一周的开始星期一的日期
     *
     * @param ts long
     * @return string Y-m-d
     */
    fun getMonday(ts: Long): String {
        val c = Calendar.getInstance()
        c.timeInMillis = ts
        c.firstDayOfWeek = Calendar.MONDAY
        // Set the calendar to monday of the current week
        c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY)

        // Print dates of the current week starting on Monday
        val df = SimpleDateFormat("yyyy-MM-dd")
        return df.format(c.time)
    }


    /**
     * 获取当前日期是星期几
     *
     * @param dt
     * @return 当前日期是星期几
     */
    fun getWeekOfDate(dt: Long): String {
        val cal = Calendar.getInstance()
        cal.timeInMillis = dt
        return getWeekOfDate(cal)
    }

    fun getWeekOfDate(dt: Date): String {
        val cal = Calendar.getInstance()
        cal.time = dt
        return getWeekOfDate(cal)
    }

    fun getWeekOfDate(cal: Calendar): String {
        var w = cal.get(Calendar.DAY_OF_WEEK) - 1
        if (w < 0) w = 0

        return WEEK_DAYS[w]
    }


    object Constellation {
        val zodiacArr = arrayOf("猴", "鸡", "狗", "猪", "鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊")

        val constellationArr = arrayOf("水瓶", "双鱼", "牡羊", "金牛", "双子", "巨蟹", "狮子", "处女", "天秤", "天蝎", "射手", "魔羯")

        val constellationEdgeDay = intArrayOf(20, 19, 21, 21, 21, 22, 23, 23, 23, 23, 22, 22)

        /**
         * 根据日期获取生肖
         *
         * @return
         */
        fun date2Zodica(time: Calendar): String {
            return zodiacArr[time.get(Calendar.YEAR) % 12]
        }

        /**
         * 根据日期获取星座
         *
         * @param time
         * @return
         */
        fun date2Constellation(time: Calendar): String {
            var month = time.get(Calendar.MONTH)
            val day = time.get(Calendar.DAY_OF_MONTH)
            if (day < constellationEdgeDay[month]) {
                month = month - 1
            }
            return if (month >= 0) {
                constellationArr[month]
            } else constellationArr[11]
            // default to return 魔羯
        }
    }

    fun getTimeAMorPm(time: Long): String {
        val ca = GregorianCalendar()
        ca.time = Date(time)
        return if (ca.get(GregorianCalendar.AM_PM) > 0) {
            "pm"
        } else {
            "am"
        }
    }

    fun getDefaultDateTime(time: Long): String {
        val st = startOfDay()
        if (time >= st && time < st + 24 * 3600 * 1000)
            return "今天"

        val cal = Calendar.getInstance()
        cal.timeInMillis = time
        val sb = StringBuilder()
        sb.append(cal.get(Calendar.MONTH) + 1).append("月")
            .append(cal.get(Calendar.DATE)).append("日 ")
            .append(getWeekOfDate(cal))

        return sb.toString()
    }


    fun timeMachineRecordTime(millisTime: Long): String {
        val intervalSinceNow = abs((System.currentTimeMillis() - millisTime) / 1000)
        val result: String
        if (intervalSinceNow < 60)
            result = "刚刚"
        else if (intervalSinceNow < 3600)
            result = (intervalSinceNow / 60).toString() + "分钟前"
        else if (intervalSinceNow < 86400)
            result = (intervalSinceNow / 3600).toString() + "小时前"
        else if (intervalSinceNow < 604800)
            result = (intervalSinceNow / 86400).toString() + "天前"
        else if (intervalSinceNow < 2592000)
            result = (intervalSinceNow / 604800).toString() + "周前"
        else if (intervalSinceNow < 31536000)
            result = (intervalSinceNow / 2592000).toString() + "个月前"
        else
            result = (intervalSinceNow / 31536000).toString() + "年前"
        return result
    }

    /**
     * 将传进来的秒数格式化为“00:00”的形式
     * @param time    单位毫秒
     * @return
     */
    fun secondToTime(time: Long): String {
        var time = time
        time /= 1000
        var timeStr: String? = null
        var minute: Long = 0
        var second: Long = 0

        if (time <= 0) {
            return "00:00"
        } else {
            minute = time / 60
            second = time % 60
            timeStr = timeFormat(minute) + ":" + timeFormat(second)
        }

        return timeStr
    }

    fun secondToTime_2(time: Long): String {
        var time = time
        time /= 1000
        var timeStr: String? = null
        var minute: Long = 0
        var second: Long = 0
        var hour: Long = 0

        if (time <= 0) {
            return "00:00:00"
        } else {
            hour = time / 3600
            minute = time % 3600 / 60
            second = time % 60
            timeStr = timeFormat(hour) + ":" + timeFormat(minute) + ":" + timeFormat(second)
        }

        return timeStr
    }

    fun secondToTimeArr(time: Long): Array<String?> {
        var time = time
        val timeArr = arrayOfNulls<String>(3)
        time /= 1000
        val timeStr: String? = null
        var minute: Long = 0
        var second: Long = 0
        var hour: Long = 0

        hour = time / 3600
        minute = time % 3600 / 60
        second = time % 60

        if (hour < 10) {
            timeArr[0] = "0$hour"
        } else {
            timeArr[0] = hour.toString() + ""
        }

        if (minute < 10) {
            timeArr[1] = "0$minute"
        } else {
            timeArr[1] = minute.toString() + ""
        }
        if (second < 10) {
            timeArr[2] = "0$second"
        } else {
            timeArr[2] = second.toString() + ""
        }


        return timeArr
    }

    private fun timeFormat(time: Long): String {
        var str: String? = null
        if (time >= 0 && time < 10) {
            str = "0$time"
        } else {
            str = "" + time
        }
        return str
    }

    private fun abs(value: Long): Long {
        var value = value
        if (value < 0) {
            value *= -1
        }
        return value
    }


    fun getTimeTimestamp(dateStr: String): Long {
        var date = Date()
        //注意format的格式要与日期String的格式相匹配
        val sdf = SimpleDateFormat("hh:mm")
        try {
            date = sdf.parse(dateStr)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return date.time
    }

    fun getTimeLeft(endTime: Long): String {
        if (endTime > System.currentTimeMillis()) {
            val timeLeft = endTime - System.currentTimeMillis()
            val dayLeft = timeLeft / 1000 / 60 / 60 / 24
            return "还剩" + dayLeft + "天"
        } else {
            return "已结束"
        }
    }

    fun getTimeMinLeft(endTime: Long): String {
        if (endTime > System.currentTimeMillis()) {
            val timeLeft = endTime - System.currentTimeMillis()
            val dayLeft = timeLeft / 1000 / 60 / 60
            return "还剩" + dayLeft + "天"
        } else {
            return "已结束"
        }
    }

    /**
     * 获取过去第几天的日期
     *
     * @param past
     * @return
     */
    fun getPastDate(past: Int): String {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past)
        val today = calendar.time
        val format = SimpleDateFormat("yyyy-MM-dd")
//        Log.e(null, result);
        return format.format(today)
    }

    /**
     * 获取未来 第 past 天的日期
     * @param past
     * @return
     */
    fun getFetureDate(past: Int): String {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + past)
        val today = calendar.time
        val format = SimpleDateFormat("yyyy-MM-dd")
//        Log.e(null, result);
        return format.format(today)
    }

    /**
     * 获取指定年月的第一天
     * @param year
     * @param month
     * @return
     */
    fun getFirstDayOfMonth1(year: Int, month: Int): String {
        var year = year
        var month = month
        // 边界处理
        if (month < 0) {
            year = year - 1
            month = 11
        }
        val cal = Calendar.getInstance()
        //设置年份
        cal.set(Calendar.YEAR, year)
        //设置月份
        cal.set(Calendar.MONTH, month)
        //获取某月最小天数
        val firstDay = cal.getMinimum(Calendar.DATE)
        //设置日历中月份的最小天数
        cal.set(Calendar.DAY_OF_MONTH, firstDay)
        //格式化日期
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        return sdf.format(cal.time)
    }


    /**
     * 获取指定年月的最后一天
     * @param year
     * @param month
     * @return
     */
    fun getLastDayOfMonth1(year: Int, month: Int): String {
        var year = year
        var month = month
        // 边界处理
        if (month < 0) {
            year = year - 1
            month = 11
        }
        val cal = Calendar.getInstance()
        //设置年份
        cal.set(Calendar.YEAR, year)
        //设置月份
        cal.set(Calendar.MONTH, month)
        //获取某月最大天数
        val lastDay = cal.getActualMaximum(Calendar.DATE)
        //设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, lastDay)
        //格式化日期
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        return sdf.format(cal.time)
    }


    /**
     * 获得指定日期的前几天
     * @param specifiedDay
     * @return
     * @throws Exception
     */
    fun getSpecifiedDayBefore(specifiedDay: String, before: Int): String {
        //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        val c = Calendar.getInstance()
        var date: Date? = null
        try {
            date = SimpleDateFormat("yyyy-MM-dd").parse(specifiedDay)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        c.time = date
        val day = c.get(Calendar.DATE)
        c.set(Calendar.DATE, day - before)

        return SimpleDateFormat("yyyy-MM-dd").format(c.time)
    }

    /**
     * 获得指定日期的前几天
     * @param specifiedDay
     * @return
     * @throws Exception
     */
    fun getSpecifiedDayBeforeFomat(specifiedDay: String, before: Int): String {
        //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        val c = Calendar.getInstance()
        var date: Date? = null
        try {
            date = SimpleDateFormat("yyyy-MM-dd").parse(specifiedDay)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        c.time = date
        val day = c.get(Calendar.DATE)
        c.set(Calendar.DATE, day - before)

        return SimpleDateFormat("MM-dd").format(c.time)
    }

    /**
     * 获得指定日期的后几天
     * @param specifiedDay
     * @return
     */
    fun getSpecifiedDayAfter(specifiedDay: String, after: Int): String {
        val c = Calendar.getInstance()
        var date: Date? = null
        try {
            date = SimpleDateFormat("yyyy-MM-dd").parse(specifiedDay)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        c.time = date
        val day = c.get(Calendar.DATE)
        c.set(Calendar.DATE, day + after)

        return SimpleDateFormat("yyyy-MM-dd").format(c.time)
    }

    /**
     * 比较两个日期的大小，日期格式为yyyy-MM-dd
     *
     * @param str1 the first date
     * @param str2 the second date
     * @return true <br></br>false
     */
    fun isDate2Bigger(str1: String, str2: String): Boolean {
        var isBigger = false
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        var dt1: Date? = null
        var dt2: Date? = null
        try {
            dt1 = sdf.parse(str1)
            dt2 = sdf.parse(str2)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        if (dt1!!.time > dt2!!.time) {
            isBigger = false
        } else if (dt1.time <= dt2.time) {
            isBigger = true
        }
        return isBigger
    }

    /**
     * 获取当前时间
     */
    fun getToday(): String {
        val df = SimpleDateFormat("yyyy-MM-dd")//设置日期格式
        return df.format(Date())// new Date()为获取当前系统时间
    }

    fun getYearMonth(ts: Long): String {
        return date("yyyyMM", ts)
    }

    fun date(format: String, ts: Long): String {
        return stampToDate(format,ts)
    }

    /*
 * 将时间戳转换为时间
 */
    fun stampToDate(format: String,timeMillis: Long): String {
        val simpleDateFormat = SimpleDateFormat(format)
        val date = Date(timeMillis)
        return simpleDateFormat.format(date)
    }

    fun getYear(ts: Long): String {
        return date("yyyy", ts)
    }

    fun getMonth(ts: Long): String {
        return date("MM", ts)
    }


}
