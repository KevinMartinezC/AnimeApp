package com.example.animeapp.components.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.animeapp.R
import com.example.domain.model.search.Anime


@Composable
fun AnimeGrid(
    animes: LazyPagingItems<Anime>,
    navController: NavHostController,
    onToggleFavorite: (Anime) -> Unit,
    favoriteAnime: Set<Int>,
    modifier: Modifier = Modifier
) {
    if (animes.itemCount == 0) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                stringResource(R.string.no_results),
                style = MaterialTheme.typography.titleLarge
            )
        }
    } else {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            contentPadding = PaddingValues(dimensionResource(id = R.dimen.padding_8dp)),
            modifier = modifier
        ) {
            items(animes.itemCount) { index ->
                val animeItem = animes[index] ?: return@items
                AnimeCard(
                    onToggleFavorite = onToggleFavorite,
                    anime = animeItem,
                    navController = navController,
                    favoriteAnime = favoriteAnime,
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


