package com.isaevapps.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.isaevapps.data.paging.AnimeRemoteMediator
import com.isaevapps.data.toDomain
import com.isayevapps.domain.AnimeItem
import com.isayevapps.domain.repository.Repository
import com.isayevapps.domain.local.AnimeLocalDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoryImpl @Inject constructor(
    private val animeLocalDataSource: AnimeLocalDataSource,
    private val animeRemoteMediator: AnimeRemoteMediator,
): Repository {
    @OptIn(ExperimentalPagingApi::class)
    override fun getAnime(): Flow<PagingData<AnimeItem>> = Pager(
        config = PagingConfig(
            pageSize = 25,
            initialLoadSize = 25,
            prefetchDistance = 1,
            enablePlaceholders = false
        ),
        remoteMediator = animeRemoteMediator,
        pagingSourceFactory = { animeLocalDataSource.getAllAnime() }
    ).flow.map { pagingData ->
        pagingData.map { it.toDomain() }
    }
}