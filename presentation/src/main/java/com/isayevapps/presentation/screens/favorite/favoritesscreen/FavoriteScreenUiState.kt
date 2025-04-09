package com.isayevapps.presentation.screens.favorite.favoritesscreen

import com.isayevapps.domain.AnimeItem

data class FavoriteScreenUiState(
    val animeList: List<AnimeItem> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
)
