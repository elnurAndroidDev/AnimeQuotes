package com.isayevapps.domain.usecase

import androidx.paging.PagingData
import com.isayevapps.domain.AnimeItem
import com.isayevapps.domain.cloud.Resource
import com.isayevapps.domain.repository.Repository
import kotlinx.coroutines.flow.Flow

class GetAnimeUseCase(
    private val repository: Repository
) {
    operator fun invoke(): Flow<PagingData<AnimeItem>> = repository.getAnime()
}