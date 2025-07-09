package com.isayevapps.domain.usecase

import com.isayevapps.domain.repository.Repository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class GetDetailsFromCloudUseCase(
    private val repository: Repository,
    private val dispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(animeId: Int) =
        withContext(dispatcher) { repository.getAnimeById(animeId) }
}