package com.isayevapps.presentation.screens.navigation

import android.annotation.SuppressLint
import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.isayevapps.presentation.R
import com.isayevapps.presentation.screens.favorite.FavoritesNavGraph
import com.isayevapps.presentation.screens.home.HomeNavGraph
import com.isayevapps.presentation.screens.search.SearchNavGraph

data class BottomNavItem<T : Any>(
    val route: T,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    @StringRes val label: Int
) {
    companion object {
        fun items() = listOf(
            BottomNavItem(HomeNavGraph, Icons.Filled.Home, Icons.Outlined.Home, R.string.top_anime),
            BottomNavItem(
                SearchNavGraph, Icons.Filled.Search, Icons.Outlined.Search, R.string.search
            ),
            BottomNavItem(
                FavoritesNavGraph,
                Icons.Filled.Favorite,
                Icons.Outlined.Favorite,
                R.string.favorites
            )
        )
    }
}

val Any.routeName: String
    get() = this::class.qualifiedName.toString()

@SuppressLint("RestrictedApi")
@Composable
fun BottomBar(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar {
        BottomNavItem.items().forEach { item ->
            val isSelected = currentDestination?.hierarchy?.any {
                it.route == item.route.routeName
            } == true
            NavigationBarItem(
                selected = isSelected,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    val icon = if (isSelected) item.selectedIcon else item.unselectedIcon
                    Icon(icon, contentDescription = null)
                },
                label = { Text(text = stringResource(item.label)) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    selectedTextColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    indicatorColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
    }
}