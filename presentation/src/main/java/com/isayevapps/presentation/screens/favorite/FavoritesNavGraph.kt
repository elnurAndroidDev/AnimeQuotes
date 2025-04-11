package com.isayevapps.presentation.screens.favorite

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
import com.isayevapps.presentation.screens.favorite.favoritedetails.FavoriteDetailViewModel
import com.isayevapps.presentation.screens.favorite.favoritedetails.FavoriteDetailsScreen
import com.isayevapps.presentation.screens.favorite.favoritesscreen.FavoritesScreen
import com.isayevapps.presentation.screens.favorite.favoritesscreen.FavoritesViewModel
import kotlinx.serialization.Serializable

@Serializable
object FavoritesNavGraph

@Serializable
object FavoritesList

@Serializable
data class FavoriteAnimeDetail(val animeId: Int)

@OptIn(ExperimentalSharedTransitionApi::class)
fun NavGraphBuilder.favoritesNavigation(
    navController: NavController,
    sharedTransitionScope: SharedTransitionScope
) {
    navigation<FavoritesNavGraph>(startDestination = FavoritesList) {
        composable<FavoritesList> {
            val favoritesViewModel = hiltViewModel<FavoritesViewModel>()
            FavoritesScreen(
                viewModel = favoritesViewModel,
                onTitleClick = { animeId -> navController.navigate(FavoriteAnimeDetail(animeId)) },
                keyPrefix = "favorites",
                sharedTransitionScope = sharedTransitionScope,
                animatedVisibilityScope = this@composable,
                modifier = Modifier.fillMaxSize()
            )
        }
        composable<FavoriteAnimeDetail> { backStackEntry ->
            val animeDetail: FavoriteAnimeDetail = backStackEntry.toRoute()
            val favoriteDetailViewModel = hiltViewModel<FavoriteDetailViewModel>()
            FavoriteDetailsScreen(
                animeId = animeDetail.animeId,
                keyPrefix = "favorites",
                sharedTransitionScope = sharedTransitionScope,
                animatedVisibilityScope = this@composable,
                viewModel = favoriteDetailViewModel
            )
        }
    }
}