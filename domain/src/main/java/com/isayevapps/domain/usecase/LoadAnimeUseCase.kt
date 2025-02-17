package com.isayevapps.domain.usecase

import com.isayevapps.domain.repository.LoadResult
import com.isayevapps.domain.repository.LoadType
import com.isayevapps.domain.repository.Repository

class LoadAnimeUseCase(
    private val repository: Repository
) {
    suspend operator fun invoke(loadType: LoadType): LoadResult = repository.loadItems(loadType)
}