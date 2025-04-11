package com.isayevapps.domain.cloud

import com.isayevapps.domain.AnimeItem

interface AnimeCloudDataSource {
    suspend fun getAnime(page: Int, pageSize: Int): Resource<List<AnimeItem>>
    suspend fun getAnimeById(animeId: Int): Resource<AnimeItem>

    //Boolean - hasNextPage
    suspend fun searchAnime(query: String, page: Int): Resource<Pair<List<AnimeItem>, Boolean>>
}