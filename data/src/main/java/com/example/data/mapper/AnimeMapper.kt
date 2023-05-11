package com.example.data.mapper

import com.example.data.GetAnimeListQuery
import com.example.domain.Anime
import com.example.domain.AnimeCharacter

fun GetAnimeListQuery.Medium.toAnime(): Anime {
    return Anime(
        id = id,
        title = title?.romaji.orEmpty(),
        imageUrl = coverImage?.large.orEmpty(),
        description = description,
        episodes = episodes,
        averageScore = averageScore,
        genres = genres?.mapNotNull { it },
        characters = characters?.nodes?.mapNotNull { character ->
            character?.let {
                AnimeCharacter(
                    id = it.id,
                    name = it.name?.full.orEmpty(),
                    imageUrl = it.image?.large.orEmpty()
                )
            }
        }
    )
}


