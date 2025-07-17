package com.isayevapps.presentation.screens.home

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.isayevapps.presentation.screens.home.animedetails.AnimeDetailsScreen
import com.isayevapps.presentation.screens.home.animetitles.AnimeTitlesScreen
import kotlinx.serialization.Serializable


@Serializable
object HomeNavGraph

@Serializable
object TopAnime

@Serializable
data class AnimeDetail(val animeId: Int)

@OptIn(ExperimentalSharedTransitionApi::class)
fun NavGraphBuilder.homeNavigation(
    navController: NavController,
    sharedTransitionScope: SharedTransitionScope
) {
    navigation<HomeNavGraph>(startDestination = TopAnime) {
        composable<TopAnime> {
            AnimeTitlesScreen(
                onTitleClick = { animeId -> navController.navigate(AnimeDetail(animeId)) },
                keyPrefix = "home",
                sharedTransitionScope = sharedTransitionScope,
                animatedVisibilityScope = this@composable,
                modifier = Modifier.fillMaxSize()
            )
        }
        composable<AnimeDetail> { backStackEntry ->
            val animeDetail: AnimeDetail = backStackEntry.toRoute()
            AnimeDetailsScreen(
                animeId = animeDetail.animeId,
                keyPrefix = "home",
                sharedTransitionScope = sharedTransitionScope,
                animatedVisibilityScope = this@composable,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}