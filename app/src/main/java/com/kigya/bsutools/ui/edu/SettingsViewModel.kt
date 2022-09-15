package com.kigya.bsutools.ui.edu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kigya.bsutools.R
import com.kigya.bsutools.repository.DataStoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) : ViewModel() {

    private var course = 1
    private var group = 1

    val pairState = dataStoreRepository.getFromDataStore()

    fun collectData() {
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreRepository.savetoDataStore(course, group)
        }
    }

    fun getCurrentCourseId(value: Int): Int {
        return when (value) {
            1 -> R.id.courseFirst
            2 -> R.id.courseSecond
            3 -> R.id.courseThird
            4 -> R.id.courseFourth
            else -> R.id.courseFirst
        }
    }

    fun getCurrentGroupId(value: Int): Int {
        return when (value) {
            1 -> R.id.groupFirst
            2 -> R.id.groupSecond
            3 -> R.id.groupThird
            4 -> R.id.groupFourth
            5 -> R.id.groupFifth
            6 -> R.id.groupSixth
            7 -> R.id.groupSeventh
            8 -> R.id.groupEighth
            9 -> R.id.groupNine
            10 -> R.id.groupTen
            11 -> R.id.groupEleven
            else -> R.id.groupFirst
        }
    }

    fun performGroupIdActions(checkedId: Int) {
        group = when (checkedId) {
            R.id.groupFirst -> 1
            R.id.groupSecond -> 2
            R.id.groupThird -> 3
            R.id.groupFourth -> 4
            R.id.groupFifth -> 5
            R.id.groupSixth -> 6
            R.id.groupSeventh -> 7
            R.id.groupEighth -> 8
            R.id.groupNine -> 9
            R.id.groupTen -> 10
            R.id.groupEleven -> 11
            else -> 1
        }
    }

    fun performCourseIdActions(checkedId: Int) {
        course = when (checkedId) {
            R.id.courseFirst -> 1
            R.id.courseSecond -> 2
            R.id.courseThird -> 3
            R.id.courseFourth -> 4
            else -> 1
        }
    }
}