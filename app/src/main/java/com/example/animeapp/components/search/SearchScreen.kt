package com.example.animeapp.components.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.paging.compose.LazyPagingItems
import com.example.animeapp.components.search.filter.FilterOptions
import com.example.domain.model.search.Anime
import com.example.domain.model.search.AnimeSort
import com.example.domain.model.search.AnimeType


@Composable
fun SearchScreen(
    animes: LazyPagingItems<Anime>,
    onTypeChanged: (AnimeType) -> Unit,
    onSortChanged: (AnimeSort) -> Unit,
    onSearchChanged: (String) -> Unit,
    navController: NavHostController,
    onToggleFavorite: (Anime) -> Unit,
    favoriteAnime: Set<Int>,
    modifier: Modifier = Modifier
) {
    var selectedType by rememberSaveable { mutableStateOf(AnimeType.ANIME) }
    var selectedSort by rememberSaveable { mutableStateOf(AnimeSort.POPULARITY_DESC) }
    val onTypeChangedState by rememberUpdatedState(onTypeChanged)
    val onSortChangedState by rememberUpdatedState(onSortChanged)
    var searchQuery by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        SearchBar(
            searchQuery = searchQuery,
            onSearchChanged = { newValue ->
                searchQuery = newValue
                onSearchChanged(newValue)
            },
        )

        FilterOptions(
            type = selectedType,
            sort = selectedSort,
            onTypeSelected = { type ->
                selectedType = type
                onTypeChangedState(type)
            }
        ) { sort ->
            selectedSort = sort
            onSortChangedState(sort)
        }

        AnimeGrid(
            animes = animes,
            navController = navController,
            onToggleFavorite = onToggleFavorite,
            favoriteAnime = favoriteAnime,
            modifier = modifier
        )
    }
}
