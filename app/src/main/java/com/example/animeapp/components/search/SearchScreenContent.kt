package com.example.animeapp.components.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.animeapp.components.topbar.TopBarWithFavoriteIcon
import com.example.animeapp.components.favorite.viewmodel.FavoriteViewModel
import com.example.animeapp.components.navigation.BottomNavItem
import com.example.animeapp.components.search.viewmodel.AnimeListViewModel
import com.example.domain.model.search.AnimeSort
import com.example.domain.model.search.AnimeType

@Composable
fun SearchScreenContent(
    navController: NavHostController,
) {
    val viewModel = hiltViewModel<AnimeListViewModel>()
    val favoriteViewModel = hiltViewModel<FavoriteViewModel>()
    var selectedType by rememberSaveable { mutableStateOf(AnimeType.ANIME) }
    var selectedSort by rememberSaveable { mutableStateOf(AnimeSort.POPULARITY_DESC) }
    val searchQueryState = remember { mutableStateOf("") }
    val anime = viewModel.animeFlow.collectAsLazyPagingItems()
    val uiState by favoriteViewModel.uiState.collectAsState()

    LaunchedEffect(selectedType, selectedSort) {
        viewModel.applyFilter(selectedType, listOf(selectedSort))
    }

    LaunchedEffect(searchQueryState.value) {
        viewModel.applySearch(searchQueryState.value)
    }

    Scaffold(
        topBar = {
            TopBarWithFavoriteIcon {
                navController.navigate(BottomNavItem.Favorite.route)
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SearchScreen(
                animes = anime,
                onTypeChanged = { type -> selectedType = type },
                onSortChanged = { sort -> selectedSort = sort },
                onSearchChanged = { query -> searchQueryState.value = query },
                navController = navController,
                onToggleFavorite = { anime -> favoriteViewModel.addToFavorites(anime) },
                uiState = uiState
            )
        }
    }
}