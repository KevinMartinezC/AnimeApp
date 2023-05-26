package com.example.animeapp.components.character

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.animeapp.R
import com.example.animeapp.components.character.viewmodel.CharacterScreenViewModel
import com.example.animeapp.components.navigation.BottomNavItem
import com.example.animeapp.components.topbar.TopBarWithFavoriteIcon
import com.example.animeapp.components.utils.ShowDescriptionFormat
import com.example.animeapp.theme.MyApplicationTheme
import com.example.domain.model.character.CharacterInfo

@Composable
fun CharacterScreen(
    characterId: Int,
    navController: NavHostController,
    characterViewModel: CharacterScreenViewModel = hiltViewModel()

) {
    val characterDetails by characterViewModel.characterDetails.collectAsState()

    characterDetails?.let { character ->
        Scaffold(
            topBar = {
                TopBarWithFavoriteIcon {
                    navController.navigate(BottomNavItem.Favorite.route)
                }
            }
        ) { padding ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
            ) {
                CharacterScreenContent(characterDetails = character)
            }
        }
    } ?: run {
        characterViewModel.fetchCharacterDetails(characterId)
    }
}

@Composable
fun CharacterScreenContent(
    characterDetails: CharacterInfo
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.spacing_8dp)),
        modifier = Modifier
            .padding(
                start = dimensionResource(id = R.dimen.padding_20dp),
                end = dimensionResource(id = R.dimen.padding_20dp)
            )
            .testTag("CharacterScreenContent"),
    ) {
        item {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxWidth(),
                ) {
                if (LocalInspectionMode.current){
                    Image(
                        painter = painterResource(R.drawable.imagen1),
                        contentDescription = stringResource(R.string.image_character),
                        modifier = Modifier
                            .size(dimensionResource(id = R.dimen.size_200))
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
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
private fun PreviewCharacterScreen() {
        val character = CharacterInfo(
            id = 1,
            imageUrl = "https://i.blogs.es/bc1dd2/naruto/840_560.png",
            name = "Naruto",
            description = stringResource(R.string.one_piece_description)
        )
    MyApplicationTheme {
        Box(modifier = Modifier.background(Color.Black)) {

            CharacterScreenContent(characterDetails = character)
        }
    }
}

