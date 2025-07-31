package com.isayevapps.presentation.screens.search.searchresultdetails

sealed class SearchDetailsIntent {
    data object LoadAnimeDetails : SearchDetailsIntent()
    data object ToggleFavorite : SearchDetailsIntent()
}