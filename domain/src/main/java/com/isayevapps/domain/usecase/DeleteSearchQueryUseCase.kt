package com.isayevapps.domain.usecase

import com.isayevapps.domain.repository.SearchRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class DeleteSearchQueryUseCase(
    private val searchHistoryRepository: SearchRepository,
    private val dispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(query: String) = withContext(dispatcher) {
        searchHistoryRepository.deleteQuery(query)
    }
}