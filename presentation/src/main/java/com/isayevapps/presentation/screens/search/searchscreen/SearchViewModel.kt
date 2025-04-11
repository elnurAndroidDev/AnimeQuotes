package com.isayevapps.presentation.screens.search.searchscreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.isayevapps.domain.AnimeItem
import com.isayevapps.domain.cloud.Resource
import com.isayevapps.domain.usecase.GetSearchResultUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getSearchResultUseCase: GetSearchResultUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(SearchScreenUiState())
    val uiState: StateFlow<SearchScreenUiState> = _uiState

    private var currentPage = 1
    private var hasNextPage = false

    fun onQueryChanged(query: String) {
        _uiState.value = _uiState.value.copy(query = query)
    }

    fun getAnimeItemById(animeId: Int): AnimeItem {
        return _uiState.value.animeList.find { it.animeId == animeId }!!
    }

    fun loadMore() {
        if (!hasNextPage)
            return
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            val result = getSearchResultUseCase(_uiState.value.query, ++currentPage)
            when (result) {
                is Resource.Success -> {
                    _uiState.value = _uiState.value.copy(
                        animeList = _uiState.value.animeList + result.data.first,
                        isLoading = false
                    )
                    hasNextPage = result.data.second
                }

                is Resource.Error -> {
                    _uiState.value =
                        _uiState.value.copy(error = result.error.toString(), isLoading = false)
                }
            }

        }
    }

    fun searchAnime() {
        if (_uiState.value.query.isBlank())
            return
        viewModelScope.launch {
            Log.d("SearchViewModel", "searchAnime: started")
            _uiState.value = _uiState.value.copy(isLoading = true)
            currentPage = 1
            val result = getSearchResultUseCase(_uiState.value.query, 1)
            when (result) {
                is Resource.Success -> {
                    _uiState.value =
                        _uiState.value.copy(animeList = result.data.first, isLoading = false)
                    hasNextPage = result.data.second
                    Log.d("SearchViewModel", "searchAnime: ${uiState.value.animeList.size}")
                }

                is Resource.Error -> {
                    _uiState.value =
                        _uiState.value.copy(error = result.error.toString(), isLoading = false)
                }
            }
        }
    }

}