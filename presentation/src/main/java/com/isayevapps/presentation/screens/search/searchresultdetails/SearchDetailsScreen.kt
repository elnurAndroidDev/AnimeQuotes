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
import com.isayevapps.domain.AnimeItem
import com.isayevapps.presentation.screens.common.AnimeDetailsContent
import com.isayevapps.presentation.screens.favorite.favoritedetails.FavoriteDetailsIntent


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SearchDetailsScreen(
    animeId: Int,
    viewModel: SearchDetailViewModel,
    keyPrefix: String,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    modifier: Modifier = Modifier
) {

    LaunchedEffect(Unit) {
        viewModel.processIntent(SearchDetailsIntent.LoadAnimeDetails(animeId))
    }

    val state by viewModel.state.collectAsState()

    when {
        state.isLoading -> {}//LoadingScreen()
        state.error != null -> {}//ErrorScreen(state.error, onRetry = { viewModel.processIntent(DetailIntent.Retry) })
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