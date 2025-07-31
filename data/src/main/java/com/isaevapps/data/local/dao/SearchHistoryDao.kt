package com.isaevapps.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.isaevapps.data.local.entities.SearchQuery
import kotlinx.coroutines.flow.Flow

@Dao
interface SearchHistoryDao {

    @Query("SELECT * FROM search_history ORDER BY timestamp DESC")
    fun getFullHistory(): Flow<List<SearchQuery>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(query: SearchQuery)

    @Query("DELETE FROM search_history WHERE `query` = :query")
    suspend fun deleteExact(query: String)
}
