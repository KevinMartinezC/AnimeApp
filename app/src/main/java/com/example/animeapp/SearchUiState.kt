package com.example.animeapp

import com.example.domain.Anime

data class SearchUiState(
    val animeList: List<Anime> = emptyList()
)
