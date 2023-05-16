package com.example.animeapp.components.favorite.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animeapp.components.favorite.FavoriteUiState
import com.example.animeapp.components.favorite.UiState
import com.example.domain.model.favorite.FavoriteAnime
import com.example.domain.usecases.favorite.GetFavoriteAnimeUseCase
import com.example.domain.usecases.favorite.RemoveFavoriteAnimeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel@Inject constructor(
    private val getFavoriteAnimeUseCase: GetFavoriteAnimeUseCase,
    private val removeFavoriteAnimeUseCase: RemoveFavoriteAnimeUseCase
) : ViewModel()  {

    private val _favoriteAnimes = MutableStateFlow<List<FavoriteAnime>>(emptyList())

    private val _uiState = MutableStateFlow(
        UiState(
            favoriteAnime = emptySet()
        )
    )


    private val _uiStateFavorite = MutableStateFlow(
        FavoriteUiState(
            removeFromFavorites = this::removeFromFavorites,
            favoriteAnimes = _favoriteAnimes
        )
    )

    val uiStateFavorite = _uiStateFavorite.asStateFlow()


    init {
        viewModelScope.launch {
            getFavoriteAnimeUseCase()
                .onEach { favoriteAnimeList ->
                    _favoriteAnimes.value = favoriteAnimeList
                    _uiState.value = _uiState.value.copy(favoriteAnime = favoriteAnimeList.map { it.id }.toSet())
                }.launchIn(viewModelScope)
        }
    }


    private fun removeFromFavorites(animeId: Int) {
        viewModelScope.launch {
            removeFavoriteAnimeUseCase(animeId)
            updateFavoriteAnimeUiState(_uiState.value.favoriteAnime - animeId)
        }
    }

    private fun updateFavoriteAnimeUiState(updatedFavoriteAnime: Set<Int>) {
        _uiState.value = _uiState.value.copy(favoriteAnime = updatedFavoriteAnime)
    }
}