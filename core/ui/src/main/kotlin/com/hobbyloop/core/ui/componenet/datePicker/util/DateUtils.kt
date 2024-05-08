package com.hobbyloop.core.ui.componenet.datePicker.util

import java.util.Calendar

object DateUtils {


    fun getTimeMiles(year: Int, month: Int, day: Int): Long {
        val calendar = Calendar.getInstance()
        calendar[Calendar.YEAR] = year
        calendar[Calendar.MONTH] = month
        val maxDayCount = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
        calendar[Calendar.DAY_OF_MONTH] = Math.min(day, maxDayCount)
        return calendar.timeInMillis
    }

    fun getCurrentTime(): Long {
        val calendar = Calendar.getInstance()
        calendar[Calendar.HOUR_OF_DAY] = 0
        calendar[Calendar.MINUTE] = 0
        calendar[Calendar.SECOND] = 0
        calendar[Calendar.MILLISECOND] = 0
        return calendar.timeInMillis
    }

    fun getMonthDayCount(timeStamp: Long): Int {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = timeStamp
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
    }

    fun getDay(timeStamp: Long): Int {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = timeStamp
        return calendar[Calendar.DAY_OF_MONTH]
    }

    fun getMonth(timeStamp: Long): Int {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = timeStamp
        return calendar[Calendar.MONTH]
    }

    fun getYear(timeStamp: Long): Int {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = timeStamp
        return calendar[Calendar.YEAR]
    }


    fun getCurrentHour(): Int {
        val calendar = Calendar.getInstance()
        return calendar[Calendar.HOUR_OF_DAY]
    }


    fun getCurrentMinute(): Int {
        val calendar = Calendar.getInstance()
        return calendar[Calendar.MINUTE]
    }
}


fun Date.monthsOfDate(): List<Int> {
    val months = mutableListOf<Int>()
    for (month in 1..12) {
        months.add(month)
    }
    return months
}


fun Date.daysOfDate(): List<Int> {
    val days = mutableListOf<Int>()
    val monthDayCount = DateUtils.getMonthDayCount(this.date)
    for (day in 1..monthDayCount) {
        days.add(day)
    }
    return days
}


fun Date.isFullMonthOfYear(minDate: Date, maxDate: Date): Boolean =
    !((this.year == minDate.year && minDate.month != 11) || (this.year == maxDate.year && maxDate.month != 0))

fun Date.isFullDayOfMonth(minDate: Date, maxDate: Date): Boolean {
    val monthDayCount = DateUtils.getMonthDayCount(this.date)
    val isMin =
        (this.year == minDate.year && this.month == minDate.month && minDate.day != 1)
    val isMax =
        (this.year == maxDate.year && this.month == maxDate.month && maxDate.day != monthDayCount)
    return !(isMin || isMax)
}

fun Date.withYear(year: Int): Date {
    return Date(year, this.month, this.day)
}

fun Date.withMonth(month: Int): Date {
    return Date(this.year, month, this.day)
}

fun Date.withDay(day: Int): Date {
    return Date(this.year, this.month, day)
}
