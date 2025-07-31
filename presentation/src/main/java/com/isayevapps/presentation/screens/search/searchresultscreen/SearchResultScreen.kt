package com.isayevapps.presentation.screens.search.searchresultscreen

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.isayevapps.domain.AnimeItem
import com.isayevapps.domain.repository.LoadType
import com.isayevapps.presentation.screens.common.AnimeVerticalGrid
import com.isayevapps.presentation.screens.common.EmptyScreen
import com.isayevapps.presentation.screens.common.LoadingScreen

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SearchResultScreen(
    modifier: Modifier = Modifier,
    onTitleClick: (Int) -> Unit = {},
    keyPrefix: String,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
) {
    val viewModel = hiltViewModel<SearchResultViewModel>()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when {
        uiState.isLoading -> LoadingScreen(modifier)
        uiState.animeList.isEmpty() -> EmptyScreen(
            icon = Icons.Outlined.Close,
            contentDescription = "No Results",
            text = "No results found",
            modifier = modifier
        )

        else -> SearchResultGrid(
            animeList = uiState.animeList,
            loadMore = { viewModel.load(LoadType.Append) },
            onTitleClick = onTitleClick,
            keyPrefix = keyPrefix,
            sharedTransitionScope = sharedTransitionScope,
            animatedVisibilityScope = animatedVisibilityScope,
            modifier = modifier
        )
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SearchResultGrid(
    animeList: List<AnimeItem>,
    loadMore: () -> Unit = {},
    onTitleClick: (Int) -> Unit = {},
    keyPrefix: String,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    modifier: Modifier = Modifier
) {
    val lazyGridState = rememberLazyGridState()
    val shouldLoadMore by remember {
        derivedStateOf {
            val lastVisibleItemIndex =
                lazyGridState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0
            val totalItemsCount = lazyGridState.layoutInfo.totalItemsCount
            totalItemsCount > 0 && lastVisibleItemIndex >= totalItemsCount - 1
        }
    }

    LaunchedEffect(shouldLoadMore) {
        if (shouldLoadMore) {
            loadMore()
        }
    }

    AnimeVerticalGrid(
        state = lazyGridState,
        animeList = animeList,
        onTitleClick = onTitleClick,
        columns = GridCells.Adaptive(150.dp),
        modifier = modifier,
        contentPadding = PaddingValues(4.dp),
        keyPrefix = keyPrefix,
        sharedTransitionScope = sharedTransitionScope,
        animatedVisibilityScope = animatedVisibilityScope
    )
}
