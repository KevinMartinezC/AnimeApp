package com.example.animeapp.components.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.animeapp.components.favorite.FavoriteScreen
import com.example.news.components.search.SearchScreen

@Composable
fun BottomNavGraph(
    navController: NavHostController,
    contentPadding: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = BottomNavItem.Search.route
    ) {
        composable(route = BottomNavItem.Search.route) {
            SearchScreen(modifier = Modifier.padding(contentPadding))
        }
        composable(route = BottomNavItem.Favorite.route) {
            FavoriteScreen(modifier = Modifier.padding(contentPadding))
        }
    }
}

