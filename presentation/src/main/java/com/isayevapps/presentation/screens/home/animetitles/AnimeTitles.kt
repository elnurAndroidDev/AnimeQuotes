package com.isayevapps.presentation.screens.home.animetitles

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.isayevapps.domain.AnimeItem
import com.isayevapps.domain.repository.LoadType
import com.isayevapps.presentation.screens.common.AnimeVerticalGrid
import com.isayevapps.presentation.screens.common.LoadingScreen
import com.isayevapps.presentation.R

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun AnimeTitlesScreen(
    onTitleClick: (Int) -> Unit,
    keyPrefix: String,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    modifier: Modifier = Modifier,
) {
    val viewModel = hiltViewModel<AnimeTitlesViewModel>()
    val isNetworkAvailable by viewModel.isNetworkAvailableFlow.collectAsState(initial = false)
    val uiState by viewModel.uiState.collectAsState()

    when {
        uiState.isLoading -> LoadingScreen(modifier)
        uiState.error != null -> ErrorScreen(
            error = uiState.error!!,
            onRetry = { viewModel.loadAnime(LoadType.Refresh) },
            modifier = modifier
        )
        else -> {
            TitlesGrid(
                animeList = uiState.animeList,
                loadMore = { viewModel.loadAnime(LoadType.Append) },
                onTitleClick = onTitleClick,
                keyPrefix = keyPrefix,
                sharedTransitionScope = sharedTransitionScope,
                animatedVisibilityScope = animatedVisibilityScope,
                modifier = modifier
            )
        }
    }
}

@Composable
fun ErrorScreen(error: String, onRetry: () -> Unit, modifier: Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = error,
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.error
            )
            Spacer(modifier = Modifier.height(8.dp))
            RetryButton(onRetry = onRetry)
        }
    }
}

@Composable
fun RetryButton(onRetry: () -> Unit) {
    OutlinedButton(onClick = onRetry) {
        Text(text = stringResource(R.string.retry))
    }
}


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun TitlesGrid(
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

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun TitleGridPreview() {
    //TitlesGrid()
}