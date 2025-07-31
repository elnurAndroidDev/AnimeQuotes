package com.isayevapps.presentation.screens.search.searchscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.isayevapps.domain.usecase.DeleteSearchQueryUseCase
import com.isayevapps.domain.usecase.GetFullSearchHistoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@Suppress("OPT_IN_USAGE")
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getFullSearchHistoryUseCase: GetFullSearchHistoryUseCase,
    private val deleteSearchQueryUseCase: DeleteSearchQueryUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(SearchScreenUiState())
    val uiState: StateFlow<SearchScreenUiState> = _uiState

    init {
        _uiState
            .map { it.query }
            .distinctUntilChanged()
            .flatMapLatest { query ->
                getFullSearchHistoryUseCase().map { list ->
                    if (query.isBlank()) list
                    else list.filter { it.contains(query, ignoreCase = true) }
                }
            }
            .onEach { suggestions ->
                _uiState.update { it.copy(suggestions = suggestions) }
            }
            .launchIn(viewModelScope)
    }

    fun onQueryChanged(query: String) {
        _uiState.update { it.copy(query = query) }
    }

    fun showDeleteDialog(item: String) {
        _uiState.update { it.copy(showDialog = true, itemToDelete = item) }
    }

    fun hideDeleteDialog() {
        _uiState.update { it.copy(showDialog = false, itemToDelete = "") }
    }

    fun deleteQuery() = viewModelScope.launch {
        deleteSearchQueryUseCase(_uiState.value.itemToDelete)
        hideDeleteDialog()
    }

}