package com.isayevapps.domain.usecase

import com.isayevapps.domain.repository.FavoritesRepository

class GetFavoriteDetailUseCase(private val favoritesRepository: FavoritesRepository) {
    suspend operator fun invoke(animeId: Int) = favoritesRepository.getFavoriteAnimeDetails(animeId)
}