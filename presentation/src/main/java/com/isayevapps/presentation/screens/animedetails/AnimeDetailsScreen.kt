package com.isayevapps.presentation.screens.animedetails

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import coil3.request.placeholder
import com.isayevapps.domain.AnimeItem
import com.isayevapps.presentation.R
import com.isayevapps.presentation.screens.animedetails.components.InfoBox
import com.isayevapps.presentation.theme.InfoBoxColor
import com.isayevapps.presentation.theme.Stroke


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun AnimeDetailsScreen(
    animeId: Int,
    viewModel: AnimeDetailViewModel,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    modifier: Modifier = Modifier
) {

    LaunchedEffect(Unit) {
        viewModel.processIntent(DetailIntent.LoadAnimeDetails(animeId))
    }

    val state by viewModel.state.collectAsState()

    when {
        state.isLoading -> {}//LoadingScreen()
        state.error != null -> {}//ErrorScreen(state.error, onRetry = { viewModel.processIntent(DetailIntent.Retry) })
        state.animeItem != null -> AnimeDetailsContent(
            state.animeItem!!,
            sharedTransitionScope,
            animatedVisibilityScope
        )
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun AnimeDetailsContent(
    anime: AnimeItem,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = modifier
            .verticalScroll(scrollState)
            .fillMaxSize()
            .padding(vertical = 16.dp, horizontal = 16.dp)
    ) {
        with(sharedTransitionScope) {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(330.dp)
            ) {
                val (img, info) = createRefs()
                AsyncImage(
                    model = ImageRequest.Builder(context = LocalContext.current)
                        .data(anime.imgUrl)
                        .crossfade(true)
                        .placeholder(R.drawable.placeholder_img)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .sharedElement(
                            state = sharedTransitionScope.rememberSharedContentState(key = anime.animeId),
                            animatedVisibilityScope = animatedVisibilityScope
                        )
                        .aspectRatio(0.7f)
                        .clip(RoundedCornerShape(16.dp))
                        .constrainAs(img) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                            bottom.linkTo(parent.bottom)
                        }
                )
                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .constrainAs(info) {
                            top.linkTo(img.top)
                            bottom.linkTo(img.bottom)
                            end.linkTo(parent.end)
                        },
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    InfoBox(ico = R.drawable.genre_ico, type = "Type", text = anime.type)
                    InfoBox(
                        ico = R.drawable.episodes_ico,
                        type = "Episodes",
                        text = anime.episodes.toString()
                    )
                    InfoBox(
                        ico = R.drawable.rating_ico,
                        type = "Rating",
                        text = "${anime.score}/10"
                    )
                }
            }
            Text(
                text = anime.title,
                fontSize = 24.sp,
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .sharedBounds(
                        sharedContentState = sharedTransitionScope.rememberSharedContentState(key = "title ${anime.animeId}"),
                        //boundsTransform = {_,_ -> tween(3000)},
                        animatedVisibilityScope = animatedVisibilityScope
                    )
                    .padding(vertical = 16.dp)
            )
        }
        Divider(color = Stroke)
        Text(
            text = "Synopsis",
            fontSize = 18.sp,
            color = MaterialTheme.colorScheme.onBackground,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(vertical = 16.dp)
        )
        Text(
            text = anime.synopsis,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth()
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