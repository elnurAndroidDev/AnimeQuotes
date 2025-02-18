package com.isayevapps.presentation

import android.app.Application
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.toRoute
import com.isayevapps.presentation.screens.animedetails.AnimeDetailViewModel
import com.isayevapps.presentation.screens.animedetails.AnimeDetailsScreen
import com.isayevapps.presentation.screens.animetitles.AnimeTitlesScreen
import com.isayevapps.presentation.screens.animetitles.AnimeTitlesViewModel
import kotlinx.serialization.Serializable


//enum class Screen(val route: String) {
//    AnimeTitleScreen("anime_title"),
//    AnimeDetail("anime_detail/{id}");
//
//    fun createRoute(vararg args: Any): String {
//        var formattedRoute = route
//        args.forEach { arg ->
//            formattedRoute = formattedRoute.replaceFirst(Regex("\\{.*?\\}"), arg.toString())
//        }
//        return formattedRoute
//    }
//}

@Serializable
object AnimeTitles

@Serializable
data class AnimeDetail(val animeId: Int)


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimeQuotesApp(modifier: Modifier = Modifier) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val navController = rememberNavController()
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = { AnimeQuoteTopAppBar(scrollBehavior = scrollBehavior) }
    ) { innerPadding ->
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            NavHost(
                navController = navController,
                startDestination = AnimeTitles,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable<AnimeTitles> {
                    val animeTitlesViewModel = hiltViewModel<AnimeTitlesViewModel>()
                    AnimeTitlesScreen(
                        viewModel = animeTitlesViewModel,
                        onTitleClick = { animeId -> navController.navigate(AnimeDetail(animeId)) },
                        modifier = Modifier.fillMaxSize()
                    )
                }
                composable<AnimeDetail> { backStackEntry ->
                    val animeDetail: AnimeDetail = backStackEntry.toRoute()
                    val animeDetailViewModel = hiltViewModel<AnimeDetailViewModel>()
                    AnimeDetailsScreen(
                        animeId = animeDetail.animeId,
                        viewModel = animeDetailViewModel
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimeQuoteTopAppBar(
    scrollBehavior: TopAppBarScrollBehavior,
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        scrollBehavior = scrollBehavior,
        title = {
            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.headlineSmall,
            )
        },
        modifier = modifier
    )
}