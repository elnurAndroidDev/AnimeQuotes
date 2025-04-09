package com.isayevapps.presentation.screens.favorite.favoritedetails

import com.isayevapps.domain.AnimeItem

data class FavoriteDetailsUiState(
    val animeItem: AnimeItem? = null,
    val isFavorite: Boolean = false,
    val isLoading: Boolean = false,
    val error: String? = null,
)