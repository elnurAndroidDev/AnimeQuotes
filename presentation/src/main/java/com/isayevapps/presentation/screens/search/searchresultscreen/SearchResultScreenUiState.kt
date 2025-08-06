package com.isayevapps.presentation.screens.search.searchresultscreen

import com.isayevapps.domain.AnimeItem
import com.isayevapps.presentation.utils.UiText

data class SearchResultScreenUiState(
    val animeList: List<AnimeItem> = emptyList(),
    val isLoading: Boolean = false,
    val error: UiText? = null,
)