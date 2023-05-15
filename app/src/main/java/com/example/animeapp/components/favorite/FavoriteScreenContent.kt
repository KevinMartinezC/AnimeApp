package com.example.animeapp.components.favorite

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.animeapp.components.favorite.viewmodel.FavoriteViewModel

@Composable
fun FavoriteScreenContent() {

    val favoriteViewModel = hiltViewModel<FavoriteViewModel>()

    FavoriteScreen(
        favoriteAnimeFlow = favoriteViewModel.favoriteAnimes,
        removeFromFavorites = favoriteViewModel::removeFromFavorites,
    )
}