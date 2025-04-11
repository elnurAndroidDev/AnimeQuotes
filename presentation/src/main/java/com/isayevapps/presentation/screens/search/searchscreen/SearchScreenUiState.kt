package com.isayevapps.presentation.screens.search.searchscreen

import com.isayevapps.domain.AnimeItem

data class SearchScreenUiState(
    val query: String = "",
    val animeList: List<AnimeItem> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
)