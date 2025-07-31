package com.isayevapps.presentation.screens.search.searchscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.isayevapps.presentation.screens.common.components.SearchBar
import com.isayevapps.presentation.screens.common.components.SearchHistoryList

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    onSearchClick: (String) -> Unit = {}
) {
    val viewModel = hiltViewModel<SearchViewModel>()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(modifier = modifier) {
        SearchBar(
            query = uiState.query,
            onQueryChanged = { viewModel.onQueryChanged(it) },
            onSearchClick = onSearchClick
        )
        SearchHistoryList(
            historyList = uiState.suggestions,
            onClick = onSearchClick,
            onDelete = {
                viewModel.showDeleteDialog(it)
            },
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp, vertical = 4.dp)
        )
    }

    if (uiState.showDialog && uiState.itemToDelete.isNotEmpty()) {
        AlertDialog(
            onDismissRequest = viewModel::hideDeleteDialog,
            title = { Text("Delete Item") },
            text = { Text("Are you sure you want to delete '${uiState.itemToDelete}' from search history?") },
            confirmButton = {
                TextButton(
                    onClick = viewModel::deleteQuery
                ) {
                    Text("Delete")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = viewModel::hideDeleteDialog
                ) {
                    Text("Cancel")
                }
            }
        )
    }
}
