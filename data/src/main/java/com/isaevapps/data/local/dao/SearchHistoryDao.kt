package com.isaevapps.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.isaevapps.data.local.entities.SearchQuery

@Dao
interface SearchHistoryDao {

    @Query("SELECT * FROM search_history WHERE query LIKE '%' || :input || '%' ORDER BY id DESC LIMIT 10")
    suspend fun getSuggestions(input: String): List<SearchQuery>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(query: SearchQuery)

    @Query("DELETE FROM search_history WHERE query = :query")
    suspend fun deleteExact(query: String)
}
