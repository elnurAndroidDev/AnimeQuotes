package com.isayevapps.domain

interface AnimeRepository {
    suspend fun getAnime() : List<AnimeItem>
}