package com.isayevapps.presentation.screens.animetitles

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.isayevapps.domain.AnimeItem
import com.isayevapps.domain.cloud.Resource
import com.isayevapps.domain.usecase.GetAnimeUseCase
import com.isayevapps.domain.utils.NetworkMonitor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.retry
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimeTitlesViewModel @Inject constructor(
    getAnimeUseCase: GetAnimeUseCase,
    private val networkMonitor: NetworkMonitor
) : ViewModel() {

    var uiState by mutableStateOf<AnimeTitlesScreenUiState>(AnimeTitlesScreenUiState.Loading)
        private set

    val animeTitles: Flow<PagingData<AnimeItem>> = getAnimeUseCase().cachedIn(viewModelScope)

    val isNetworkAvailableFlow = networkMonitor.observe()

//    fun load() {
//        viewModelScope.launch {
//            val response = getAnimeUseCase()
//            uiState = when (response) {
//                is Resource.Success -> AnimeTitlesScreenUiState.Success(response.data)
//                is Resource.Error -> AnimeTitlesScreenUiState.Error(response.error.message ?: "Unknown error")
//                is Resource.Loading -> AnimeTitlesScreenUiState.Loading
//            }
//        }
//    }

}