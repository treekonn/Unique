package com.kigya.unique.domain.repository

import androidx.room.withTransaction
import com.kigya.unique.data.api.RowApi
import com.kigya.unique.data.db.RowDatabase
import com.kigya.unique.core.networkBoundResource
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
                if (rows.isNotEmpty()) rowDao.deleteRows()
                rowDao.upsertRows(rows)
            }
        }
    )
}