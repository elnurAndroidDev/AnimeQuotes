package com.isayevapps.presentation.screens.search

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.isayevapps.presentation.screens.search.searchresultdetails.SearchDetailViewModel
import com.isayevapps.presentation.screens.search.searchresultdetails.SearchDetailsScreen
import com.isayevapps.presentation.screens.search.searchscreen.SearchScreen
import kotlinx.serialization.Serializable


@Serializable
object SearchNavGraph

@Serializable
object SearchResult

@Serializable
data class SearchDetail(val animeId: Int)

@OptIn(ExperimentalSharedTransitionApi::class)
fun NavGraphBuilder.searchNavigation(
    navController: NavController,
    sharedTransitionScope: SharedTransitionScope
) {
    navigation<SearchNavGraph>(startDestination = SearchResult) {
        composable<SearchResult> {
            SearchScreen(
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
            val searchDetail: SearchDetail = backStackEntry.toRoute()
            SearchDetailsScreen(
                animeId = searchDetail.animeId,
                keyPrefix = "search",
                sharedTransitionScope = sharedTransitionScope,
                animatedVisibilityScope = this@composable
            )
        }
    }
}