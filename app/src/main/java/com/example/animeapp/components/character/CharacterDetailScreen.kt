package com.example.animeapp.components.character

import androidx.compose.foundation.Image
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
import androidx.compose.ui.layout.ContentScale
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
        item {
            ShowDescriptionFormat(description = characterDetails.description)
        }
    }
}

@Preview
@Composable
fun PreviewCharacterScreen() {
    // create a mock CharacterInfo

    MyApplicationTheme {
        val mockCharacterDetails = CharacterInfo(
            id = 1,
            "example",
            imageUrl = "https://cdn.vox-cdn.com/thumbor/xBIBkXiGLcP-kph3pCX61U7RMPY=/0x0:1400x788/1200x800/filters:focal(588x282:812x506)/cdn.vox-cdn.com/uploads/chorus_image/image/70412073/0377c76083423a1414e4001161e0cdffb0b36e1f_760x400.0.png",
            description = "Example description"
        )
        CharacterScreen(characterDetails = mockCharacterDetails)
    }
    // call your CharacterScreen composable with the mock data
}
