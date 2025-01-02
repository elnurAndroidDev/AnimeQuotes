package com.isayevapps.domain

interface AnimeRepository {
    suspend fun getAnime() : Resource<List<AnimeItem>>
}