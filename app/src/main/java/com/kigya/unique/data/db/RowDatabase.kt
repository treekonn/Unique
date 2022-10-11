package com.kigya.unique.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.kigya.unique.data.models.Row

@Database(
    entities = [Row::class],
    version = 1
)
abstract class RowDatabase : RoomDatabase() {
    abstract fun getRowDao(): RowDao
}