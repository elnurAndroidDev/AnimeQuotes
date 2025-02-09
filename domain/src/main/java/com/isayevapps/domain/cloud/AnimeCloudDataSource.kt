package com.isayevapps.domain.cloud

import com.isayevapps.domain.AnimeItem

interface AnimeCloudDataSource {
    suspend fun getAnime(page: Int, pageSize: Int) : Resource<List<AnimeItem>>
}