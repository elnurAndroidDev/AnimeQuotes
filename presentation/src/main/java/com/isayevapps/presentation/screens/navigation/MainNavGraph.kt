package com.isayevapps.presentation.screens.navigation

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.isayevapps.presentation.screens.favorite.favoritesNavigation
import com.isayevapps.presentation.screens.home.HomeNavGraph
import com.isayevapps.presentation.screens.home.homeNavigation
import com.isayevapps.presentation.screens.search.SearchScreen
import com.isayevapps.presentation.screens.search.SearchViewModel
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
                SearchScreen(
                    viewModel = hiltViewModel<SearchViewModel>(),
                    onTitleClick = {},
                    keyPrefix = "search",
                    sharedTransitionScope = this@SharedTransitionLayout,
                    animatedVisibilityScope = this@composable,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}



