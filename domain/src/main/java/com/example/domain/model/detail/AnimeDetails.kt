package com.example.domain.model.detail

data class AnimeDetails(
    val id: Int,
    val title: String,
    val englishName: String,
    val japaneseName: String,
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
    val imageUrl: String,
)

