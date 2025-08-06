package com.isayevapps.presentation.screens.search.searchresultdetails

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.isayevapps.presentation.screens.common.AnimeDetailsContent
import com.isayevapps.presentation.screens.common.ErrorScreen
import com.isayevapps.presentation.screens.common.LoadingScreen


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SearchDetailsScreen(
    keyPrefix: String,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    modifier: Modifier = Modifier
) {

    val viewModel = hiltViewModel<SearchDetailViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()

    when {
        state.isLoading -> LoadingScreen(modifier)
        state.error != null -> ErrorScreen(
            error = state.error!!.asString(LocalContext.current),
            onRetry = { viewModel.processIntent(SearchDetailsIntent.LoadAnimeDetails) },
            modifier = modifier
        )
        state.animeItem != null -> AnimeDetailsContent(
            anime = state.animeItem!!,
            keyPrefix = keyPrefix,
            sharedTransitionScope = sharedTransitionScope,
            animatedVisibilityScope = animatedVisibilityScope,
            isFavorite = state.isFavorite,
            onToggleFavorites = { viewModel.processIntent(SearchDetailsIntent.ToggleFavorite) },
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