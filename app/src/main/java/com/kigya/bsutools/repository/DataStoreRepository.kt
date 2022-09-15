package com.kigya.bsutools.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.kigya.bsutools.utils.Constants
import com.kigya.bsutools.utils.datastore.PreferencesKeys
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(Constants.TIMETABLE_PREFERENCES)

class DataStoreRepository @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {
    var course = 1
    var group = 1

    suspend fun savetoDataStore(course: Int, group: Int) {
        dataStore.edit {
            it[PreferencesKeys.COURSE] = course
            this.course = course
            it[PreferencesKeys.GROUP] = group
            this.group = group
        }
    }

    fun getFromDataStore(): Flow<Pair<Int, Int>> =
        dataStore.data.map {
            Pair(
                it[PreferencesKeys.COURSE] ?: 3,
                it[PreferencesKeys.GROUP] ?: 9
            )
        }

}