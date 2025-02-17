package com.isayevapps.presentation.screens.animetitles

import com.isayevapps.domain.AnimeItem

data class AnimeScreenUiState(
    val animeList: List<AnimeItem> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
)