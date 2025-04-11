package com.isayevapps.domain.repository

import com.isayevapps.domain.AnimeItem
import com.isayevapps.domain.cloud.Resource
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun loadItems(loadType: LoadType): LoadResult
    fun getAllAnime(): Flow<List<AnimeItem>>
    fun getAnimeDetails(animeId: Int): Flow<AnimeItem?>

    suspend fun getAnimeById(animeId: Int): Resource<AnimeItem>
}