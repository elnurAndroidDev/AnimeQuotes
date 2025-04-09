package com.isayevapps.presentation.screens.common

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.isayevapps.domain.AnimeItem
import com.isayevapps.presentation.screens.home.animetitles.TitleItem

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun AnimeVerticalGrid(
    animeList: List<AnimeItem>,
    state: LazyGridState,
    columns: GridCells,
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier,
    onTitleClick: (Int) -> Unit = {},
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
) {
    LazyVerticalGrid(
        state = state,
        columns = columns,
        modifier = modifier,
        contentPadding = contentPadding
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