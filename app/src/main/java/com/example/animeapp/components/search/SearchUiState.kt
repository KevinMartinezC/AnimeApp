package com.example.animeapp.components.search

import com.example.domain.model.search.Anime
import com.example.domain.model.search.AnimeSort
import com.example.domain.model.search.AnimeType

data class SearchUiState(
    val animeList: List<Anime> = emptyList(),
    val type: AnimeType,
    val sort: List<AnimeSort>,
    val search: String? = null

)
