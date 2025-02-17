package com.isayevapps.domain.repository

import com.isayevapps.domain.AnimeItem
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun loadItems(loadType: LoadType): LoadResult
    fun getAllAnime(): Flow<List<AnimeItem>>
}