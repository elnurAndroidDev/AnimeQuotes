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
import com.isayevapps.domain.AnimeItem
import com.isayevapps.presentation.screens.home.animedetails.AnimeDetailViewModel
import com.isayevapps.presentation.screens.home.animedetails.AnimeDetailsScreen
import com.isayevapps.presentation.screens.home.animetitles.AnimeTitlesScreen
import com.isayevapps.presentation.screens.home.animetitles.AnimeTitlesViewModel
import com.isayevapps.presentation.screens.search.searchresultdetails.SearchDetailViewModel
import com.isayevapps.presentation.screens.search.searchresultdetails.SearchDetailsScreen
import com.isayevapps.presentation.screens.search.searchscreen.SearchScreen
import com.isayevapps.presentation.screens.search.searchscreen.SearchViewModel
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
            val searchViewModel = hiltViewModel<SearchViewModel>()
            SearchScreen(
                viewModel = searchViewModel,
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
            val searchDetailViewModel = hiltViewModel<SearchDetailViewModel>()
            SearchDetailsScreen(
                animeId = searchDetail.animeId,
                keyPrefix = "search",
                sharedTransitionScope = sharedTransitionScope,
                animatedVisibilityScope = this@composable,
                viewModel = searchDetailViewModel
            )
        }
    }
}