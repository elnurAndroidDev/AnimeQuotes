package com.isayevapps.domain.usecase

import com.isayevapps.domain.repository.SearchRepository

class GetSearchResultUseCase(private val searchRepository: SearchRepository) {
    suspend operator fun invoke(query: String, page: Int) = searchRepository.searchAnime(query, page)
}