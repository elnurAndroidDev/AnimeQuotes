package com.isayevapps.presentation.screens.search.searchresultscreen

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.isayevapps.domain.repository.LoadType
import com.isayevapps.domain.result.Result
import com.isayevapps.domain.usecase.GetSearchResultUseCase
import com.isayevapps.domain.usecase.InsertSearchQueryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchResultViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getSearchResultUseCase: GetSearchResultUseCase,
    private val insertSearchQueryUseCase: InsertSearchQueryUseCase
) : ViewModel() {
    val query: String = checkNotNull(savedStateHandle["query"])

    private val _uiState = MutableStateFlow(SearchResultScreenUiState())
    val uiState = _uiState
        .onStart { load(LoadType.Refresh) }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            SearchResultScreenUiState()
        )

    private var currentPage = 1
    private var hasNextPage = true

    fun load(loadType: LoadType) {
        if (!hasNextPage)
            return
        viewModelScope.launch {
            if (loadType == LoadType.Refresh)
                _uiState.update { it.copy(isLoading = true) }
            when (val result = getSearchResultUseCase(query, currentPage++)) {
                is Result.Success -> {
                    val animeList = result.data.first.distinctBy { it.animeId }
                    _uiState.update {
                        it.copy(
                            animeList = _uiState.value.animeList + animeList,
                            isLoading = false
                        )
                    }
                    insertSearchQueryUseCase(query)
                    hasNextPage = result.data.second
                }
                is Result.Error -> {
                    _uiState.update {
                        it.copy(error = result.error.toString(), isLoading = false)
                    }
                }
            }

        }
    }
}