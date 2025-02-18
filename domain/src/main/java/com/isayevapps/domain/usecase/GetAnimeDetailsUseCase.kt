package com.isayevapps.domain.usecase

import com.isayevapps.domain.AnimeItem
import com.isayevapps.domain.repository.Repository
import kotlinx.coroutines.flow.Flow

class GetAnimeDetailsUseCase(private val repository: Repository) {
    operator fun invoke(animeId: Int): Flow<AnimeItem?> = repository.getAnimeDetails(animeId)
}