package com.isayevapps.domain.usecase

import com.isayevapps.domain.repository.SearchRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class GetSearchResultUseCase(
    private val searchRepository: SearchRepository,
    private val dispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(query: String, page: Int) =
        withContext(dispatcher) { searchRepository.searchAnime(query, page) }
}