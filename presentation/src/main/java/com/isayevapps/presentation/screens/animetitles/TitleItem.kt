package com.isayevapps.presentation.screens.animetitles

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import coil3.request.placeholder
import com.isayevapps.presentation.R

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun TitleItem(
    animeId: Int,
    title: String,
    imgUrl: String,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
    ) {
        with(sharedTransitionScope) {
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(imgUrl)
                    .crossfade(true)
                    .placeholder(R.drawable.placeholder_img)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .sharedElement(
                        state = sharedTransitionScope.rememberSharedContentState(key = animeId),
                        animatedVisibilityScope = animatedVisibilityScope
                    )
                    .fillMaxWidth()
                    .aspectRatio(0.6f)
                    .clip(RoundedCornerShape(16.dp))
                    .drawWithContent {
                        drawContent()
                        drawRect(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    Color.Black.copy(alpha = 0.2f),
                                    Color.Black
                                )
                            ),
                            topLeft = Offset.Zero,
                            size = Size(size.width, size.height)
                        )
                    }
            )

            Text(
                text = title,
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                color = Color.White,
                modifier = Modifier
                    .sharedBounds(
                        sharedContentState = sharedTransitionScope.rememberSharedContentState(key = "title $animeId"),
                        //boundsTransform = {_,_ -> tween(3000)},
                        animatedVisibilityScope = animatedVisibilityScope
                    )
                    .align(Alignment.BottomCenter)
                    .padding(horizontal = 8.dp, vertical = 16.dp)
            )
        }
    }
}


@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun TitlePreview() {
    //TitleItem(imgUrl = "img", title = "title", modifier = Modifier.width(200.dp))
}