package com.example.domain

interface AnimeRepository {
    suspend fun getAnimeList(page: Int, perPage: Int): List<Anime>
}