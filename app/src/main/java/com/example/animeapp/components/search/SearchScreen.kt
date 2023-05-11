package com.example.animeapp.components.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
        LazyColumn(modifier = modifier) {
            items(animeList) { anime ->
                AnimeCard(anime = anime, modifier = Modifier.padding(8.dp))
            }
        }
    }
}

@Composable
fun AnimeCard(anime: Anime, modifier: Modifier = Modifier) {
    Card(modifier = modifier, shape = RoundedCornerShape(4.dp)) {
        Column {
            anime.imageUrl?.let { url ->
                NetworkImage(
                    url = url,
                    contentDescription = anime.title,
                    modifier = Modifier
                        .height(200.dp)
                        .fillMaxWidth()
                )
            }
            Text(text = anime.title, modifier = Modifier.padding(8.dp))
        }
    }
}

@Composable
fun NetworkImage(url: String, contentDescription: String, modifier: Modifier = Modifier) {
    val painter = rememberAsyncImagePainter(model = url)
    Image(painter = painter, contentDescription = contentDescription, modifier = modifier)
}
