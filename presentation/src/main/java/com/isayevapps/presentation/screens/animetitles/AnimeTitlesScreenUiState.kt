package com.isayevapps.presentation.screens.animetitles

import com.isayevapps.domain.AnimeItem

interface AnimeTitlesScreenUiState {
    data class Success(val titles: List<AnimeItem>) : AnimeTitlesScreenUiState
    data class Error(val error: String) : AnimeTitlesScreenUiState
    object Loading : AnimeTitlesScreenUiState
}