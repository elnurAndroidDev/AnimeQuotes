package com.isayevapps.presentation.screens.animetitles

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.isayevapps.domain.cloud.AnimeCloudRepository
import com.isayevapps.domain.cloud.Resource
import kotlinx.coroutines.launch

class AnimeTitlesViewModel(
    private val repository: AnimeCloudRepository
) : ViewModel() {

    var uiState by mutableStateOf<AnimeTitlesScreenUiState>(AnimeTitlesScreenUiState.Loading)
        private set

    init {
        load()
    }

    fun load() {
        viewModelScope.launch {
            val response = repository.getAnime()
            uiState = when (response) {
                is Resource.Success -> AnimeTitlesScreenUiState.Success(response.data!!)
                is Resource.Error -> AnimeTitlesScreenUiState.Error(response.message!!)
                is Resource.Loading -> AnimeTitlesScreenUiState.Loading
            }
        }
    }

}