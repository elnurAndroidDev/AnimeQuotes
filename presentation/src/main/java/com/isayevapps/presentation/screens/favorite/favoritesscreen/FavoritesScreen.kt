package com.isayevapps.presentation.screens.favorite.favoritesscreen

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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

    when {
        uiState.isLoading -> LoadingScreen(modifier)
        uiState.animeList.isEmpty() -> EmptyScreen(modifier)
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

@Composable
fun EmptyScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Outlined.FavoriteBorder,
            contentDescription = "Empty favorites",
            modifier = Modifier.size(128.dp),
            tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
        )
        Text(
            text = "No favorites yet",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f),
            modifier = Modifier.padding(top = 16.dp)
        )
    }
}