package com.example.data.mapper.apollo

import com.example.data.type.MediaType
import com.example.domain.model.search.AnimeType

fun AnimeType.toGraphQLMediaType(): MediaType {
    return when (this) {
        AnimeType.ANIME-> MediaType.ANIME
        AnimeType.MANGA-> MediaType.MANGA
    }
}