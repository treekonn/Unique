package com.kigya.bsutools.data.db

import androidx.room.*
import com.kigya.bsutools.data.models.Row
import kotlinx.coroutines.flow.Flow

@Dao
interface RowDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertRows(rows: List<Row>)

    @Query("SELECT * FROM rows")
    fun getAllRows(): Flow<List<Row>>

    @Query("DELETE FROM rows")
    suspend fun deleteRows()
}