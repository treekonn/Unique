package com.kigya.unique.domain.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.kigya.unique.utils.datastore.PreferencesKeys
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataStoreRepository @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {

    suspend fun savetoDataStore(course: Int, group: Int) {
        dataStore.edit {
            it[PreferencesKeys.COURSE] = course
            it[PreferencesKeys.GROUP] = group
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