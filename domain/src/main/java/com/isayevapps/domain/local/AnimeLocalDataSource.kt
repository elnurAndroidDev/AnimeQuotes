package com.isayevapps.domain.local

import com.isayevapps.domain.AnimeItem
import kotlinx.coroutines.flow.Flow

interface AnimeLocalDataSource {
    suspend fun insertAll(animeList: List<AnimeItem>)
    fun getAllAnime(): Flow<List<AnimeItem>>
    fun getAnimeDetails(animeId: Int): Flow<AnimeItem?>
    suspend fun clearAll()
}