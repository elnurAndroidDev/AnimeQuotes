package com.isayevapps.domain.local

import com.isayevapps.domain.AnimeItem

interface AnimeLocalRepository {
    suspend fun insertAll(animeList: List<AnimeItem>)
    suspend fun getAllAnime(): List<AnimeItem>
    suspend fun getAnimeById(animeId: Int): AnimeItem?
}