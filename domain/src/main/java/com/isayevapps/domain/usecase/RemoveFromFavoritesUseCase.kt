package com.isayevapps.domain.usecase

import com.isayevapps.domain.repository.FavoritesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class RemoveFromFavoritesUseCase(
    private val favoritesRepository: FavoritesRepository,
    private val dispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(id: Int) =
        withContext(dispatcher) { favoritesRepository.removeFromFavorite(id) }
}