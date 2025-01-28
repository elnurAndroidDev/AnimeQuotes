package com.isaevapps.data.local

import com.isaevapps.data.toAnimeEntity
import com.isaevapps.data.toAnimeItem
import com.isayevapps.domain.AnimeItem
import com.isayevapps.domain.local.AnimeLocalRepository
import kotlinx.coroutines.flow.Flow

class AnimeLocalRepositoryImp(private val animeDao: AnimeDao): AnimeLocalRepository {

    override suspend fun insertAll(animeList: List<AnimeItem>) {
        val animeEntities = animeList.map { it.toAnimeEntity() }
        animeDao.insertAll(animeEntities)
    }

    override fun <T> getAllAnime(): T {
        return animeDao.getAllAnime() as T
    }

    override fun <T> getAnimeById(animeId: Int): T {
        return animeDao.getAnimeById(animeId) as T
    }
}