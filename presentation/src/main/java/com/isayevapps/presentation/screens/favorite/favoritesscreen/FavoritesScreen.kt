package com.isayevapps.presentation.screens.favorite.favoritesscreen

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.isayevapps.domain.AnimeItem
import com.isayevapps.presentation.screens.common.AnimeVerticalGrid
import com.isayevapps.presentation.screens.common.LoadingScreen

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun FavoritesScreen(
    viewModel: FavoritesViewModel,
    onTitleClick: (Int) -> Unit,
    keyPrefix: String,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    modifier: Modifier = Modifier,
) {
    val uiState by viewModel.uiState.collectAsState()

    if (uiState.animeList.isEmpty() && uiState.isLoading) {
        LoadingScreen(modifier)
    }

    FavoritesGrid(
        animeList = uiState.animeList,
        onTitleClick = onTitleClick,
        keyPrefix = keyPrefix,
        sharedTransitionScope = sharedTransitionScope,
        animatedVisibilityScope = animatedVisibilityScope,
        modifier = modifier
    )

}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun FavoritesGrid(
    animeList: List<AnimeItem>,
    onTitleClick: (Int) -> Unit = {},
    keyPrefix: String,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    modifier: Modifier = Modifier
) {
    val lazyGridState = rememberLazyGridState()

    AnimeVerticalGrid(
        state = lazyGridState,
        animeList = animeList,
        onTitleClick = onTitleClick,
        columns = GridCells.Adaptive(150.dp),
        contentPadding = PaddingValues(4.dp),
        keyPrefix = keyPrefix,
        sharedTransitionScope = sharedTransitionScope,
        animatedVisibilityScope = animatedVisibilityScope,
        modifier = modifier,
    )
}