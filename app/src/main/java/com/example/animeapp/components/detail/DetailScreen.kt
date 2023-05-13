package com.example.animeapp.components.detail

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.animeapp.R
import com.example.animeapp.components.detail.viewmodel.DetailScreenViewModel
import com.example.domain.AnimeCharacter
import com.example.domain.AnimeDetails
import org.jsoup.Jsoup

@Composable
fun DetailScreen(id: Int) {
    val viewModel = hiltViewModel<DetailScreenViewModel>()
    val animeDetails by viewModel.animeDetails.collectAsState(null)

    LaunchedEffect(key1 = id) {
        viewModel.fetchAnimeDetails(id)
    }

    animeDetails?.let { details ->
        AnimeDetailContent(animeDetails = details)
    }
}

@Composable
fun AnimeDetailContent(animeDetails: AnimeDetails) {
    val scrollState = rememberScrollState()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            Box {
                Image(
                    painter = rememberAsyncImagePainter(animeDetails.imageUrl),
                    contentDescription = stringResource(R.string.image_cover),
                    modifier = Modifier
                        .height(250.dp)
                        .fillMaxWidth(),
                    contentScale = ContentScale.Crop
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = animeDetails.title,
                    style = MaterialTheme.typography.headlineLarge,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(8.dp))
                ShowDescriptionFormat(animeDetails.description)
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = formatResourceString(R.string.episodes, animeDetails.episodes ?: 0),
                    fontSize = 18.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = formatResourceString(R.string.score, animeDetails.averageScore ?: 0),
                    fontSize = 18.sp
                )
                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(id = R.string.genders),
                        fontSize = 18.sp,
                        modifier = Modifier.padding(end = 8.dp)
                    )
                    animeDetails.genres?.take(3)?.forEach { genre ->
                        Text(
                            text = genre,
                            fontSize = 18.sp,
                            modifier = Modifier.padding(end = 8.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = formatResourceString(R.string.englisname, animeDetails.englishName),
                    fontSize = 18.sp
                )
                Spacer(
                    modifier = Modifier.height(4.dp)
                )
                Text(
                    text = formatResourceString(R.string.japanese_name, animeDetails.japaneseName),
                    fontSize = 18.sp
                )

            }
            CharacterCards(characters = animeDetails.characters ?: emptyList())

            Spacer(modifier = Modifier.height(100.dp))

        }


}

@Composable
fun CharacterCards(characters: List<AnimeCharacter>) {
    LazyRow {
        items(characters.size) { index ->
            val character = characters[index]
            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .width(120.dp)
                    .height(150.dp),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(character.imageUrl),
                        contentDescription = stringResource(R.string.character_image),
                        modifier = Modifier
                            .size(64.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = character.name,
                            style = MaterialTheme.typography.bodyMedium,
                            textAlign = TextAlign.Center,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ShowDescriptionFormat(description: String?) {
    description?.let {
        val cleanDescription = Jsoup.parse(description).text()
        Text(
            text = cleanDescription,
            fontSize = 16.sp,
            lineHeight = 24.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Composable
fun formatResourceString(@StringRes stringResId: Int, value: Any): String {
    return "${stringResource(stringResId)}: $value"
}