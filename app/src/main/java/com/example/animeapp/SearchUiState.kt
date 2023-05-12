package com.example.animeapp

import com.example.domain.Anime
import com.example.domain.AnimeSort
import com.example.domain.AnimeType

data class SearchUiState(
    val animeList: List<Anime> = emptyList(),
    val type: AnimeType = AnimeType.ANIME,
    val sort: List<AnimeSort> = listOf(AnimeSort.POPULARITY_DESC)
)
