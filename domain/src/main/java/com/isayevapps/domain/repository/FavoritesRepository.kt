package com.isayevapps.domain.repository

import com.isayevapps.domain.AnimeItem
import kotlinx.coroutines.flow.Flow

interface FavoritesRepository {
    suspend fun addToFavorite(anime: AnimeItem)
    suspend fun removeFromFavorite(animeId: Int)
    suspend fun isFavorite(animeId: Int): Boolean
    fun getAllFavorite(): Flow<List<AnimeItem>>
    suspend fun getFavoriteAnimeDetails(animeId: Int): AnimeItem?
}