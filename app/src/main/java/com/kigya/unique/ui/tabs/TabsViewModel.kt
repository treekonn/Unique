package com.kigya.unique.ui.tabs

import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.kigya.unique.App
import com.kigya.unique.data.local.calendar.WeekDayClickListener
import com.kigya.unique.data.local.calendar.WeekDayDateBinder
import com.kigya.unique.data.local.settings.AppSettings
import com.kigya.unique.ui.base.BaseViewModel
import com.kigya.unique.utils.calendar.CalendarHelper.setActive
import com.kigya.unique.utils.calendar.CalendarHelper.setInactive
import com.kigya.unique.utils.converters.LocaleConverter.Russian.russianValue
import com.kigya.unique.utils.calendar.CalendarHelper
import com.kigya.unique.utils.logger.Logger
import com.kizitonwose.calendar.view.WeekCalendarView
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class TabsViewModel @Inject constructor(
    appSettings: AppSettings,
    logger: Logger
) : BaseViewModel(appSettings, logger), WeekDayClickListener, WeekDayDateBinder {

    private var selectedDate: LocalDate = CalendarHelper.currentDate
    private var previousSelectedDate = CalendarHelper.currentDate

    private fun swapDates(date: LocalDate) {
        previousSelectedDate = selectedDate
        selectedDate = date
    }

    override fun dateClicked(weekCalendarView: WeekCalendarView, date: LocalDate) {
        swapDates(date)
        Toast.makeText(
            App.appContext,
            "Selected date is $date",
            Toast.LENGTH_SHORT
        ).show()
        weekCalendarView.notifyCalendarChanged()
    }


    override fun bindDate(date: LocalDate, tvDate: TextView, tvWeekDay: TextView, wrapper: ViewGroup) {
        tvDate.text = date.dayOfMonth.toString()
        tvWeekDay.text = date.dayOfWeek.russianValue()
        if (date == previousSelectedDate) {
            setInactive(tvDate, tvWeekDay, wrapper)
        }
        if (date == selectedDate) {
            setActive(tvDate, tvWeekDay, wrapper)
        } else {
            setInactive(tvDate, tvWeekDay, wrapper)
        }
    }
}







