package com.example.animeapp.components.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.domain.Anime

@Composable
fun SearchScreen(animeList: List<Anime>, modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            contentPadding = PaddingValues(8.dp),
            modifier = modifier
        ) {
            items(animeList.size) { index ->
                AnimeCard(anime = animeList[index], modifier = Modifier.padding(4.dp))
            }
        }
    }
}

@Composable
fun AnimeCard(anime: Anime, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .size(width = 150.dp, height = 270.dp)
            .padding(4.dp),
        shape = RoundedCornerShape(4.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            NetworkImage(
                url = anime.imageUrl,
                contentDescription = anime.title,
                modifier = Modifier
                    .height(180.dp)
                    .fillMaxWidth()
            )
            Text(
                text = anime.title,
                modifier = Modifier.padding(2.dp),
                maxLines = 3,
            )
        }
    }
}



@Composable
fun NetworkImage(url: String, contentDescription: String, modifier: Modifier = Modifier) {
    val painter = rememberAsyncImagePainter(model = url)
    Image(painter = painter, contentDescription = contentDescription, modifier = modifier)
}
