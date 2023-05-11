package com.example.domain.repositories

import com.example.domain.Anime

interface AnimeRepository {
    suspend fun getAnimeList(page: Int, perPage: Int): List<Anime>
}