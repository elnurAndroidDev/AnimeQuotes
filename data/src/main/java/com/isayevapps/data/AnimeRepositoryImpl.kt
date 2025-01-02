package com.isayevapps.data

import com.isayevapps.domain.AnimeItem
import com.isayevapps.domain.AnimeRepository
import com.isayevapps.domain.Resource

class AnimeRepositoryImpl(private val apiService: AnimeService): AnimeRepository {
    override suspend fun getAnime(): Resource<List<AnimeItem>> {
        return try {
            val animeResponse = apiService.getAnime()
            val animes = animeResponse.data.map { animeInfo ->
                AnimeItem(
                    title = animeInfo.title,
                    imgUrl = animeInfo.images.jpg.imageUrl
                )
            }
            Resource.Success(animes)
        } catch (e: Exception) {
            Resource.Error(e.message ?: "Unknown error")
        }
    }

}