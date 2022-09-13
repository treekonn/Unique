package com.kigya.bsutools.ui

import androidx.lifecycle.*
import com.kigya.bsutools.adapters.RowAdapter
import com.kigya.bsutools.models.Row
import com.kigya.bsutools.repository.DataStoreRepository
import com.kigya.bsutools.repository.RowRepository
import com.kigya.bsutools.utils.Resource
import com.skydoves.powerspinner.OnSpinnerItemSelectedListener
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RowsViewModel @Inject constructor(
    rowRepository: RowRepository,
    dataStoreRepository: DataStoreRepository
) : ViewModel(), OnSpinnerItemSelectedListener<String> {

    var rows: LiveData<Resource<List<Row>>> = fetchRows(dataStoreRepository, rowRepository)
    var timetableList: List<Row> = emptyList()

    val rowAdapter = RowAdapter()
    var currentDayIndex: Int? = null

    init {
        viewModelScope.launch {
            dataStoreRepository.savetoDataStore(3, 9)
        }
    }

    private fun filterTimetable(list: List<Row>, index: Int): List<Row> {
        return list.filter {
            when (index) {
                0 -> it.day == "Понедельник"
                1 -> it.day == "Вторник"
                2 -> it.day == "Среда"
                3 -> it.day == "Четверг"
                4 -> it.day == "Пятница"
                5 -> it.day == "Суббота"
                else -> it.day == "Понедельник"
            }
        }
    }

    override fun onItemSelected(oldIndex: Int, oldItem: String?, newIndex: Int, newItem: String) {
        rowAdapter.submitList(filterTimetable(timetableList, newIndex))
        currentDayIndex = newIndex
    }

//    fun retrieveData(dataStoreRepository: DataStoreRepository) {
//        viewModelScope.launch(Dispatchers.IO) {
//            dataStoreRepository.getFromDataStore().collect() {
//                pairLiveData.postValue(it)
//            }
//        }
//    }

    private fun fetchRows(
        dataStoreRepository: DataStoreRepository,
        rowRepository: RowRepository
    ): LiveData<Resource<List<Row>>> {
        return Transformations.switchMap(dataStoreRepository.getFromDataStore().asLiveData()) {
            return@switchMap rowRepository.getRows(it.first, it.second).asLiveData()
        }
    }
}

