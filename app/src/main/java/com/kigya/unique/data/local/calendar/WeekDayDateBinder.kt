package com.kigya.unique.data.local.calendar

import android.view.ViewGroup
import android.widget.TextView
import java.time.LocalDate

fun interface WeekDayDateBinder {
    fun bindDate(date: LocalDate, tvDate: TextView, tvWeekDay: TextView, wrapper: ViewGroup)
}