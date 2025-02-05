package com.isaevapps.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.isaevapps.data.local.entities.AnimeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AnimeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(animeList: List<AnimeEntity>)

    @Query("SELECT * FROM anime_titles")
    fun getAllAnime(): PagingSource<Int,AnimeEntity>

    @Query("SELECT * FROM anime_titles WHERE animeId = :animeId")
    fun getAnimeById(animeId: Int): Flow<AnimeEntity?>

    @Query("DELETE FROM anime_titles")
    suspend fun clearAll()
}