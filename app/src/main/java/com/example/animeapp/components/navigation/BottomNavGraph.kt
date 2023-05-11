package com.example.animeapp.components.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.animeapp.viewmodel.AnimeListViewModel
import com.example.animeapp.components.favorite.FavoriteScreen
import com.example.animeapp.components.search.SearchScreen

@Composable
fun BottomNavGraph(
    navController: NavHostController,
    contentPadding: PaddingValues
) {
    val viewModel: AnimeListViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsState()

    NavHost(
        navController = navController,
        startDestination = BottomNavItem.Search.route
    ) {
        composable(route = BottomNavItem.Search.route) {
            SearchScreen(uiState = uiState, modifier = Modifier.padding(contentPadding))
        }
        composable(route = BottomNavItem.Favorite.route) {
            FavoriteScreen(modifier = Modifier.padding(contentPadding))
        }
    }
}
