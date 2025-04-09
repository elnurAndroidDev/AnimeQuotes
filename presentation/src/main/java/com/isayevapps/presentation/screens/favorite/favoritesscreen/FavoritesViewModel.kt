package com.isayevapps.presentation.screens.favorite.favoritesscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.isayevapps.domain.usecase.GetFavoritesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getFavoritesUseCase: GetFavoritesUseCase,
): ViewModel() {
    private val _uiState = MutableStateFlow(FavoriteScreenUiState(isLoading = true))
    val uiState: StateFlow<FavoriteScreenUiState> = _uiState

    init {
        observeAnime()
    }

    private fun observeAnime() {
        viewModelScope.launch {
            getFavoritesUseCase().collect { animeList ->
                _uiState.value = _uiState.value.copy(animeList = animeList)
            }
        }
    }
}