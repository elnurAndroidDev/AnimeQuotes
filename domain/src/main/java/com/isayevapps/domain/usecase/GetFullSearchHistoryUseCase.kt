package com.isayevapps.domain.usecase

import com.isayevapps.domain.repository.SearchRepository
import kotlinx.coroutines.CoroutineDispatcher

class GetFullSearchHistoryUseCase (
    private val searchRepository: SearchRepository
) {
    operator fun invoke() = searchRepository.getFullHistory()
}