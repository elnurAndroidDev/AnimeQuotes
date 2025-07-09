package com.isayevapps.domain.usecase

import com.isayevapps.domain.repository.FavoritesRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class IsFavoriteUseCase(
    private val favoritesRepository: FavoritesRepository,
    private val dispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(id: Int): Boolean {
        return withContext(dispatcher) { favoritesRepository.isFavorite(id) }
    }
}