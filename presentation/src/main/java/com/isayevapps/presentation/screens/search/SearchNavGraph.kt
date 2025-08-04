package com.isayevapps.presentation.screens.search

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.isayevapps.presentation.screens.search.searchresultdetails.SearchDetailsScreen
import com.isayevapps.presentation.screens.search.searchresultscreen.SearchResultScreen
import com.isayevapps.presentation.screens.search.searchscreen.SearchScreen
import kotlinx.serialization.Serializable


@Serializable
object SearchNavGraph

@Serializable
data class SearchResult(val query: String)

@Serializable
object SearchQuery

@Serializable
data class SearchDetail(val animeId: Int)

@OptIn(ExperimentalSharedTransitionApi::class)
fun NavGraphBuilder.searchNavigation(
    navController: NavController,
    sharedTransitionScope: SharedTransitionScope
) {
    navigation<SearchNavGraph>(startDestination = SearchQuery) {
        composable<SearchQuery> {
            SearchScreen(
                modifier = Modifier.fillMaxSize(),
                sharedTransitionScope = sharedTransitionScope,
                animatedVisibilityScope = this@composable,
                onSearchClick = { query ->
                    navController.navigate(SearchResult(query))
                }
            )
        }
        composable<SearchResult> {
            SearchResultScreen(
                onTitleClick = { animeId ->
                    navController.navigate(SearchDetail(animeId))
                },
                keyPrefix = "search",
                sharedTransitionScope = sharedTransitionScope,
                animatedVisibilityScope = this@composable,
                modifier = Modifier.fillMaxSize()
            )
        }
        composable<SearchDetail> { backStackEntry ->
            SearchDetailsScreen(
                keyPrefix = "search",
                sharedTransitionScope = sharedTransitionScope,
                animatedVisibilityScope = this@composable
            )
        }
    }
}