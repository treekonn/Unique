package com.kigya.unique.data.local

import androidx.room.withTransaction
import com.kigya.unique.data.local.db.LessonDatabase
import com.kigya.unique.data.remote.LessonApi
import com.kigya.unique.data.remote.networkBoundResource
import javax.inject.Inject

class LessonRepository @Inject constructor(
    private val lessonsApi: LessonApi,
    private val database: LessonDatabase
) {

    private val lessonDao = database.getLessonDao()

    fun getLessons(course: Int, group: Int) = networkBoundResource(
        query = {
            lessonDao.getLessons(course, group)
        },
        fetch = {
            lessonsApi.getNetworkData()
        },
        saveFetchResult = { rows ->
            database.withTransaction {
                if (rows.isNotEmpty()) lessonDao.deleteAllLessons()
                lessonDao.upsertLessons(rows)
            }
        }
    )
}