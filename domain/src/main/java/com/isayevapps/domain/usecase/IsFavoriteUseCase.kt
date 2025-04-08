package com.isayevapps.domain.usecase

import com.isayevapps.domain.repository.FavoritesRepository

class IsFavoriteUseCase(private val favoritesRepository: FavoritesRepository) {
    suspend operator fun invoke(id: Int): Boolean {
        return favoritesRepository.isFavorite(id)
    }
}