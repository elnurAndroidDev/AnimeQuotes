package com.isayevapps.domain.cloud.usecases

import com.isayevapps.domain.AnimeItem
import com.isayevapps.domain.cloud.AnimeCloudRepository
import com.isayevapps.domain.cloud.Resource

class GetAnimeTitlesUseCase(private val repository: AnimeCloudRepository) {
    suspend operator fun invoke(): Resource<List<AnimeItem>> {
        return repository.getAnime()
    }
}