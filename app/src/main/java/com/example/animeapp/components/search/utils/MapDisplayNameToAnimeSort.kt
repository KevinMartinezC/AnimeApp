package com.example.animeapp.components.search.utils

import com.example.domain.AnimeSort

fun mapDisplayNameToAnimeSort(displayName: String, sortDisplayNames: Map<AnimeSort, String>): AnimeSort? {
    return sortDisplayNames.entries.firstOrNull { it.value == displayName }?.key
}