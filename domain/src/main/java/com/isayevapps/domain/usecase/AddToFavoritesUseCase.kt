package com.isayevapps.domain.usecase

import com.isayevapps.domain.AnimeItem
import com.isayevapps.domain.repository.FavoritesRepository
import com.isayevapps.domain.repository.Repository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class AddToFavoritesUseCase(
    private val favoritesRepository: FavoritesRepository,
    private val dispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(anime: AnimeItem) {
        withContext(dispatcher) {
            favoritesRepository.addToFavorite(anime)
        }
    }
}