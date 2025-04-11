package com.isayevapps.presentation.screens.search.searchresultdetails

import com.isayevapps.domain.AnimeItem

sealed class SearchDetailsIntent {
    data class LoadAnimeDetails(val animeId: Int) : SearchDetailsIntent()
    data object ToggleFavorite : SearchDetailsIntent()
}