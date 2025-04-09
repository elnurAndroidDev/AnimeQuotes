package com.isayevapps.presentation.screens.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.isayevapps.presentation.screens.favorite.favoritesNavigation
import com.isayevapps.presentation.screens.home.HomeNavGraph
import com.isayevapps.presentation.screens.home.TopAnime
import com.isayevapps.presentation.screens.home.homeNavigation
import kotlinx.serialization.Serializable

@Serializable
object Search

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun MainNavHost(
    navController: NavHostController,
    startDestination: Any = HomeNavGraph,
    modifier: Modifier = Modifier,
) {
    SharedTransitionLayout(modifier) {
        NavHost(navController, startDestination) {
            homeNavigation(navController, this@SharedTransitionLayout)
            favoritesNavigation(navController, this@SharedTransitionLayout)
            composable<Search> {
                Text(text = "Search")
            }
        }
    }
}



