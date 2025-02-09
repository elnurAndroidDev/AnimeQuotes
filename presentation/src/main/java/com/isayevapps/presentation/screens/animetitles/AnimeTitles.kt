package com.isayevapps.presentation.screens.animetitles

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.isayevapps.domain.AnimeItem
import com.isayevapps.presentation.Screen

@Composable
fun AnimeTitlesScreen(
    viewModel: AnimeTitlesViewModel,
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    val isNetworkAvailable by viewModel.isNetworkAvailableFlow.collectAsState(initial = false)
    val animePaging = viewModel.animeTitles.collectAsLazyPagingItems()
    val lazyGridState = rememberLazyGridState()

    TitlesGrid(
        animeList = animePaging,
        lazyGridState = lazyGridState,
        onTitleClick = { title ->
            navController.navigate(
                Screen.AnimeDetail.createRoute(title)
            )
        },
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
fun LoadingScreen(modifier: Modifier) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.wrapContentSize(),
            color = MaterialTheme.colorScheme.primary
        )
    }
}


@Composable
fun TitlesGrid(
    animeList: LazyPagingItems<AnimeItem>,
    lazyGridState: LazyGridState,
    onTitleClick: (String) -> Unit = {},
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        state = lazyGridState,
        columns = GridCells.Adaptive(150.dp),
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(4.dp)
    ) {
        items(animeList.itemCount, key = { index -> animeList[index]!!.animeId}) { index ->
            val anime = animeList[index]!!
            TitleItem(
                title = anime.title,
                imgUrl = anime.imgUrl,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
                    .clickable {
                        onTitleClick(anime.title)
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