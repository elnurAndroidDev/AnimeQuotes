package com.isayevapps.data

import com.isayevapps.domain.cloud.AnimeItem
import com.isayevapps.domain.cloud.AnimeCloudRepository
import com.isayevapps.domain.cloud.Resource

class AnimeCloudRepositoryImpl(private val apiService: AnimeService) : AnimeCloudRepository {
    override suspend fun getAnime(): Resource<List<AnimeItem>> {
        return try {
            val animeResponse = apiService.getAnime()
            val anime = animeResponse.data.map { it.toAnimeItem() }
            Resource.Success(anime)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Unknown error")
        }
    }

}