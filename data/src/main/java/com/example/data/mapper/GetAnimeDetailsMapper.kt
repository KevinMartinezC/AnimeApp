package com.example.data.mapper

import com.example.data.GetAnimeDetailsQuery
import com.example.domain.model.AnimeCharacter
import com.example.domain.model.AnimeDetails

fun GetAnimeDetailsQuery.Media.toAnimeDetails(): AnimeDetails {
    return AnimeDetails(
        id = id,
        title = title?.romaji.orEmpty(),
        englishName = title?.english.orEmpty(),
        japaneseName = title?.native.orEmpty(),
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
                    imageUrl = it.image?.large.orEmpty(),
                )
            }
        }
    )
}

