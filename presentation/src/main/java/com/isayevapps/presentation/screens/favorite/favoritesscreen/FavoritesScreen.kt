package com.isayevapps.presentation.screens.favorite.favoritesscreen

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.isayevapps.domain.AnimeItem
import com.isayevapps.domain.repository.LoadType
import com.isayevapps.presentation.screens.home.animetitles.AnimeTitlesViewModel
import com.isayevapps.presentation.screens.home.animetitles.LoadingScreen
import com.isayevapps.presentation.screens.home.animetitles.TitleItem
import com.isayevapps.presentation.screens.home.animetitles.TitlesGrid

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun FavoritesScreen(
    viewModel: FavoritesViewModel,
    onTitleClick: (Int) -> Unit,
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
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    modifier: Modifier = Modifier
) {
    val lazyGridState = rememberLazyGridState()

    LazyVerticalGrid(
        state = lazyGridState,
        columns = GridCells.Adaptive(150.dp),
        modifier = modifier,
        contentPadding = PaddingValues(4.dp)
    ) {
        items(animeList, key = { anime -> anime.animeId }) { anime ->
            TitleItem(
                animeId = anime.animeId,
                title = anime.title,
                imgUrl = anime.imgUrl,
                sharedTransitionScope = sharedTransitionScope,
                animatedVisibilityScope = animatedVisibilityScope,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
                    .clickable {
                        onTitleClick(anime.animeId)
                    }
            )
        }
    }
}