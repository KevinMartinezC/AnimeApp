package com.example.animeapp.components.search.utils

import com.example.domain.AnimeSort

object AnimeSortUtils {
    const val POPULARITY = "Popularity (Descending)"
    const val EPISODES_DESC = "Episodes (Descending)"
    const val START_DATE = "Start Date"

    val sortDisplayNames = mapOf(
        AnimeSort.POPULARITY_DESC to POPULARITY,
        AnimeSort.EPISODES_DESC to EPISODES_DESC,
        AnimeSort.START_DATE to START_DATE
    )
}
