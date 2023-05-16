package com.example.animeapp.components.topbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.animeapp.R
import com.example.animeapp.theme.MyApplicationTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarWithFavoriteIcon(onFavoriteIconClick: () -> Unit) {
    TopAppBar(
        colors = topAppBarColors(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer,
            actionIconContentColor = MaterialTheme.colorScheme.onTertiaryContainer
        ),
        title = { Text(text = stringResource(R.string.anime)) },
        actions = {
            IconButton(onClick = onFavoriteIconClick) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = stringResource(R.string.favorite),
                )
            }
        }
    )
}

@Preview
@Composable
fun TopBarWithFavoriteIconPreview() {
    MyApplicationTheme {
        TopBarWithFavoriteIcon(onFavoriteIconClick = { /* Do something here */ })
    }
}

