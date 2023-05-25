package com.example.animeapp.utils

import androidx.compose.runtime.Composable
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.animeapp.components.search.SearchScreenContent
import com.example.domain.model.search.Anime
import com.example.domain.model.search.AnimeSort
import com.example.domain.model.search.AnimeType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

 fun prepareContent(
    animes: Flow<PagingData<Anime>> = flowOf(PagingData.empty()),
    onTypeChanged: (AnimeType) -> Unit = { },
    onSortChanged: (AnimeSort) -> Unit = { },
    onSearchChanged: (String) -> Unit = { },
    onAnimeSelected: (Anime) -> Unit = { },
    onToggleFavorite: (Anime) -> Unit = { },
    favoriteAnime: Set<Int> = setOf()
    ): @Composable () -> Unit {
    return {
        val animeItems = animes.collectAsLazyPagingItems()
        SearchScreenContent(
            animes = animeItems,
            onTypeChanged = onTypeChanged,
            onSortChanged = onSortChanged,
            onSearchChanged = onSearchChanged,
            onAnimeSelected = onAnimeSelected,
            onToggleFavorite = onToggleFavorite,
            favoriteAnime = favoriteAnime
        )
    }
}