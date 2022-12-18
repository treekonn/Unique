package com.kigya.unique.data.remote

import com.kigya.unique.data.dto.lesson.Lesson

interface LessonApiSource {
    suspend fun getNetworkData(): List<Lesson>
}