package com.example.animeapp.components.character

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.animeapp.components.character.viewmodel.CharacterScreenViewModel

@Composable
fun CharacterDetailScreen(characterId: Int, viewModel: CharacterScreenViewModel = hiltViewModel()) {
    val characterDetails by viewModel.characterDetails.collectAsState()

    characterDetails?.let { character ->
        CharacterDetailsContent(characterDetails = character)
    } ?: run {
        viewModel.fetchCharacterDetails(characterId)
    }
}