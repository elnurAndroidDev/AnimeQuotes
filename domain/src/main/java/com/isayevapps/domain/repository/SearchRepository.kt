package com.isayevapps.domain.repository

import com.isayevapps.domain.AnimeItem
import com.isayevapps.domain.result.CloudError
import com.isayevapps.domain.result.Result
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    suspend fun searchAnime(query: String, page: Int): Result<Pair<List<AnimeItem>, Boolean>, CloudError>

    //Search history
    fun getFullHistory(): Flow<List<String>>
    suspend fun insertQuery(query: String)
    suspend fun deleteQuery(query: String)
}