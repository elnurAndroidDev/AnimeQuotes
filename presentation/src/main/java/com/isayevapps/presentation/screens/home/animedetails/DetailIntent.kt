package com.isayevapps.presentation.screens.home.animedetails

import com.isayevapps.domain.AnimeItem

sealed class DetailIntent {
    data class LoadAnimeDetails(val animeId: Int) : DetailIntent()
    data object ToggleFavorite : DetailIntent()
}