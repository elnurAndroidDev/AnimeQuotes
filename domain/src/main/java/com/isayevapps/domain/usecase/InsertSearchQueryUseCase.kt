package com.isayevapps.domain.usecase

import com.isayevapps.domain.repository.SearchRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class InsertSearchQueryUseCase(
    private val searchRepository: SearchRepository,
    private val dispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(query: String) = withContext(dispatcher) {
        searchRepository.insertQuery(query)
    }
}