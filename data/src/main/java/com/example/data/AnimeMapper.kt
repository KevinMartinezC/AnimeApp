package com.example.data

import com.example.data.GetAnimeListQuery
import com.example.domain.Anime

fun GetAnimeListQuery.Medium.toAnime(): Anime {
    return Anime(
        id = id,
        title = title?.romaji.orEmpty(),
        imageUrl = coverImage?.large.orEmpty()
    )
}