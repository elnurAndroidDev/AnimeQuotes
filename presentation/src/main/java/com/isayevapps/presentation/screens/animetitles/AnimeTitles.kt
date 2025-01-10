package com.isayevapps.presentation.screens.animetitles

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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.isayevapps.domain.cloud.AnimeItem
import com.isayevapps.presentation.Screen

@Composable
fun AnimeTitlesScreen(
    uiState: AnimeTitlesScreenUiState,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    when (uiState) {
        is AnimeTitlesScreenUiState.Loading -> LoadingScreen(modifier = modifier)
        is AnimeTitlesScreenUiState.Success -> TitlesGrid(
            titles = uiState.titles,
            onTitleClick = { title ->
                navController.navigate(
                    Screen.AnimeDetail.createRoute(title)
                )
            },
            modifier = modifier
        )

        is AnimeTitlesScreenUiState.Error -> ErrorScreen(modifier = modifier)
    }
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
    titles: List<AnimeItem> = listOf(),
    onTitleClick: (String) -> Unit = {},
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(4.dp)
    ) {
        items(titles) { anime ->
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
    TitlesGrid()
}