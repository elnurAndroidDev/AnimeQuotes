package com.isaevapps.data.repository

import com.isayevapps.domain.AnimeItem
import com.isayevapps.domain.cloud.AnimeCloudDataSource
import com.isayevapps.domain.cloud.Resource
import com.isayevapps.domain.repository.SearchRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchRepositoryImpl @Inject constructor(
    private val animeCloudDataSource: AnimeCloudDataSource
) : SearchRepository {

    private var currentPage = 1
    private var currentQuery = ""
    private var hasNextPage = true

    override suspend fun searchAnime(query: String, page: Int): Resource<List<AnimeItem>> {
        if (query != currentQuery) {
            currentPage = 1
            currentQuery = query
            hasNextPage = true
        }
        if (currentQuery.isBlank() || !hasNextPage)
            return Resource.Success(emptyList())
        val result = animeCloudDataSource.searchAnime(query, page)
        if (result is Resource.Success) {
            hasNextPage = result.data.second
            if (hasNextPage)
                currentPage++
        }
        return when (result) {
            is Resource.Success -> Resource.Success(result.data.first)
            is Resource.Error -> Resource.Error(result.error)
        }
    }
}