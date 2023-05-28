package com.example.animeapp.components.character.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.character.CharacterInfo
import com.example.domain.usecases.GetCharacterInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterScreenViewModel  @Inject constructor(
    private val getCharacterInfoUseCase: GetCharacterInfoUseCase
) : ViewModel() {

    private val _characterDetails = MutableStateFlow<CharacterInfo?>(null)
    val characterDetails: StateFlow<CharacterInfo?> = _characterDetails

    fun fetchCharacterDetails(id: Int) {
        viewModelScope.launch {
            runCatching { getCharacterInfoUseCase(id) }
                .onSuccess { details -> _characterDetails.value = details }
                .onFailure { e -> Log.d("Error", "${e.message}") }
        }
    }
}