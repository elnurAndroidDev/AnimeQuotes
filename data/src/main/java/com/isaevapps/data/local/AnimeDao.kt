package com.isaevapps.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AnimeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(animeList: List<AnimeEntity>)

    @Query("SELECT * FROM anime_titles")
    fun getAllAnime(): Flow<List<AnimeEntity>>

    @Query("SELECT * FROM anime_titles WHERE animeId = :animeId")
    fun getAnimeById(animeId: Int): Flow<AnimeEntity?>
}