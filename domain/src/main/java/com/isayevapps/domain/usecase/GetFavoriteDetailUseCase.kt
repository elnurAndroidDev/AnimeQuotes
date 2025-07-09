package com.isayevapps.domain.usecase

import com.isayevapps.domain.repository.FavoritesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class GetFavoriteDetailUseCase(
    private val favoritesRepository: FavoritesRepository,
    private val dispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(animeId: Int) =
        withContext(dispatcher) { favoritesRepository.getFavoriteAnimeDetails(animeId) }
}