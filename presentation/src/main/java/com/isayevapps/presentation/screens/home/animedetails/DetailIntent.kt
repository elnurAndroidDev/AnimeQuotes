package com.isayevapps.presentation.screens.home.animedetails

sealed class DetailIntent {
    data class LoadAnimeDetails(val animeId: Int) : DetailIntent()
    data object ToggleFavorite : DetailIntent()
}