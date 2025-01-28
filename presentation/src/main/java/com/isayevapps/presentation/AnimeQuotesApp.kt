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
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.isayevapps.presentation.screens.animedetails.AnimeDetailsScreen
import com.isayevapps.presentation.screens.animetitles.AnimeTitlesScreen


enum class Screen(val route: String) {
    AnimeTitleScreen("anim_title"),
    AnimeDetail("anime_detail/{title}");

    fun createRoute(vararg args: Any): String {
        var formattedRoute = route
        args.forEach { arg ->
            formattedRoute = formattedRoute.replaceFirst(Regex("\\{.*?\\}"), arg.toString())
        }
        return formattedRoute
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimeQuotesApp(application: Application, modifier: Modifier = Modifier) {
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
                startDestination = Screen.AnimeTitleScreen.route,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(Screen.AnimeTitleScreen.route) {
                    val homeViewModel = (application as ProvideViewModel).provideViewModel()
                    AnimeTitlesScreen(
                        homeViewModel.uiState,
                        navController,
                        modifier = Modifier.fillMaxSize()
                    )
                }
                composable(
                    route = Screen.AnimeDetail.route,
                    arguments = listOf(navArgument("title") { type = NavType.StringType })
                ) { backStackEntry ->
                    val title = backStackEntry.arguments?.getString("title") ?: ""
                    AnimeDetailsScreen(title)
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