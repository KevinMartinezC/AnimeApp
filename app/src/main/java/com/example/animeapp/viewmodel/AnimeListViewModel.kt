package com.example.animeapp.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animeapp.SearchUiState
import com.example.domain.Anime
import com.example.domain.AnimeSort
import com.example.domain.AnimeType
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

    private suspend fun fetchAnimeList(
        type: AnimeType,
        sort: List<AnimeSort>,
        search: String? = null
    ): List<Anime> {
        return getAnimeListUseCase(1, 50, type, sort, search)
    }

    private fun updateAnimeList(
        animeList: List<Anime>,
        type: AnimeType,
        sort: List<AnimeSort>
    ) {
        _uiState.update { it.copy(animeList = animeList, type = type, sort = sort) }
    }

    fun applyFilter(
        type: AnimeType,
        sort: List<AnimeSort>,
        search: String? = null
    ) {
        viewModelScope.launch {
            val animeList = fetchAnimeList(type, sort, search)
            updateAnimeList(animeList, type, sort)
        }
    }

    fun applySearch(search: String? = null) {
        viewModelScope.launch {
            val animeList = fetchAnimeList(uiState.value.type, uiState.value.sort, search)
            updateAnimeList(animeList, uiState.value.type, uiState.value.sort)
        }
    }
}