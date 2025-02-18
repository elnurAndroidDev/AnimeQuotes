package com.isayevapps.presentation.screens.animedetails

import com.isayevapps.domain.AnimeItem

data class AnimeDetailsUiState(
    val animeItem: AnimeItem? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
)