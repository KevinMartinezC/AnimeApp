package com.example.animeapp.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animeapp.SearchUiState
import com.example.domain.usecases.GetAnimeListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimeListViewModel @Inject constructor(
    private val getAnimeListUseCase: GetAnimeListUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        SearchUiState(
            animeList = emptyList()
        )
    )

    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            val result = getAnimeListUseCase(1, 50)
            if (result.isNotEmpty()) {
                _uiState.update { it.copy(animeList = result) }
            }
        }
    }
}
