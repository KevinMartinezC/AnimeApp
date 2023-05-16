package com.example.animeapp.components.search

import com.example.domain.model.search.Anime
import com.example.domain.model.search.AnimeSort
import com.example.domain.model.search.AnimeType

data class SearchUiState(
    val addToFavorites: (Anime) -> Unit = {},
    val favoriteAnime: Set<Int> = emptySet(),
    val onTypeChanged: (AnimeType) -> Unit = {},
    val onSortChanged: (AnimeSort) -> Unit = {},
    val onSearchChanged: (String) -> Unit = {}
)
