package com.kigya.bsutools.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(
    tableName = "rows"
)
data class Row(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val day: String,
    val time: String,
    val group: String?,
    val subject: String,
    val teacher: String,
    val type: String?,
    val audience: String?
) : Serializable