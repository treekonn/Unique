package com.kigya.unique.data.local.calendar

import com.kizitonwose.calendar.view.WeekCalendarView
import java.time.LocalDate

fun interface WeekDayClickListener {
    fun dateClicked(weekCalendarView: WeekCalendarView, date: LocalDate)
}