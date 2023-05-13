package com.example.animeapp.components.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.input.ImeAction
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.animeapp.R
import com.example.animeapp.components.search.filter.FilterOptions
import com.example.domain.Anime
import com.example.domain.AnimeSort
import com.example.domain.AnimeType

@Composable
fun SearchScreen(
    animes: LazyPagingItems<Anime>,
    onTypeChanged: (AnimeType) -> Unit,
    onSortChanged: (AnimeSort) -> Unit,
    onSearchChanged: (String) -> Unit,
    navController: NavHostController,
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
        TextField(
            value = searchQuery,
            onValueChange = { newValue ->
                searchQuery = newValue
                onSearchChanged(newValue)
            },
            label = { Text("Search") },
            singleLine = true,
            keyboardActions = KeyboardActions(onDone = { onSearchChanged(searchQuery) }),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search)
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

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            contentPadding = PaddingValues(dimensionResource(id = R.dimen.padding_8dp)),
            modifier = modifier
        ) {
            items(animes.itemCount) { index ->
                val animeItem = animes[index] ?: return@items
                AnimeCard(
                    anime = animeItem,
                    navController = navController,
                    modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_4dp))
                )
            }
            animes.apply {
                if (loadState.append is LoadState.Loading) {
                    item {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentSize(Alignment.Center)
                                .padding()
                        )
                    }
                }
            }
        }
    }
}
