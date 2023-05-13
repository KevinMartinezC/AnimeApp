package com.example.animeapp.components.search

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.animeapp.viewmodel.AnimeListViewModel
import com.example.domain.AnimeSort
import com.example.domain.AnimeType

@Composable
fun SearchScreenContent(
    navController: NavHostController,
) {
    val viewModel = hiltViewModel<AnimeListViewModel>()

    var selectedType by rememberSaveable { mutableStateOf(AnimeType.ANIME) }
    var selectedSort by rememberSaveable { mutableStateOf(AnimeSort.POPULARITY_DESC) }
    val searchQueryState = remember { mutableStateOf("") }
    val anime = viewModel.animeFlow.collectAsLazyPagingItems()

    LaunchedEffect(selectedType, selectedSort) {
        viewModel.applyFilter(selectedType, listOf(selectedSort))
    }

    LaunchedEffect(searchQueryState.value) {
        viewModel.applySearch(searchQueryState.value)
    }

    SearchScreen(
        animes = anime,
        onTypeChanged = { type -> selectedType = type },
        onSortChanged = { sort -> selectedSort = sort },
        onSearchChanged = { query -> searchQueryState.value = query },
        navController = navController
    )
}