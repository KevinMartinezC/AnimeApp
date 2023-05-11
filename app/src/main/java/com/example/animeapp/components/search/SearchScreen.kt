package com.example.animeapp.components.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import coil.compose.rememberAsyncImagePainter
import com.example.animeapp.R
import com.example.animeapp.SearchUiState
import com.example.animeapp.components.FilterOptions
import com.example.domain.Anime
import com.example.domain.AnimeSort
import com.example.domain.AnimeType

@Composable
fun SearchScreen(
    uiState: SearchUiState,
    onTypeChanged: (AnimeType) -> Unit,
    onSortChanged: (AnimeSort) -> Unit,
    modifier: Modifier = Modifier
) {
    val animeList = uiState.animeList
    var selectedType by rememberSaveable {
        mutableStateOf(AnimeType.ANIME)
    }
    var selectedSort by rememberSaveable {
        mutableStateOf(AnimeSort.POPULARITY_DESC)
    }

    val onTypeChangedState by rememberUpdatedState(onTypeChanged)
    val onSortChangedState by rememberUpdatedState(onSortChanged)



    Column(modifier = modifier) {
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
                    items(animeList.size) { index ->
                        AnimeCard(
                            anime = animeList[index],
                            modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_4dp))
                        )
                    }
                }
            }


}

private const val MAX_LINE_TEXT = 3

@Composable
fun AnimeCard(anime: Anime, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .size(
                width = dimensionResource(id = R.dimen.wight_150dp),
                height = dimensionResource(id = R.dimen.height_270dp)
            )
            .padding(dimensionResource(id = R.dimen.padding_4dp)),
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.rounder_corner_4))
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            NetworkImage(
                url = anime.imageUrl,
                contentDescription = anime.title,
                modifier = Modifier
                    .height(dimensionResource(id = R.dimen.height_180dp))
                    .fillMaxWidth()
            )
            Text(
                text = anime.title,
                modifier = Modifier.padding(dimensionResource(id = R.dimen.padding_2dp)),
                maxLines = MAX_LINE_TEXT,
            )
        }
    }
}


@Composable
fun NetworkImage(url: String, contentDescription: String, modifier: Modifier = Modifier) {
    val painter = rememberAsyncImagePainter(model = url)
    Image(painter = painter, contentDescription = contentDescription, modifier = modifier)
}
