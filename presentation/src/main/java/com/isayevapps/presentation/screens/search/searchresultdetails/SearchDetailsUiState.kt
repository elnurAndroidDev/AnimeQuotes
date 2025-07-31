package com.isayevapps.presentation.screens.search.searchresultdetails

import com.isayevapps.domain.AnimeItem

data class SearchDetailsUiState(
    val isLoading: Boolean = true,
    val animeItem: AnimeItem? = null,
    val isFavorite: Boolean = false,
    val error: String? = null
)