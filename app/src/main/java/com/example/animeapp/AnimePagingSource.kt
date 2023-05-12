package com.example.animeapp

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.domain.Anime
import com.example.domain.AnimeSort
import com.example.domain.AnimeType
import com.example.domain.usecases.GetAnimeListUseCase

class AnimePagingSource(

    private val getAnimeListUseCase: GetAnimeListUseCase,
    private val type: AnimeType,
    private val sort: List<AnimeSort>,
    private val search: String?
) : PagingSource<Int, Anime>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Anime> {
        val page = params.key ?: 1
        return try {
            val data = getAnimeListUseCase(
                page = page,
                perPage = 10,
                type = type,
                sort = sort,
                search = search
            )

            LoadResult.Page(
                data = data,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (data.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Anime>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}