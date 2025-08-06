package com.isayevapps.domain.repository

import com.isayevapps.domain.AnimeItem
import com.isayevapps.domain.result.CloudError
import com.isayevapps.domain.result.Result
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun loadItems(loadType: LoadType): Result<Unit, CloudError>
    fun getAllAnime(): Flow<List<AnimeItem>>
    fun getAnimeDetails(animeId: Int): Flow<AnimeItem?>

    suspend fun getAnimeById(animeId: Int): Result<AnimeItem, CloudError>
}