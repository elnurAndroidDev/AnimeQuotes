package com.isayevapps.domain.cloud

import com.isayevapps.domain.AnimeItem

interface AnimeCloudRepository {
    suspend fun getAnime() : Resource<List<AnimeItem>>
}