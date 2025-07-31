package com.isaevapps.data.repository

import com.isayevapps.domain.AnimeItem
import com.isayevapps.domain.cloud.AnimeCloudDataSource
import com.isayevapps.domain.cloud.Resource
import com.isayevapps.domain.local.AnimeLocalDataSource
import com.isayevapps.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchRepositoryImpl @Inject constructor(
    private val animeCloudDataSource: AnimeCloudDataSource,
    private val animeLocalDataSource: AnimeLocalDataSource
) : SearchRepository {

    override suspend fun searchAnime(query: String, page: Int): Resource<Pair<List<AnimeItem>, Boolean>> {
        return animeCloudDataSource.searchAnime(query, page)
    }

    override fun getFullHistory(): Flow<List<String>> {
        return animeLocalDataSource.getFullHistory()
    }

    override suspend fun insertQuery(query: String) {
        animeLocalDataSource.insert(query)
    }

    override suspend fun deleteQuery(query: String) {
        animeLocalDataSource.deleteExact(query)
    }
}