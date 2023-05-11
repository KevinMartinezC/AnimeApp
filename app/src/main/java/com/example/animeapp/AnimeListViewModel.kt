package com.example.animeapp

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.GetAnimeListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimeListViewModel @Inject constructor(
    private val getAnimeListUseCase: GetAnimeListUseCase
) : ViewModel() {

    fun loadAnimeList() {
        viewModelScope.launch {
            val animeList = getAnimeListUseCase(1,10)
            animeList.forEach {
                Log.d("MainViewModel", it.toString())
            }
        }
    }

}

