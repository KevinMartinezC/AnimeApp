package com.example.domain.repositories

import com.example.domain.model.search.Anime
import com.example.domain.model.detail.AnimeDetails
import com.example.domain.model.search.AnimeSort
import com.example.domain.model.search.AnimeType
import com.example.domain.model.character.CharacterInfo

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