package com.isayevapps.presentation.screens.favorite.favoritedetails

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.isayevapps.presentation.screens.common.AnimeDetailsContent
import com.isayevapps.presentation.screens.common.LoadingScreen


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun FavoriteDetailsScreen(
    animeId: Int,
    viewModel: FavoriteDetailViewModel,
    keyPrefix: String,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    modifier: Modifier = Modifier
) {

    LaunchedEffect(Unit) {
        viewModel.processIntent(FavoriteDetailsIntent.LoadAnimeDetails(animeId))
    }

    val state by viewModel.state.collectAsState()

    when {
        state.isLoading -> LoadingScreen(modifier)
        state.animeItem != null -> AnimeDetailsContent(
            anime = state.animeItem!!,
            keyPrefix = keyPrefix,
            sharedTransitionScope = sharedTransitionScope,
            animatedVisibilityScope = animatedVisibilityScope,
            isFavorite = state.isFavorite,
            onToggleFavorites = { viewModel.processIntent(FavoriteDetailsIntent.ToggleFavorite) },
            modifier = modifier.padding(16.dp)
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