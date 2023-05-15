package com.example.animeapp.components.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.animeapp.components.topbar.TopBarWithFavoriteIcon
import com.example.animeapp.components.detail.viewmodel.DetailScreenViewModel
import com.example.animeapp.components.navigation.BottomNavItem

@Composable
fun DetailScreenContent(id: Int, navController: NavHostController) {
    
    val viewModel = hiltViewModel<DetailScreenViewModel>()
    val animeDetails by viewModel.animeDetails.collectAsState(null)

    LaunchedEffect(key1 = id) {
        viewModel.fetchAnimeDetails(id)
    }

    animeDetails?.let { details ->
        Scaffold(
            topBar = {
                TopBarWithFavoriteIcon {
                    navController.navigate(BottomNavItem.Favorite.route)
                }
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                DetailScreen(animeDetails = details, onCharacterClick = { characterId ->
                    navController.navigate("character/$characterId")
                })
            }

        }
    }
}