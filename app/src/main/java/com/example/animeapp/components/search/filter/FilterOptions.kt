package com.example.animeapp.components.search.filter

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.animeapp.R
import com.example.animeapp.components.search.utils.AnimeSortUtils
import com.example.animeapp.components.search.utils.mapDisplayNameToAnimeSort
import com.example.animeapp.theme.MyApplicationTheme
import com.example.domain.model.search.AnimeSort
import com.example.domain.model.search.AnimeType


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
            .padding(vertical = dimensionResource(id = R.dimen.padding_8dp))
    ) {
        DropdownSelector(
            items = typeItems,
            selectedItem = type.name,
            onItemSelected = { selected ->
                onTypeSelected(AnimeType.valueOf(selected))
            },
            modifier = Modifier.testTag("typeDropdown")

        )
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

@Preview
@Composable
fun PreviewFilterOptions() {
    val selectedType = AnimeType.ANIME
    val selectedSort = AnimeSort.EPISODES_DESC
    MyApplicationTheme {
        FilterOptions(
            type = selectedType,
            sort = selectedSort,
            onTypeSelected = {
                // Handle type selection
            },
            onSortSelected = {
                // Handle sort selection
            }
        )
    }
}