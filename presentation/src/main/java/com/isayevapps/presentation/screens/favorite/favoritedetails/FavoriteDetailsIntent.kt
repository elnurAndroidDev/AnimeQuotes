package com.isayevapps.presentation.screens.favorite.favoritedetails

sealed class FavoriteDetailsIntent {
    data class LoadAnimeDetails(val animeId: Int) : FavoriteDetailsIntent()
    data object ToggleFavorite : FavoriteDetailsIntent()
}