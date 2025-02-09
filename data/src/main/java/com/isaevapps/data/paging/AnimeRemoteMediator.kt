package com.isaevapps.data.paging

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.isaevapps.data.local.entities.AnimeEntity
import com.isayevapps.domain.cloud.AnimeCloudDataSource
import com.isayevapps.domain.cloud.Resource
import com.isayevapps.domain.local.AnimeLocalDataSource
import com.isayevapps.domain.local.RemoteKeys
import javax.inject.Inject
import javax.inject.Singleton

@OptIn(ExperimentalPagingApi::class)
@Singleton
class AnimeRemoteMediator @Inject constructor(
    private val animeCloudDataSource: AnimeCloudDataSource,
    private val animeLocalDataSource: AnimeLocalDataSource
) : RemoteMediator<Int, AnimeEntity>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, AnimeEntity>
    ): MediatorResult {
        val currentPage: Int = when (loadType) {
            LoadType.REFRESH -> 1
            LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
            LoadType.APPEND -> animeLocalDataSource.getLastRemoteKeys()?.nextKey
                ?: return MediatorResult.Success(endOfPaginationReached = true)
        }

        Log.d("XXX", "MovieRemoteMediator: load() called with: loadType = $loadType, page: $currentPage, loadSize=${state.config.initialLoadSize}")

        when (val result = animeCloudDataSource.getAnime(currentPage, state.config.pageSize)) {
            is Resource.Success -> {
                Log.d("XXX", "success loaded ${result.data.size}")

                val anime = result.data
                val endOfPaginationReached = anime.isEmpty()

                val prevPage = if (currentPage == 1) null else currentPage - 1
                val nextPage = if (endOfPaginationReached) null else currentPage + 1
                val keys = RemoteKeys(prevKey = prevPage, nextKey = nextPage)

                animeLocalDataSource.withTransaction {
                    if (loadType == LoadType.REFRESH) {
                        animeLocalDataSource.clearAll()
                        animeLocalDataSource.clearAllRemoteKeys()
                    }

                    animeLocalDataSource.insertAll(anime)
                    animeLocalDataSource.addRemoteKeys(keys)
                }

                return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
            }

            is Resource.Error -> {
                Log.d("XXX", " error RemoteMediator: get animes from remote ${result.error}")
                return MediatorResult.Error(result.error)
            }
        }
    }
}