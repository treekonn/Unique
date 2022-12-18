package com.kigya.unique.ui.tabs

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.kigya.unique.R
import com.kigya.unique.data.local.calendar.WeekDayBinder
import com.kigya.unique.data.local.calendar.WeekDayClickListener
import com.kigya.unique.data.local.calendar.WeekDayDateBinder
import com.kigya.unique.databinding.FragmentTabsBinding
import com.kigya.unique.utils.calendar.CalendarHelper.currentDate
import com.kigya.unique.utils.calendar.CalendarHelper.daysOfWeek
import com.kigya.unique.utils.calendar.CalendarHelper.endDate
import com.kigya.unique.utils.calendar.CalendarHelper.startDate
import com.kigya.unique.utils.converters.LocaleConverter.Russian.russianValue
import com.kigya.unique.utils.extensions.startCenterCircularReveal
import com.kizitonwose.calendar.view.DaySize
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate

@AndroidEntryPoint
class TabsFragment : Fragment(R.layout.fragment_tabs) {

    private val viewBinding by viewBinding(FragmentTabsBinding::bind)
    private val viewModel by viewModels<TabsViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.startCenterCircularReveal()
        setWindowLimits()

        with(viewBinding) {
            setupCurrentBackContainerValues(currentDate)
            setupCalendarView(viewModel, viewModel)
        }
    }
}

private fun FragmentTabsBinding.setupCalendarView(
    listener: WeekDayClickListener,
    dateBinder: WeekDayDateBinder
) {
    calendarView.apply {
        dayBinder = WeekDayBinder(calendarView, listener, dateBinder)
        setup(
            startDate,
            endDate,
            daysOfWeek.first()
        )
        daySize = DaySize.SeventhWidth
        scrollToDate(currentDate)
    }
}

private fun Fragment.setWindowLimits() {
    activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
}

private fun FragmentTabsBinding.setupCurrentBackContainerValues(currentDate: LocalDate) {
    tvCurrentDateBack.text = currentDate.dayOfMonth.toString()
    tvCurrentMonthYearBack.text =
        currentDate.month.russianValue().plus(" ").plus(currentDate.year)
    tvCurrentWeekdayBack.text = currentDate.dayOfWeek.russianValue()
}


