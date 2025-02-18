package com.isayevapps.presentation.screens.animedetails

sealed class DetailIntent {
    data class LoadAnimeDetails(val animeId: Int) : DetailIntent()
}