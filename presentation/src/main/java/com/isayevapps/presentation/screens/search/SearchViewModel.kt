package com.isayevapps.presentation.screens.search

import androidx.lifecycle.ViewModel
import com.isayevapps.domain.repository.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val searchRepository: SearchRepository) : ViewModel() {

}