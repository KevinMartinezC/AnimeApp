package com.example.animeapp.components.character

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.rememberAsyncImagePainter
import com.example.animeapp.R
import com.example.animeapp.components.utils.ShowDescriptionFormat
import com.example.animeapp.theme.MyApplicationTheme
import com.example.domain.model.character.CharacterInfo


@Composable
fun CharacterScreen(characterDetails: CharacterInfo) {
    LazyColumn(
        modifier = Modifier
            .padding(
                start = dimensionResource(id = R.dimen.padding_20dp),
                end = dimensionResource(id = R.dimen.padding_20dp)
            ),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.spacing_8dp))
    ) {
        item {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                if (LocalInspectionMode.current){
                    Box(
                        modifier = Modifier
                            .size(dimensionResource(id = R.dimen.size_200))
                            .clip(CircleShape)
                            .background(Color.LightGray),
                    )
                }else{
                    Image(
                        painter = rememberAsyncImagePainter(model = characterDetails.imageUrl),
                        contentDescription = stringResource(R.string.image_character),
                        modifier = Modifier
                            .size(dimensionResource(id = R.dimen.size_200))
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                }

            }
        }
        item {
            ShowDescriptionFormat(description = characterDetails.description)
        }
    }
}

@Preview
@Composable
fun PreviewCharacterScreen() {
        val character = CharacterInfo(
            id = 1,
            imageUrl = "https://i.blogs.es/bc1dd2/naruto/840_560.png",
            name = "Naruto",
            description = stringResource(R.string.one_piece_description)
        )
    MyApplicationTheme {
        Box(modifier = Modifier.background(Color.Black)) {

            CharacterScreen(characterDetails = character)
        }
    }
}

