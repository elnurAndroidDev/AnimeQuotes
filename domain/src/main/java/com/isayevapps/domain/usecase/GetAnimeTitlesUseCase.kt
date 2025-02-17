package com.isayevapps.domain.usecase

import com.isayevapps.domain.AnimeItem
import com.isayevapps.domain.repository.Repository
import kotlinx.coroutines.flow.Flow

class GetAnimeUseCase(
    private val repository: Repository
) {
    operator fun invoke(): Flow<List<AnimeItem>> = repository.getAllAnime()
}