package com.isayevapps.presentation.screens.search.searchresultscreen

import com.isayevapps.domain.AnimeItem

data class SearchResultScreenUiState(
    val animeList: List<AnimeItem> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
)