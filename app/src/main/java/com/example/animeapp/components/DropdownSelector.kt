package com.example.animeapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.domain.AnimeSort
import com.example.domain.AnimeType


@Composable
fun DropdownSelector(
    items: List<String>,
    selectedItem: String,
    onItemSelected: (String) -> Unit,
) {
    var expanded by rememberSaveable { mutableStateOf(false) }
    val text = rememberSaveable { mutableStateOf(selectedItem) }

    Box {
        Row(
            modifier = Modifier
                .clickable(onClick = { expanded = true })
                .background(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.08f))
                .padding(horizontal = 8.dp, vertical = 4.dp)
                .wrapContentSize()
                .align(Alignment.CenterStart)
        ) {
            Text(
                text = text.value,
                style = MaterialTheme.typography.bodySmall,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                Icons.Filled.ArrowDropDown,
                contentDescription = "Filter",
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            )
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            items.forEach { item ->
                DropdownMenuItem(onClick = {
                    text.value = item
                    onItemSelected(item)
                    expanded = false
                }, text = {
                    Text(text = item)
                }
                )
            }
        }
    }
}

@Composable
fun FilterOptions(
    type: AnimeType,
    sort: AnimeSort,
    onTypeSelected: (AnimeType) -> Unit,
    onSortSelected: (AnimeSort) -> Unit
) {
    val typeItems = AnimeType.values().map { it.name }

    val sortDisplayNames = mapOf(
        AnimeSort.POPULARITY_DESC to "Popularity (Descending)",
        AnimeSort.EPISODES_DESC to "Episodes (Descending)",
        AnimeSort.START_DATE to "Start Date"
    )

    val sortItems = AnimeSort.values().map { sortDisplayNames[it] ?: it.name }

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
            }
        )
        DropdownSelector(
            items = sortItems,
            selectedItem = sortDisplayNames[sort] ?: sort.name,
            onItemSelected = { selected ->
                val selectedSort = sortDisplayNames.entries.firstOrNull { it.value == selected }?.key
                if (selectedSort != null) {
                    onSortSelected(selectedSort)
                }
            }
        )
    }
}

