package com.example.domain.repositories

import com.example.domain.model.favorite.FavoriteAnime
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {
    val favoriteAnime: Flow<List<FavoriteAnime>>

    suspend fun addFavoriteAnime(favoriteAnime: FavoriteAnime)

    suspend fun removeFavoriteAnime(animeId: Int)
}