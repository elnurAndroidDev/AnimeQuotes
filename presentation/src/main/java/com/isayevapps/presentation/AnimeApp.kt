package com.isayevapps.presentation

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.rememberNavController
import com.isayevapps.presentation.screens.navigation.BottomBar
import com.isayevapps.presentation.screens.navigation.BottomNavItem
import com.isayevapps.presentation.screens.navigation.MainNavHost


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimeApp(modifier: Modifier = Modifier) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val navController = rememberNavController()
    var selectedItemIndex by rememberSaveable { mutableIntStateOf(0) }
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = { AnimeQuoteTopAppBar(scrollBehavior = scrollBehavior, selectedItemIndex) },
        bottomBar = {
            BottomBar(
                navController,
                selectedItemIndex,
                onItemSelected = { selectedItemIndex = it })
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            MainNavHost(navController, modifier = Modifier.padding(innerPadding))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimeQuoteTopAppBar(
    scrollBehavior: TopAppBarScrollBehavior,
    selectedItemIndex: Int,
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        scrollBehavior = scrollBehavior,
        title = {
            Text(
                text = stringResource(BottomNavItem.items()[selectedItemIndex].label),
                style = MaterialTheme.typography.headlineSmall,
            )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.background,
            scrolledContainerColor = MaterialTheme.colorScheme.background
        ),
        modifier = modifier
    )
}