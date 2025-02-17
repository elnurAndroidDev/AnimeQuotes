package com.isaevapps.data.local

import com.isaevapps.data.local.dao.AnimeDao
import com.isaevapps.data.toAnimeEntity
import com.isaevapps.data.toDomain
import com.isayevapps.domain.AnimeItem
import com.isayevapps.domain.local.AnimeLocalDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Suppress("UNCHECKED_CAST")
@Singleton
class AnimeLocalDataSourceImpl @Inject constructor(
    private val animeDao: AnimeDao,
) : AnimeLocalDataSource {

    override suspend fun insertAll(animeList: List<AnimeItem>) {
        val animeEntities = animeList.map { it.toAnimeEntity() }
        animeDao.insertAll(animeEntities)
    }

    override fun getAllAnime(): Flow<List<AnimeItem>> {
        return animeDao.getAllAnime()
            .map { animeEntities -> animeEntities.map { it.toDomain() } }
    }

    override fun getAnimeById(animeId: Int): Flow<AnimeItem?> {
        return animeDao.getAnimeById(animeId).map { it?.toDomain() }
    }

    override suspend fun clearAll() {
        animeDao.clearAll()
    }
}