package com.isayevapps.domain.repository

import androidx.paging.PagingData
import com.isayevapps.domain.AnimeItem
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun getAnime(): Flow<PagingData<AnimeItem>>
}