package com.kigya.unique.data.api

import com.kigya.unique.data.models.Row

interface RowApi {
    suspend fun getAllRows(course: Int, group: Int): List<Row>
}