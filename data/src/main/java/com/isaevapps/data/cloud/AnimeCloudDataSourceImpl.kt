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

    override suspend fun getAnime(page: Int): Resource<List<AnimeItem>> {
        return try {
            val animeResponse = apiService.getAnime(page)
            val anime = animeResponse.data.map { it.toDomain() }
            Resource.Success(anime)
        } catch (e: Exception) {
            Resource.Error(e)
        }
    }

}