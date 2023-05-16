package com.example.animeapp.components.character

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.animeapp.components.topbar.TopBarWithFavoriteIcon
import com.example.animeapp.components.character.viewmodel.CharacterScreenViewModel
import com.example.animeapp.components.navigation.BottomNavItem


@Composable
fun CharacterScreenContent(
    characterId: Int,
    navController: NavHostController,
    characterViewModel: CharacterScreenViewModel = hiltViewModel()

) {
    val characterDetails by characterViewModel.characterDetails.collectAsState()

    characterDetails?.let { character ->
        Scaffold(
            topBar = {
                TopBarWithFavoriteIcon {
                    navController.navigate(BottomNavItem.Favorite.route)
                }
            }
        ) { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CharacterScreen(characterDetails = character)
            }
        }
    } ?: run {
        characterViewModel.fetchCharacterDetails(characterId)
    }
}



