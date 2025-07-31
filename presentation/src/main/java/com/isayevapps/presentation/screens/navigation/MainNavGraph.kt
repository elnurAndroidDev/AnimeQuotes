package com.isayevapps.presentation.screens.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.isayevapps.presentation.screens.favorite.favoritesNavigation
import com.isayevapps.presentation.screens.home.HomeNavGraph
import com.isayevapps.presentation.screens.home.homeNavigation
import com.isayevapps.presentation.screens.search.searchNavigation

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
            searchNavigation(navController, this@SharedTransitionLayout)
        }
    }
}



