package com.example.domain.repositories

import com.example.domain.Anime
import com.example.domain.AnimeSort
import com.example.domain.AnimeType

interface AnimeRepository {
    suspend fun getAnimeList(
        page: Int,
        perPage: Int,
        type: AnimeType,
        sort: List<AnimeSort>,
        search: String? = null

    ): List<Anime>
}