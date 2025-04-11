package com.isayevapps.presentation.screens.search.searchresultdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.isayevapps.domain.AnimeItem
import com.isayevapps.domain.cloud.Resource
import com.isayevapps.domain.usecase.AddToFavoritesUseCase
import com.isayevapps.domain.usecase.GetDetailsFromCloudUseCase
import com.isayevapps.domain.usecase.GetFavoriteDetailUseCase
import com.isayevapps.domain.usecase.IsFavoriteUseCase
import com.isayevapps.domain.usecase.RemoveFromFavoritesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SearchDetailViewModel @Inject constructor(
    private val getDetailsFromCloudUseCase: GetDetailsFromCloudUseCase,
    private val isFavoriteUseCase: IsFavoriteUseCase,
    private val addToFavoritesUseCase: AddToFavoritesUseCase,
    private val removeFromFavoritesUseCase: RemoveFromFavoritesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(SearchDetailsUiState(isLoading = true))
    val state: StateFlow<SearchDetailsUiState> = _state

    fun processIntent(intent: SearchDetailsIntent) {
        when (intent) {
            is SearchDetailsIntent.LoadAnimeDetails -> loadAnimeDetails(intent.animeId)
            is SearchDetailsIntent.ToggleFavorite -> toggleFavorite()
        }
    }

    private fun loadAnimeDetails(animeId: Int) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            val result = getDetailsFromCloudUseCase(animeId)
            when (result) {
                is Resource.Success -> {
                    _state.value = _state.value.copy(
                        animeItem = result.data,
                        isLoading = false
                    )
                }
                is Resource.Error -> {
                    _state.value = _state.value.copy(
                        error = result.error.toString(),
                        isLoading = false
                    )
                }
            }
            val isFavorite = isFavoriteUseCase(animeId)
            _state.value = _state.value.copy(isFavorite = isFavorite)

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