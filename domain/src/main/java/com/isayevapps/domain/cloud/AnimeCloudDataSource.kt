package com.isayevapps.domain.cloud

import com.isayevapps.domain.AnimeItem
import com.isayevapps.domain.result.CloudError
import com.isayevapps.domain.result.Result

interface AnimeCloudDataSource {
    suspend fun getAnime(page: Int, pageSize: Int): Result<List<AnimeItem>, CloudError>
    suspend fun getAnimeById(animeId: Int): Result<AnimeItem, CloudError>

    suspend fun searchAnime(
        query: String,
        page: Int
    ): Result<Pair<List<AnimeItem>, Boolean>, CloudError>
}