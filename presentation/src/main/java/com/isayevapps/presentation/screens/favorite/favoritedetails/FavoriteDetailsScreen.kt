package com.isayevapps.presentation.screens.favorite.favoritedetails

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
import com.isayevapps.presentation.screens.common.AnimeDetailsContent


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun FavoriteDetailsScreen(
    animeId: Int,
    viewModel: FavoriteDetailViewModel,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    modifier: Modifier = Modifier
) {

    LaunchedEffect(Unit) {
        viewModel.processIntent(FavoriteDetailsIntent.LoadAnimeDetails(animeId))
    }

    val state by viewModel.state.collectAsState()

    when {
        state.isLoading -> {}//LoadingScreen()
        state.error != null -> {}//ErrorScreen(state.error, onRetry = { viewModel.processIntent(DetailIntent.Retry) })
        state.animeItem != null -> AnimeDetailsContent(
            state.animeItem!!,
            sharedTransitionScope,
            animatedVisibilityScope,
            isFavorite = state.isFavorite,
            onToggleFavorites = { viewModel.processIntent(FavoriteDetailsIntent.ToggleFavorite) },
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