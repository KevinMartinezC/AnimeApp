package com.example.animeapp.components.favorite

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.animeapp.components.favorite.viewmodel.FavoriteViewModel

@Composable
fun FavoriteScreenContent(
    favoriteViewModel: FavoriteViewModel = hiltViewModel()
) {

    val uiStateFavorite by favoriteViewModel.uiStateFavorite.collectAsState()

    FavoriteScreen(
        favoriteAnimeFlow = uiStateFavorite.favoriteAnimes,
        removeFromFavorites = uiStateFavorite.removeFromFavorites,
    )
}