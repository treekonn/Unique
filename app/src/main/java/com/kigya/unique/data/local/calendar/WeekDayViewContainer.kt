package com.kigya.unique.data.local.calendar

import android.view.View
import com.kigya.unique.databinding.CalendarDayItemBinding
import com.kizitonwose.calendar.core.WeekDay
import com.kizitonwose.calendar.view.ViewContainer
import com.kizitonwose.calendar.view.WeekCalendarView

class WeekDayViewContainer(
    view: View,
    calendarView: WeekCalendarView,
    clickListener: WeekDayClickListener
) : ViewContainer(view) {
    var tvDay = CalendarDayItemBinding.bind(view).tvDayItemDate
    var tvWeekDay = CalendarDayItemBinding.bind(view).tvDayItemWeekDay
    var wrapper = CalendarDayItemBinding.bind(view).cvDayItemCalendar

    lateinit var weekDay: WeekDay

    init {
        view.setOnClickListener {
            clickListener.dateClicked(calendarView, weekDay.date)
        }
    }
}