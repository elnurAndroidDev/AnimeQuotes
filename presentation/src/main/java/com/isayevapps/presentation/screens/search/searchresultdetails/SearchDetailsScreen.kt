package com.isayevapps.presentation.screens.search.searchresultdetails

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.isayevapps.domain.AnimeItem
import com.isayevapps.presentation.screens.common.AnimeDetailsContent
import com.isayevapps.presentation.screens.common.ErrorScreen
import com.isayevapps.presentation.screens.common.LoadingScreen
import com.isayevapps.presentation.screens.favorite.favoritedetails.FavoriteDetailsIntent


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SearchDetailsScreen(
    animeId: Int,
    keyPrefix: String,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    modifier: Modifier = Modifier
) {

    val viewModel = hiltViewModel<SearchDetailViewModel>()
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.processIntent(SearchDetailsIntent.LoadAnimeDetails(animeId))
    }

    when {
        state.isLoading -> LoadingScreen(modifier)
        state.error != null -> ErrorScreen(
            error = state.error!!,
            onRetry = { viewModel.processIntent(SearchDetailsIntent.LoadAnimeDetails(animeId)) },
            modifier = modifier
        )
        state.animeItem != null -> AnimeDetailsContent(
            anime = state.animeItem!!,
            keyPrefix = keyPrefix,
            sharedTransitionScope = sharedTransitionScope,
            animatedVisibilityScope = animatedVisibilityScope,
            isFavorite = state.isFavorite,
            onToggleFavorites = { viewModel.processIntent(SearchDetailsIntent.ToggleFavorite) },
        )
    }
}


@Preview(
    showSystemUi = true, showBackground = false,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL,
    backgroundColor = 0xFF262630, device = "spec:parent=pixel_5"
)
@Composable
private fun AnimeDetailsScreenPreview() {
}