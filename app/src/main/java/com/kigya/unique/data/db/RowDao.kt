package com.kigya.unique.data.db

import androidx.room.*
import com.kigya.unique.data.models.Row
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