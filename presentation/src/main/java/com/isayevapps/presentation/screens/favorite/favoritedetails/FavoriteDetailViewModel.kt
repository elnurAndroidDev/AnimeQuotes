package com.isayevapps.presentation.screens.favorite.favoritedetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.isayevapps.domain.usecase.AddToFavoritesUseCase
import com.isayevapps.domain.usecase.GetAnimeDetailsUseCase
import com.isayevapps.domain.usecase.GetFavoriteDetailUseCase
import com.isayevapps.domain.usecase.IsFavoriteUseCase
import com.isayevapps.domain.usecase.RemoveFromFavoritesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FavoriteDetailViewModel @Inject constructor(
    private val getFavoriteDetailsUseCase: GetFavoriteDetailUseCase,
    private val isFavoriteUseCase: IsFavoriteUseCase,
    private val addToFavoritesUseCase: AddToFavoritesUseCase,
    private val removeFromFavoritesUseCase: RemoveFromFavoritesUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(FavoriteDetailsUiState())
    val state: StateFlow<FavoriteDetailsUiState> = _state

    fun processIntent(intent: FavoriteDetailsIntent) {
        when (intent) {
            is FavoriteDetailsIntent.LoadAnimeDetails -> loadAnimeDetails(intent.animeId)
            is FavoriteDetailsIntent.ToggleFavorite -> toggleFavorite()
        }
    }

    private fun loadAnimeDetails(animeId: Int) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            val isFavorite = isFavoriteUseCase(animeId)
            _state.value = _state.value.copy(isFavorite = isFavorite)
            val anime = getFavoriteDetailsUseCase(animeId)
            _state.value = _state.value.copy(
                isLoading = false,
                animeItem = anime
            )
        }
    }

    private fun toggleFavorite() {
        viewModelScope.launch {
            val isFavorite = _state.value.isFavorite
            if (isFavorite)
                removeFromFavoritesUseCase(_state.value.animeItem!!.animeId)
            else
                addToFavoritesUseCase(_state.value.animeItem!!)
            _state.value = _state.value.copy(isFavorite = !isFavorite)
        }
    }
}