package com.example.animeapp.components.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.animeapp.components.character.CharacterScreen
import com.example.animeapp.components.detail.DetailScreen
import com.example.animeapp.components.favorite.FavoriteScreen
import com.example.animeapp.components.search.SearchScreen

@Composable
fun BottomNavGraph(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = BottomNavItem.Search.route
    ) {
        composable(route = BottomNavItem.Search.route) {
            SearchScreen(
                navController = navController
            )
        }
        composable(route = BottomNavItem.Favorite.route) {
            FavoriteScreen()
        }
        composable(
            route = "detail/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { navBackStackEntry ->
            navBackStackEntry.arguments?.getInt("id")?.let { id ->
                DetailScreen(id = id, navController = navController)
            }
        }
        composable(
            route = "character/{characterId}",
            arguments = listOf(navArgument("characterId") { type = NavType.IntType })
        ) { navBackStackEntry ->
            navBackStackEntry.arguments?.getInt("characterId")?.let { characterId ->
                CharacterScreen(characterId = characterId, navController = navController)
            }
        }
    }
}