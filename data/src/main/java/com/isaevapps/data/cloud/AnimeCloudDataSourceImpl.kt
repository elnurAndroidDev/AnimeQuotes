package com.isaevapps.data.cloud

import com.isaevapps.data.toDomain
import com.isayevapps.domain.AnimeItem
import com.isayevapps.domain.cloud.AnimeCloudDataSource
import com.isayevapps.domain.cloud.Resource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AnimeCloudDataSourceImpl @Inject constructor(
    private val apiService: AnimeService
) : AnimeCloudDataSource {

    override suspend fun getAnime(page: Int, pageSize: Int): Resource<List<AnimeItem>> {
        return try {
            val animeResponse = apiService.getAnime(page, pageSize)
            val anime = animeResponse.data.map { it.toDomain() }
            Resource.Success(anime)
        } catch (e: Exception) {
            Resource.Error(e)
        }
    }

    override suspend fun searchAnime(query: String, page: Int): Resource<Pair<List<AnimeItem>, Boolean>> {
        return try {
            val animeResponse = apiService.searchAnime(query, page)
            val anime = animeResponse.data.map { it.toDomain() }
            val hasNextPage = animeResponse.pagination.hasNextPage
            Resource.Success(Pair(anime, hasNextPage))
        } catch (e: Exception) {
            Resource.Error(e)
        }
    }

}