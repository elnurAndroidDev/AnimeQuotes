package com.isayevapps.domain.local

import com.isayevapps.domain.AnimeItem

interface AnimeLocalRepository {
    suspend fun insertAll(animeList: List<AnimeItem>)
    fun <T> getAllAnime(): T
    fun <T> getAnimeById(animeId: Int): T
}