package com.example.animeapp.components.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.animeapp.components.topbar.TopBarWithFavoriteIcon
import com.example.animeapp.components.navigation.BottomNavItem
import com.example.animeapp.components.search.viewmodel.SearchViewModel

@Composable
fun SearchScreenContent(
    navController: NavHostController,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val anime = viewModel.animeFlow.collectAsLazyPagingItems()
    val uiState by viewModel.uiState.collectAsState()
    val uiStateSearch by viewModel.uiStateSearch.collectAsState()

    Scaffold(
        topBar = {
            TopBarWithFavoriteIcon {
                navController.navigate(BottomNavItem.Favorite.route)
            }
        }
    ) { innerPadding ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            SearchScreen(
                animes = anime,
                onTypeChanged = uiStateSearch.onTypeChanged,
                onSortChanged = uiStateSearch.onSortChanged,
                onSearchChanged = uiStateSearch.onSearchChanged,
                navController = navController,
                onToggleFavorite = { anime -> uiStateSearch.addToFavorites(anime) },
                favoriteAnime = uiState.favoriteAnime
            )
        }
    }
}