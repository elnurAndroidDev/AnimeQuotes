package com.isayevapps.domain.usecase

import com.isayevapps.domain.AnimeItem
import com.isayevapps.domain.repository.FavoritesRepository
import com.isayevapps.domain.repository.Repository

class AddToFavoritesUseCase(
    private val favoritesRepository: FavoritesRepository
) {
    suspend operator fun invoke(anime: AnimeItem) {
        favoritesRepository.addToFavorite(anime)
    }
}