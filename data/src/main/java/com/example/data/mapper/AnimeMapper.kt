package com.example.data.mapper

import com.example.data.GetAnimeListQuery
import com.example.data.type.MediaSort
import com.example.data.type.MediaType
import com.example.domain.Anime
import com.example.domain.AnimeCharacter
import com.example.domain.AnimeSort
import com.example.domain.AnimeType

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
fun AnimeType.toGraphQLMediaType(): MediaType {
    return when (this) {
        AnimeType.ANIME-> MediaType.ANIME
        AnimeType.MANGA-> MediaType.MANGA
    }
}

 fun AnimeSort.toGraphQLMediaSort(): MediaSort {
    return when (this) {
        AnimeSort.POPULARITY_DESC -> MediaSort.POPULARITY_DESC
        AnimeSort.EPISODES_DESC -> MediaSort.EPISODES_DESC
        AnimeSort.START_DATE -> MediaSort.START_DATE
    }
}


