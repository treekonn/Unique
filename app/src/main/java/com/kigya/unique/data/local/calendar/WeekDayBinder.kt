package com.kigya.unique.data.local.calendar

import android.view.View
import com.kizitonwose.calendar.core.WeekDay
import com.kizitonwose.calendar.view.WeekCalendarView
import com.kizitonwose.calendar.view.WeekDayBinder

class WeekDayBinder(
    private val calendarView: WeekCalendarView,
    private val clickListener: WeekDayClickListener,
    private val dateBinder: WeekDayDateBinder
) : WeekDayBinder<WeekDayViewContainer> {
    override fun create(view: View): WeekDayViewContainer =
        WeekDayViewContainer(view, calendarView, clickListener)

    override fun bind(container: WeekDayViewContainer, data: WeekDay) {
        container.weekDay = data
        dateBinder.bindDate(
            data.date,
            container.tvDay,
            container.tvWeekDay,
            container.wrapper
        )
    }
}