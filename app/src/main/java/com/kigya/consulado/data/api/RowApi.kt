package com.kigya.consulado.data.api

import com.kigya.consulado.data.models.Row

interface RowApi {
    suspend fun getAllRows(course: Int, group: Int): List<Row>
}