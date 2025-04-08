package com.isaevapps.data.repository

import com.isayevapps.domain.AnimeItem
import com.isayevapps.domain.local.AnimeLocalDataSource
import com.isayevapps.domain.repository.FavoritesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavoritesRepositoryImpl @Inject constructor(private val animeLocalDataSource: AnimeLocalDataSource): FavoritesRepository {

    override suspend fun addToFavorite(anime: AnimeItem) {
        animeLocalDataSource.addToFavorites(anime)
    }

    override suspend fun removeFromFavorite(animeId: Int) {
        animeLocalDataSource.removeFromFavorites(animeId)
    }

    override suspend fun isFavorite(animeId: Int) = animeLocalDataSource.isFavorite(animeId)

    override fun getAllFavorite(): Flow<List<AnimeItem>> {
        return animeLocalDataSource.getAllFavorite()
    }
}