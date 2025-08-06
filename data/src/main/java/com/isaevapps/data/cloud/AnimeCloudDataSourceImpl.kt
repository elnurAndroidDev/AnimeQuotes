package com.isaevapps.data.cloud

import com.isaevapps.data.toDomain
import com.isayevapps.domain.AnimeItem
import com.isayevapps.domain.cloud.AnimeCloudDataSource
import com.isayevapps.domain.result.CloudError
import com.isayevapps.domain.result.Result
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AnimeCloudDataSourceImpl @Inject constructor(
    private val apiService: AnimeService
) : AnimeCloudDataSource {

    override suspend fun getAnime(page: Int, pageSize: Int): Result<List<AnimeItem>, CloudError> {
        return safeApiCall(
            apiCall = { apiService.getAnime(page, pageSize) },
            map = { response -> response.data.map { it.toDomain() } }
        )
    }

    override suspend fun getAnimeById(animeId: Int): Result<AnimeItem, CloudError> {
        return safeApiCall(
            apiCall = { apiService.getAnimeById(animeId) },
            map = { response -> response.data.toDomain() }
        )
    }

    override suspend fun searchAnime(
        query: String,
        page: Int
    ): Result<Pair<List<AnimeItem>, Boolean>, CloudError> {
        return safeApiCall(
            apiCall = { apiService.searchAnime(query, page) },
            map = { response ->
                val animeList = response.data.map { it.toDomain() }
                val hasNextPage = response.pagination.hasNextPage
                Pair(animeList, hasNextPage)
            }
        )
    }

}