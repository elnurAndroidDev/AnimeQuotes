package com.isayevapps.domain.usecase

import com.isayevapps.domain.repository.FavoritesRepository

class RemoveFromFavoritesUseCase(private val favoritesRepository: FavoritesRepository) {
    suspend operator fun invoke(id: Int) = favoritesRepository.removeFromFavorite(id)
}