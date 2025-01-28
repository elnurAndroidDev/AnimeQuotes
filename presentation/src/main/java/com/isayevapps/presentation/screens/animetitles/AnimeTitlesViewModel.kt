package com.isayevapps.presentation.screens.animetitles

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.isayevapps.domain.cloud.usecases.GetAnimeTitlesUseCase
import com.isayevapps.domain.cloud.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimeTitlesViewModel @Inject constructor(
    private val getAnimeTitlesUseCase: GetAnimeTitlesUseCase
) : ViewModel() {

    var uiState by mutableStateOf<AnimeTitlesScreenUiState>(AnimeTitlesScreenUiState.Loading)
        private set

    init {
        load()
    }

    fun load() {
        viewModelScope.launch {
            val response = getAnimeTitlesUseCase()
            Log.d("AnimeTitlesViewModel", "Response")
            uiState = when (response) {
                is Resource.Success -> AnimeTitlesScreenUiState.Success(response.data!!)
                is Resource.Error -> AnimeTitlesScreenUiState.Error(response.message!!)
                is Resource.Loading -> AnimeTitlesScreenUiState.Loading
            }
        }
    }

}