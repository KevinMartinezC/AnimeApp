package com.example.domain

data class Anime(
    val id: Int,
    val title: String,
    val imageUrl: String,
    val description: String?,
    val episodes: Int?,
    val averageScore: Int?,
    val genres: List<String>?,
    val characters: List<AnimeCharacter>?
)

data class AnimeCharacter(
    val id: Int,
    val name: String,
    val imageUrl: String
)

