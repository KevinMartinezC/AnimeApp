package com.example.data.mapper.apollo

import com.example.data.GetAnimeListQuery
import com.example.domain.model.search.Anime

fun GetAnimeListQuery.Medium.toAnime(): Anime {
    return Anime(
        id = id,
        title = title?.romaji.orEmpty(),
        imageUrl = coverImage?.large.orEmpty()
    )
}