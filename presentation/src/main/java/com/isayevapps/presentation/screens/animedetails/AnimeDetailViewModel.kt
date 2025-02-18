package com.isayevapps.presentation.screens.animedetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.isayevapps.domain.usecase.GetAnimeDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AnimeDetailViewModel @Inject constructor(
    private val getAnimeDetailsUseCase: GetAnimeDetailsUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(AnimeDetailsUiState())
    val state: StateFlow<AnimeDetailsUiState> = _state

    fun processIntent(intent: DetailIntent) {
        when (intent) {
            is DetailIntent.LoadAnimeDetails -> loadAnimeDetails(intent.animeId)
        }
    }

    private fun loadAnimeDetails(animeId: Int) {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            getAnimeDetailsUseCase(animeId).collect { anime ->
                _state.value = _state.value.copy(
                    isLoading = false,
                    animeItem = anime
                )
            }
        }
    }
}