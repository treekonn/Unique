package com.kigya.bsutools.data.api

import com.kigya.bsutools.data.models.Row

interface RowApi {
    suspend fun getAllRows(course: Int, group: Int): List<Row>
}