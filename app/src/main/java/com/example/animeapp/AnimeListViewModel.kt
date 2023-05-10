package com.example.animeapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.AnimeRepositoryImpl
import com.example.data.GetAnimeListQuery
import com.example.domain.AnimeRepository
import kotlinx.coroutines.launch

class AnimeListViewModel(private val animeRepository: AnimeRepositoryImpl) : ViewModel() {


}