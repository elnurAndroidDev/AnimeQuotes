package com.isayevapps.domain.cloud

interface AnimeCloudRepository {
    suspend fun getAnime() : Resource<List<AnimeItem>>
}