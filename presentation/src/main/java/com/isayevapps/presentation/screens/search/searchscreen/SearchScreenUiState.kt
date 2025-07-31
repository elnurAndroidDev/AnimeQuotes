package com.isayevapps.presentation.screens.search.searchscreen

data class SearchScreenUiState(
    val query: String = "",
    val suggestions: List<String> = emptyList(),
    val showDialog: Boolean = false,
    val itemToDelete: String = ""
)