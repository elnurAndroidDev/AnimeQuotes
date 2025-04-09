package com.isayevapps.domain.usecase

import com.isayevapps.domain.AnimeItem
import com.isayevapps.domain.repository.FavoritesRepository
import kotlinx.coroutines.flow.Flow

class GetFavoritesUseCase(private val favoritesRepository: FavoritesRepository) {
    operator fun invoke(): Flow<List<AnimeItem>> {
        return favoritesRepository.getAllFavorite()
    }
}