package com.example.animeapp


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.Anime
import com.example.domain.GetAnimeListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimeListViewModel @Inject constructor(
    private val getAnimeListUseCase: GetAnimeListUseCase
) : ViewModel() {
    private val _animeList = MutableStateFlow<List<Anime>>(emptyList())
    val animeList: StateFlow<List<Anime>> get() = _animeList

    init {
        viewModelScope.launch {
            val result = getAnimeListUseCase(1, 20) // Fetch the first 20 items
            _animeList.value = result
        }
    }
}
