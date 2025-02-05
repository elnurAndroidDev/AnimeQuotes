package com.isayevapps.domain.local

import androidx.paging.PagingSource
import com.isayevapps.domain.AnimeItem
import kotlinx.coroutines.flow.Flow

interface AnimeLocalDataSource {

    suspend fun withTransaction(block: suspend () -> Unit)
    suspend fun insertAll(animeList: List<AnimeItem>)
    fun <T : Any> getAllAnime(): PagingSource<Int, T>
    fun getAnimeById(animeId: Int): Flow<AnimeItem?>
    suspend fun clearAll()

    suspend fun getLastRemoteKeys(): RemoteKeys?
    suspend fun addRemoteKeys(remoteKeys: RemoteKeys)
    suspend fun clearAllRemoteKeys()
}