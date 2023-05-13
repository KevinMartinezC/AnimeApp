package com.example.animeapp.components.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.animeapp.components.detail.viewmodel.DetailScreenViewModel

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