package com.example.animeapp.components.favorite.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animeapp.components.favorite.FavoriteUiState
import com.example.animeapp.components.favorite.UiState
import com.example.domain.model.favorite.FavoriteAnime
import com.example.domain.usecases.favorite.FavoriteAnimeUpdatesUseCase
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
class FavoriteViewModel @Inject constructor(
    private val getFavoriteAnimeUseCase: GetFavoriteAnimeUseCase,
    private val removeFavoriteAnimeUseCase: RemoveFavoriteAnimeUseCase,
    private val favoriteAnimeUpdatesUseCase: FavoriteAnimeUpdatesUseCase
) : ViewModel() {

    private val _favoriteAnimesList = MutableStateFlow<List<FavoriteAnime>>(emptyList())

    private val _uiState = MutableStateFlow(
        UiState(
            favoriteAnime = emptySet()
        )
    )

    private val _uiStateFavorite = MutableStateFlow(
        FavoriteUiState(
            removeFromFavorites = this::removeFromFavorites,
            favoriteAnimesList = _favoriteAnimesList
        )
    )

    val uiStateFavorite = _uiStateFavorite.asStateFlow()

    init {
        viewModelScope.launch {
            getFavoriteAnimeUseCase()
                .onEach { favoriteAnimeList ->
                    _favoriteAnimesList.value = favoriteAnimeList
                    _uiState.value =
                        _uiState.value.copy(favoriteAnime = favoriteAnimeList.map { it.id }.toSet())
                }.launchIn(viewModelScope)

            favoriteAnimeUpdatesUseCase().collect { updatedFavoriteAnime ->
                    _uiState.value = _uiState.value.copy(favoriteAnime = updatedFavoriteAnime)
                }
        }
    }

    private fun removeFromFavorites(animeId: Int) {
        viewModelScope.launch {
            if (_uiState.value.favoriteAnime.contains(animeId)) {
                removeFavoriteAnimeUseCase(animeId)
            }
        }
    }
}