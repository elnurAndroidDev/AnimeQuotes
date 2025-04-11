package com.isayevapps.domain.usecase

import com.isayevapps.domain.repository.Repository

class GetDetailsFromCloudUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(animeId: Int) = repository.getAnimeById(animeId)
}