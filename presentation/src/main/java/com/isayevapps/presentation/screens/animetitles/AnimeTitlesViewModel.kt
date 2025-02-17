package com.isayevapps.presentation.screens.animetitles

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.isayevapps.domain.AnimeItem
import com.isayevapps.domain.repository.LoadResult
import com.isayevapps.domain.repository.LoadType
import com.isayevapps.domain.usecase.GetAnimeUseCase
import com.isayevapps.domain.usecase.LoadAnimeUseCase
import com.isayevapps.domain.utils.NetworkMonitor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimeTitlesViewModel @Inject constructor(
    private val getAnimeUseCase: GetAnimeUseCase,
    private val loadAnimeUseCase: LoadAnimeUseCase,
    private val networkMonitor: NetworkMonitor
) : ViewModel() {

    private val _uiState = MutableStateFlow(AnimeScreenUiState(isLoading = true))
    val uiState: StateFlow<AnimeScreenUiState> = _uiState

    val isNetworkAvailableFlow = networkMonitor.observe()

    init {
        loadAnime(LoadType.Refresh)
        observeAnime()
    }

    private fun observeAnime() {
        viewModelScope.launch {
            getAnimeUseCase().collect { animeList ->
                _uiState.value = _uiState.value.copy(animeList = animeList)
            }
        }
    }

    fun loadAnime(loadType: LoadType) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            val result = loadAnimeUseCase(loadType)
            if (result is LoadResult.Error) {
                _uiState.value =
                    _uiState.value.copy(isLoading = false, error = result.error.toString())
            }
            _uiState.value = _uiState.value.copy(isLoading = false)
        }
    }

}