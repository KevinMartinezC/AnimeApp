package com.example.data.mapper

import com.example.data.type.MediaSort
import com.example.domain.AnimeSort

fun AnimeSort.toGraphQLMediaSort(): MediaSort {
    return when (this) {
        AnimeSort.POPULARITY_DESC -> MediaSort.POPULARITY_DESC
        AnimeSort.EPISODES_DESC -> MediaSort.EPISODES_DESC
        AnimeSort.START_DATE -> MediaSort.START_DATE
    }
}