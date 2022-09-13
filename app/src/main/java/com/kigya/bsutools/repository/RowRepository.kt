package com.kigya.bsutools.repository

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.room.withTransaction
import com.kigya.bsutools.api.RowApi
import com.kigya.bsutools.db.RowDatabase
import com.kigya.bsutools.utils.Constants
import com.kigya.bsutools.utils.networkBoundResource
import kotlinx.coroutines.delay
import javax.inject.Inject

class RowRepository @Inject constructor(
    private val rowApi: RowApi,
    private val database: RowDatabase
) {

    private val rowDao = database.getRowDao()

    fun getRows(course: Int, group: Int) = networkBoundResource(
        query = {
            rowDao.getAllRows()
        },
        fetch = {
            rowApi.getAllRows(course, group)
        },
        saveFetchResult = { rows ->
            database.withTransaction {
                rowDao.deleteRow()
                rowDao.upsertRows(rows)
            }

        }
    )


}