package com.example.animeapp

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.AnimeRepositoryImpl
import com.example.domain.AnimeRepository
import kotlinx.coroutines.launch

class AnimeListViewModel() : ViewModel() {
    private val animeRepository: AnimeRepository = AnimeRepositoryImpl()

    fun fetchAnimeList() {
        viewModelScope.launch {
            val animeList = animeRepository.getAnimeList(1, 10)
            animeList.forEach {
                Log.d("MainViewModel", it.toString())
            }
        }
    }

}