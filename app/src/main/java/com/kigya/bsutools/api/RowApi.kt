package com.kigya.bsutools.api

import com.kigya.bsutools.models.Row

interface RowApi {
    suspend fun getAllRows(course: Int, group: Int): List<Row>
}