package com.example.animeapp.components.search.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.animeapp.utils.AnimePagingSource
import com.example.animeapp.components.search.SearchUiState
import com.example.domain.model.search.Anime
import com.example.domain.model.search.AnimeSort
import com.example.domain.model.search.AnimeType
import com.example.domain.usecases.GetAnimeListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

private const val PAGE_SIZE = 10
@HiltViewModel
class AnimeListViewModel @Inject constructor(
    private val getAnimeListUseCase: GetAnimeListUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(
        SearchUiState(
            animeList = emptyList(),
            type = AnimeType.ANIME,
            sort = emptyList(),
            search = null
        )
    )

    val uiState = _uiState.asStateFlow()

    private val _type = MutableStateFlow(AnimeType.ANIME)
    private val _sort = MutableStateFlow<List<AnimeSort>>(emptyList())
    private val _search = MutableStateFlow<String?>(null)

    private fun createPager(
        type: AnimeType,
        sort: List<AnimeSort>,
        search: String?
    ): Pager<Int, Anime> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { AnimePagingSource(getAnimeListUseCase, type, sort, search) }
        )
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    val animeFlow: Flow<PagingData<Anime>> = combine(_type, _sort, _search) { type, sort, search ->
        Triple(type, sort, search)
    }.flatMapLatest { (type, sort, search) ->
        createPager(type, sort, search).flow
    }.cachedIn(viewModelScope)

    fun applyFilter(
        type: AnimeType,
        sort: List<AnimeSort>,
        search: String? = null
    ) {
        _type.value = type
        _sort.value = sort
        _search.value = search
    }

    fun applySearch(search: String? = null) {
        _search.value = search
    }
}