package com.example.animeapp

import com.example.domain.model.Anime
import com.example.domain.model.AnimeSort
import com.example.domain.model.AnimeType

data class SearchUiState(

    val animeList: List<Anime> = emptyList(),
    val type: AnimeType,
    val sort: List<AnimeSort>,
    val search: String? = null

)
