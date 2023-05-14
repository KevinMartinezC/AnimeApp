package com.example.animeapp.components.detail.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.AnimeDetails
import com.example.domain.usecases.GetAnimeDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailScreenViewModel @Inject constructor(
    private val getAnimeDetailsUseCase: GetAnimeDetailsUseCase
) : ViewModel() {

    private val _animeDetails = MutableStateFlow<AnimeDetails?>(null)
    val animeDetails: StateFlow<AnimeDetails?> = _animeDetails

    fun fetchAnimeDetails(id: Int) {
        viewModelScope.launch {
            try {
                val details = getAnimeDetailsUseCase(id)
                _animeDetails.value = details
            } catch (e: Exception) {
                Log.d("Error","${e.message}")
            }
        }
    }
}