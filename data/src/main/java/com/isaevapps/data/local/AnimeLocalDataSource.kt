package com.isaevapps.data.local

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.map
import androidx.room.withTransaction
import com.isaevapps.data.local.dao.AnimeDao
import com.isaevapps.data.local.dao.RemoteKeysDao
import com.isaevapps.data.paging.AnimeRemoteMediator
import com.isaevapps.data.toAnimeEntity
import com.isaevapps.data.toDomain
import com.isaevapps.data.toEntity
import com.isayevapps.domain.AnimeItem
import com.isayevapps.domain.local.AnimeLocalDataSource
import com.isayevapps.domain.local.RemoteKeys
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Suppress("UNCHECKED_CAST")
@Singleton
class AnimeLocalDataSourceImpl @Inject constructor(
    private val animeDao: AnimeDao,
    private val animeDB: AnimeDataBase,
    private val remoteKeysDao: RemoteKeysDao
) : AnimeLocalDataSource {

    override suspend fun withTransaction(block: suspend () -> Unit) =
        animeDB.withTransaction {
            block()
        }

    override suspend fun insertAll(animeList: List<AnimeItem>) {
        val animeEntities = animeList.map { it.toAnimeEntity() }
        animeDao.insertAll(animeEntities)
    }

    override fun <T: Any> getAllAnime(): PagingSource<Int, T> {
        return animeDao.getAllAnime() as PagingSource<Int, T>
    }

    override fun getAnimeById(animeId: Int): Flow<AnimeItem?> {
        return animeDao.getAnimeById(animeId).map { it?.toDomain() }
    }

    override suspend fun clearAll() {
        animeDao.clearAll()
    }

    override suspend fun getLastRemoteKeys(): RemoteKeys? {
        return remoteKeysDao.getLastRemoteKey()?.toDomain()
    }

    override suspend fun addRemoteKeys(remoteKeys: RemoteKeys) {
        remoteKeysDao.addRemoteKeys(remoteKeys.toEntity())
    }

    override suspend fun clearAllRemoteKeys() {
        remoteKeysDao.deleteAllRemoteKeys()
    }
}