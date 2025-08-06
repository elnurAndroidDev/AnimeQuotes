package com.isayevapps.presentation.screens.home.animetitles

import com.isayevapps.domain.AnimeItem
import com.isayevapps.presentation.utils.UiText

data class AnimeScreenUiState(
    val animeList: List<AnimeItem> = emptyList(),
    val isLoading: Boolean = false,
    val error: UiText? = null,
)