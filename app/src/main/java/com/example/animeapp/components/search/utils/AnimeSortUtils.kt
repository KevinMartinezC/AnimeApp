package com.example.animeapp.components.search.utils

import com.example.domain.model.search.AnimeSort

object AnimeSortUtils {
    private const val POPULARITY = "Popularity (Descending)"
    private const val EPISODES_DESC = "Episodes (Descending)"
    private const val START_DATE = "Start Date"

    val sortDisplayNames = mapOf(
        AnimeSort.POPULARITY_DESC to POPULARITY,
        AnimeSort.EPISODES_DESC to EPISODES_DESC,
        AnimeSort.START_DATE to START_DATE
    )
}
