package com.example.animeapp.components.search.filter

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.animeapp.R


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
                contentDescription = stringResource(R.string.filter),
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
                DropdownMenuItem(
                    onClick = {
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
