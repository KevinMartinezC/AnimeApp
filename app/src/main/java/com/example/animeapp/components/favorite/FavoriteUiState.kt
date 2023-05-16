package com.example.animeapp.components.favorite

import com.example.domain.model.favorite.FavoriteAnime
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class FavoriteUiState(
    val removeFromFavorites: (Int) -> Unit = {},
    val favoriteAnimes: StateFlow<List<FavoriteAnime>> = MutableStateFlow(emptyList())

)
