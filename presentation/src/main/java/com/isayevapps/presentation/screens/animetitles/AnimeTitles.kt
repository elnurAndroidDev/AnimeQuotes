package com.isayevapps.presentation.screens.animetitles

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.isayevapps.domain.AnimeItem
import com.isayevapps.domain.repository.LoadType

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun AnimeTitlesScreen(
    viewModel: AnimeTitlesViewModel,
    onTitleClick: (Int) -> Unit,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    modifier: Modifier = Modifier,
) {
    val isNetworkAvailable by viewModel.isNetworkAvailableFlow.collectAsState(initial = false)
    val uiState by viewModel.uiState.collectAsState()

    if (uiState.animeList.isEmpty() && uiState.isLoading) {
        LoadingScreen(modifier)
    }

    TitlesGrid(
        animeList = uiState.animeList,
        loadMore = {
            viewModel.loadAnime(LoadType.Append)
        },
        onTitleClick = onTitleClick,
        sharedTransitionScope = sharedTransitionScope,
        animatedVisibilityScope = animatedVisibilityScope,
        modifier = modifier
    )

//    LaunchedEffect(isNetworkAvailable) {
//        if (isNetworkAvailable) {
//            Log.d("XXX", "AnimeTitlesScreen: network available")
//            animePaging.retry()
//        }
//    }
//    val uiState = viewModel.uiState
//
//    when (uiState) {
//        is AnimeTitlesScreenUiState.Loading -> LoadingScreen(modifier = modifier)
//        is AnimeTitlesScreenUiState.Success -> TitlesGrid(
//            titles = uiState.titles,
//            onTitleClick = { title ->
//                navController.navigate(
//                    Screen.AnimeDetail.createRoute(title)
//                )
//            },
//            modifier = modifier
//        )
//
//        is AnimeTitlesScreenUiState.Error -> ErrorScreen(modifier = modifier)
//    }
}

@Composable
fun ErrorScreen(modifier: Modifier) {

}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.wrapContentSize(),
            color = MaterialTheme.colorScheme.primary
        )
    }
}


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun TitlesGrid(
    animeList: List<AnimeItem>,
    loadMore: () -> Unit = {},
    onTitleClick: (Int) -> Unit = {},
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

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun TitleGridPreview() {
    //TitlesGrid()
}