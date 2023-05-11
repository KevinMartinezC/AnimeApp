package com.example.animeapp.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.domain.AnimeSort
import com.example.domain.AnimeType

@Composable
fun DropdownSelector(
    items: List<String>,
    selectedItem: String,
    onItemSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val text = remember { mutableStateOf(selectedItem) }

    Box {
        Text(
            text = text.value,
            modifier = Modifier.clickable(onClick = { expanded = true })
        )
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
    val sortItems = AnimeSort.values().map { it.name }


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
            selectedItem = sort.name,
            onItemSelected = { selected ->
                onSortSelected(AnimeSort.valueOf(selected))
            }
        )
    }
}

