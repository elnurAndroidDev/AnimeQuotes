package com.isaevapps.data.local

import com.isaevapps.data.local.dao.AnimeDao
import com.isaevapps.data.local.dao.FavoriteDao
import com.isaevapps.data.local.dao.SearchHistoryDao
import com.isaevapps.data.local.entities.SearchQuery
import com.isaevapps.data.toAnimeEntity
import com.isaevapps.data.toDomain
import com.isaevapps.data.toFavoriteEntity
import com.isayevapps.domain.AnimeItem
import com.isayevapps.domain.local.AnimeLocalDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AnimeLocalDataSourceImpl @Inject constructor(
    private val animeDao: AnimeDao,
    private val favoriteDao: FavoriteDao,
    private val searchHistoryDao: SearchHistoryDao
) : AnimeLocalDataSource {

    override suspend fun insertAll(animeList: List<AnimeItem>) {
        val animeEntities = animeList.map { it.toAnimeEntity() }
        animeDao.insertAll(animeEntities)
    }

    override suspend fun addToFavorites(anime: AnimeItem) {
        favoriteDao.insertFavorite(anime.toFavoriteEntity())
    }

    override suspend fun removeFromFavorites(animeId: Int) {
        favoriteDao.deleteFavorite(animeId)
    }

    override fun getAllFavorite(): Flow<List<AnimeItem>> {
        return favoriteDao.getAllFavorites()
            .map { favoriteEntities -> favoriteEntities.map { it.toDomain() } }
    }

    override suspend fun isFavorite(animeId: Int) = favoriteDao.isFavorite(animeId)

    override suspend fun getFavoriteAnimeDetails(animeId: Int): AnimeItem? {
        return favoriteDao.getFavoriteAnimeDetails(animeId)?.toDomain()
    }

    override fun getFullHistory(): Flow<List<String>> {
        return searchHistoryDao.getFullHistory()
            .map { it.map { queryList -> queryList.query } }
    }

    override suspend fun insert(query: String) {
        searchHistoryDao.insert(SearchQuery(query = query))
    }

    override suspend fun deleteExact(query: String) {
        searchHistoryDao.deleteExact(query)
    }

    override fun getAllAnime(): Flow<List<AnimeItem>> {
        return animeDao.getAllAnime()
            .map { animeEntities -> animeEntities.map { it.toDomain() } }
    }

    override fun getAnimeDetails(animeId: Int): Flow<AnimeItem?> {
        return animeDao.getAnimeById(animeId).map { it?.toDomain() }
    }

    override suspend fun clearAll() {
        animeDao.clearAll()
    }
}