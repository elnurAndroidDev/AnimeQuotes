package com.isaevapps.data.repository

import com.isayevapps.domain.AnimeItem
import com.isayevapps.domain.cloud.AnimeCloudDataSource
import com.isayevapps.domain.cloud.Resource
import com.isayevapps.domain.local.AnimeLocalDataSource
import com.isayevapps.domain.repository.LoadResult
import com.isayevapps.domain.repository.LoadType
import com.isayevapps.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoryImpl @Inject constructor(
    private val animeLocalDataSource: AnimeLocalDataSource,
    private val animeCloudDataSource: AnimeCloudDataSource
) : Repository {

    private val pageSize = 25
    private var currentPage = 1

    override suspend fun loadItems(loadType: LoadType): LoadResult {
        if (loadType == LoadType.Refresh) {
            animeLocalDataSource.clearAll()
            currentPage = 1
        }
        when (val result = animeCloudDataSource.getAnime(currentPage, pageSize)) {
            is Resource.Success -> {
                if (result.data.isNotEmpty()) {
                    animeLocalDataSource.insertAll(result.data)
                    currentPage++
                }
            }

            is Resource.Error -> {
                return LoadResult.Error(result.error)
            }
        }
        return LoadResult.Success
    }

    override fun getAllAnime(): Flow<List<AnimeItem>> =
        animeLocalDataSource.getAllAnime()

    override fun getAnimeDetails(animeId: Int): Flow<AnimeItem?> =
        animeLocalDataSource.getAnimeDetails(animeId)

    override suspend fun getAnimeById(animeId: Int): Resource<AnimeItem> =
        animeCloudDataSource.getAnimeById(animeId)

}