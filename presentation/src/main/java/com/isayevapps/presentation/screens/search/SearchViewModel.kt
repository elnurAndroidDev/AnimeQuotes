package com.isayevapps.presentation.screens.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.isayevapps.domain.AnimeItem
import com.isayevapps.domain.cloud.Resource
import com.isayevapps.domain.repository.SearchRepository
import com.isayevapps.domain.usecase.GetSearchResultUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val getSearchResultUseCase: GetSearchResultUseCase) : ViewModel() {

    private val _uiState = MutableStateFlow(SearchScreenUiState())
    val uiState: StateFlow<SearchScreenUiState> = _uiState

    fun onQueryChanged(query: String) {
        _uiState.value = _uiState.value.copy(query = query)
    }

    fun searchAnime(query: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            val result = getSearchResultUseCase(query, 1)
            when (result) {
                is Resource.Success -> {
                    _uiState.value = _uiState.value.copy(animeList = result.data, isLoading = false)
                }
                is Resource.Error -> {
                    _uiState.value = _uiState.value.copy(error = result.error.toString(), isLoading = false)
                }
            }
        }
    }

}