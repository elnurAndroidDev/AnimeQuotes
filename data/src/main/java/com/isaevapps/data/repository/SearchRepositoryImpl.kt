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

    override suspend fun searchAnime(query: String, page: Int): Resource<Pair<List<AnimeItem>, Boolean>> {
        return animeCloudDataSource.searchAnime(query, page)
    }
}