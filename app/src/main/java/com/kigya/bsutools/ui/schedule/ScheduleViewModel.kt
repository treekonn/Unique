package com.kigya.bsutools.ui.schedule

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.kigya.bsutools.core.Resource
import com.kigya.bsutools.data.models.Row
import com.kigya.bsutools.domain.repository.DataStoreRepository
import com.kigya.bsutools.domain.repository.RowRepository
import com.kigya.bsutools.ui.schedule.adapters.RowAdapter
import com.skydoves.powerspinner.OnSpinnerItemSelectedListener
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ScheduleViewModel @Inject constructor(
    rowRepository: RowRepository,
    dataStoreRepository: DataStoreRepository,
    //@Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel(), OnSpinnerItemSelectedListener<String> {

    var rows: LiveData<Resource<List<Row>>> = fetchRows(dataStoreRepository, rowRepository)
    var timetableList: List<Row> = emptyList()

    val rowAdapter = RowAdapter()
    var currentDayIndex: Int? = 0

    private fun filterTimetable(list: List<Row>, index: Int): List<Row> {
        return list.filter {
            when (index) {
                0 -> it.day == MONDAY
                1 -> it.day == TUESDAY
                2 -> it.day == WEDNESDAY
                3 -> it.day == THURSDAY
                4 -> it.day == FRIDAY
                5 -> it.day == SATURDAY
                else -> it.day == MONDAY
            }
        }
    }

    override fun onItemSelected(oldIndex: Int, oldItem: String?, newIndex: Int, newItem: String) {
        rowAdapter.submitList(filterTimetable(timetableList, newIndex))
        currentDayIndex = newIndex
    }

    fun clearUI() {
        rowAdapter.submitList(emptyList())
        currentDayIndex = null
    }

    private fun fetchRows(
        dataStoreRepository: DataStoreRepository,
        rowRepository: RowRepository
    ): LiveData<Resource<List<Row>>> {
        return Transformations.switchMap(dataStoreRepository.getFromDataStore().asLiveData()) {
            return@switchMap rowRepository.getRows(it.first, it.second).asLiveData()
        }
    }

    companion object {
        private const val MONDAY = "Понедельник"
        private const val TUESDAY = "Вторник"
        private const val WEDNESDAY = "Среда"
        private const val THURSDAY = "Четверг"
        private const val FRIDAY = "Пятница"
        private const val SATURDAY = "Суббота"
    }
}

