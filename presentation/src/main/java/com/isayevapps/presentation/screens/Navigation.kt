package com.isayevapps.presentation.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.isayevapps.presentation.Favorite
import com.isayevapps.presentation.Search
import com.isayevapps.presentation.TopAnime

data class BottomNavItem<T : Any>(val route: T, val icon: ImageVector, val label: String) {
    companion object {
        fun items() = listOf(
            BottomNavItem(TopAnime, Icons.Default.Home, "Top Anime"),
            BottomNavItem(Search, Icons.Default.Search, "Search"),
            BottomNavItem(Favorite, Icons.Default.Favorite, "Favorites")
        )
    }
}


