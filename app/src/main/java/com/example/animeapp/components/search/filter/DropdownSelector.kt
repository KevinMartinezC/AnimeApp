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
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.example.animeapp.R

private const val MAX_LINE_TEXT_VALUE = 1
private const val ALPHA_VALUE = 0.08f
@Composable
fun DropdownSelector(
    items: List<String>,
    selectedItem: String,
    onItemSelected: (String) -> Unit,
    modifier: Modifier = Modifier,

) {

    var expanded by rememberSaveable { mutableStateOf(false) }
    val text = rememberSaveable { mutableStateOf(selectedItem) }

    Box {
        Row(
            modifier = modifier
                .clickable(onClick = { expanded = true })
                .background(MaterialTheme.colorScheme.onSurface.copy(alpha = ALPHA_VALUE))
                .padding(
                    horizontal = dimensionResource(id = R.dimen.padding_8dp),
                    vertical = dimensionResource(id = R.dimen.padding_4dp),
                )
                .wrapContentSize()
                .align(Alignment.CenterStart)
        ) {
            Text(
                text = text.value,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = MAX_LINE_TEXT_VALUE,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.wight_8dp)))
            Icon(
                Icons.Filled.ArrowDropDown,
                contentDescription = stringResource(R.string.filter),
                tint = MaterialTheme.colorScheme.surfaceTint,
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

@Preview
@Composable
fun PreviewDropdownSelector() {
    val items = listOf("Item 1")
    var selectedItem by remember { mutableStateOf(items.first()) }
MaterialTheme{
    DropdownSelector(
        items = items,
        selectedItem = selectedItem,
        onItemSelected = { item ->
            selectedItem = item
        }
    )
}
}
