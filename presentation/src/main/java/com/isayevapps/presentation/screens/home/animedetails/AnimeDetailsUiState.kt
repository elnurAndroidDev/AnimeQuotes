package com.isayevapps.presentation.screens.home.animedetails

import com.isayevapps.domain.AnimeItem

data class AnimeDetailsUiState(
    val animeItem: AnimeItem? = null,
    val isFavorite: Boolean = false,
    val isLoading: Boolean = false,
    val error: String? = null,
)