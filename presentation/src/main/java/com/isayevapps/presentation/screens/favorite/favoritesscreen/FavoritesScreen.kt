package com.isayevapps.presentation.screens.favorite.favoritesscreen

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.isayevapps.domain.AnimeItem
import com.isayevapps.presentation.screens.common.AnimeVerticalGrid
import com.isayevapps.presentation.screens.common.EmptyScreen
import com.isayevapps.presentation.screens.common.LoadingScreen

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun FavoritesScreen(
    onTitleClick: (Int) -> Unit,
    keyPrefix: String,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    modifier: Modifier = Modifier,
) {
    val viewModel = hiltViewModel<FavoritesViewModel>()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when {
        uiState.isLoading -> LoadingScreen(modifier)
        uiState.animeList.isEmpty() -> EmptyScreen(
            icon = Icons.Outlined.FavoriteBorder,
            contentDescription = "Favorite Icon",
            text = "No favorites yet",
            modifier = modifier
        )
        else -> {
            FavoritesGrid(
                animeList = uiState.animeList,
                onTitleClick = onTitleClick,
                keyPrefix = keyPrefix,
                sharedTransitionScope = sharedTransitionScope,
                animatedVisibilityScope = animatedVisibilityScope,
                modifier = modifier
            )
        }
    }

}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun FavoritesGrid(
    modifier: Modifier = Modifier,
    animeList: List<AnimeItem>,
    onTitleClick: (Int) -> Unit = {},
    keyPrefix: String,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope
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