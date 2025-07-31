package com.isayevapps.domain.repository

import com.isayevapps.domain.AnimeItem
import com.isayevapps.domain.cloud.Resource
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    suspend fun searchAnime(query: String, page: Int): Resource<Pair<List<AnimeItem>, Boolean>>

    //Search history
    fun getFullHistory(): Flow<List<String>>
    suspend fun insertQuery(query: String)
    suspend fun deleteQuery(query: String)
}