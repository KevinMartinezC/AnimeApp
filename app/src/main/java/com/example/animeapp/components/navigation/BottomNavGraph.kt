package com.example.animeapp.components.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.animeapp.components.character.CharacterDetailScreen
import com.example.animeapp.components.detail.DetailScreen
import com.example.animeapp.components.favorite.FavoriteScreen
import com.example.animeapp.components.search.SearchScreenContent

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
            SearchScreenContent(
                navController = navController
            )
        }
        composable(route = BottomNavItem.Favorite.route) {
            FavoriteScreen(modifier = Modifier.padding(contentPadding))
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
                CharacterDetailScreen(characterId = characterId)
            }
        }
    }
}