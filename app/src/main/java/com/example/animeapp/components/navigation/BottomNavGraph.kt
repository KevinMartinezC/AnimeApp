package com.example.animeapp.components.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.animeapp.viewmodel.AnimeListViewModel
import com.example.animeapp.components.favorite.FavoriteScreen
import com.example.animeapp.components.search.SearchScreen
import com.example.domain.AnimeSort
import com.example.domain.AnimeType

@Composable
fun BottomNavGraph(
    navController: NavHostController,
    contentPadding: PaddingValues
) {
    val viewModel: AnimeListViewModel = viewModel()

    var selectedType by rememberSaveable{ mutableStateOf(AnimeType.ANIME) }
    var selectedSort by rememberSaveable { mutableStateOf(AnimeSort.POPULARITY_DESC) }
    val searchQueryState = remember { mutableStateOf("") }
    val anime = viewModel.animeFlow.collectAsLazyPagingItems()
    LaunchedEffect(selectedType, selectedSort) {
        viewModel.applyFilter(selectedType, listOf(selectedSort))

    }

    LaunchedEffect(searchQueryState.value) {
        viewModel.applySearch(searchQueryState.value)
    }

    NavHost(
        navController = navController,
        startDestination = BottomNavItem.Search.route
    ) {
        composable(route = BottomNavItem.Search.route) {
            SearchScreen(
                animes = anime,
                onTypeChanged = { type -> selectedType = type },
                onSortChanged = { sort -> selectedSort = sort },
                onSearchChanged = { query -> searchQueryState.value = query },
                modifier = Modifier.padding(contentPadding))
        }
        composable(route = BottomNavItem.Favorite.route) {
            FavoriteScreen(modifier = Modifier.padding(contentPadding))
        }
    }
}
