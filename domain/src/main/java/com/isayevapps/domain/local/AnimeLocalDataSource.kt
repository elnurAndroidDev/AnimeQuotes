package com.isayevapps.domain.local

import com.isayevapps.domain.AnimeItem
import kotlinx.coroutines.flow.Flow

interface AnimeLocalDataSource {
    suspend fun insertAll(animeList: List<AnimeItem>)
    fun getAllAnime(): Flow<List<AnimeItem>>
    fun getAnimeDetails(animeId: Int): Flow<AnimeItem?>
    suspend fun clearAll()

    suspend fun addToFavorites(anime: AnimeItem)
    suspend fun removeFromFavorites(animeId: Int)
    fun getAllFavorite(): Flow<List<AnimeItem>>
    suspend fun isFavorite(animeId: Int): Boolean
    suspend fun getFavoriteAnimeDetails(animeId: Int): AnimeItem?

    suspend fun getSuggestions(input: String): List<String>
    suspend fun insert(query: String)
    suspend fun deleteExact(query: String)

}