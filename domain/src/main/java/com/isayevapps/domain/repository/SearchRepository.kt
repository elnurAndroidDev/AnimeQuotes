package com.isayevapps.domain.repository

import com.isayevapps.domain.AnimeItem
import com.isayevapps.domain.cloud.Resource

interface SearchRepository {
    suspend fun searchAnime(query: String, page: Int): Resource<List<AnimeItem>>
}