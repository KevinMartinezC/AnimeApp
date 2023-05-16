package com.example.animeapp.components.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.animeapp.components.search.filter.FilterOptions
import com.example.animeapp.theme.MyApplicationTheme
import com.example.domain.model.search.Anime
import com.example.domain.model.search.AnimeSort
import com.example.domain.model.search.AnimeType
import kotlinx.coroutines.flow.flowOf


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
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
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

@Preview
@Composable
fun PreviewSearchScreen() {

    val animes = flowOf(PagingData.from(listOf(Anime(
        id = 1,
        title = "Demon Slayer",
        imageUrl = "https://i.blogs.es/bc1dd2/naruto/840_560.png",
    )))) .collectAsLazyPagingItems()

    val navController = rememberNavController()
    MyApplicationTheme(darkTheme = true){
        SearchScreen(
            animes = animes,
            onTypeChanged = {},
            onSortChanged = {},
            onSearchChanged = {},
            navController = navController,
            onToggleFavorite = {},
            favoriteAnime = setOf(1)
        )
    }
}
