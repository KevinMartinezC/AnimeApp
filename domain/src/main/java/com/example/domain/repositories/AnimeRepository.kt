package com.example.domain.repositories

import com.example.domain.model.Anime
import com.example.domain.model.AnimeDetails
import com.example.domain.model.AnimeSort
import com.example.domain.model.AnimeType
import com.example.domain.model.CharacterInfo

interface AnimeRepository {
    suspend fun getAnimeList(
        page: Int,
        perPage: Int,
        type: AnimeType,
        sort: List<AnimeSort>,
        search: String? = null
    ): List<Anime>

    suspend fun getAnimeDetails(id: Int): AnimeDetails

    suspend fun getCharacter(id: Int) : CharacterInfo
}