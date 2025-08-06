package com.isayevapps.presentation.screens.search.searchresultdetails

import com.isayevapps.domain.AnimeItem
import com.isayevapps.presentation.utils.UiText

data class SearchDetailsUiState(
    val isLoading: Boolean = true,
    val animeItem: AnimeItem? = null,
    val isFavorite: Boolean = false,
    val error: UiText? = null
)