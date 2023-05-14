package com.example.animeapp.components.search.filter

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.animeapp.components.search.utils.AnimeSortUtils
import com.example.animeapp.components.search.utils.mapDisplayNameToAnimeSort
import com.example.domain.model.AnimeSort
import com.example.domain.model.AnimeType


@Composable
fun FilterOptions(
    type: AnimeType,
    sort: AnimeSort,
    onTypeSelected: (AnimeType) -> Unit,
    onSortSelected: (AnimeSort) -> Unit
) {
    val typeItems = AnimeType.values().map { it.name }
    val sortItems = AnimeSort.values().map { AnimeSortUtils.sortDisplayNames[it] ?: it.name }

    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        DropdownSelector(
            items = typeItems,
            selectedItem = type.name,
            onItemSelected = { selected ->
                onTypeSelected(AnimeType.valueOf(selected))
            })
        DropdownSelector(
            items = sortItems,
            selectedItem = AnimeSortUtils.sortDisplayNames[sort] ?: sort.name,
            onItemSelected = { selected ->
                mapDisplayNameToAnimeSort(
                    selected, AnimeSortUtils.sortDisplayNames
                )?.let { selectedSort ->
                    onSortSelected(selectedSort)
                }
            }
        )
    }
}