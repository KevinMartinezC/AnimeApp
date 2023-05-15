package com.example.animeapp.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.animeapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarWithFavoriteIcon(onFavoriteIconClick: () -> Unit) {
    TopAppBar(
        title = { Text(text = stringResource(R.string.anime)) },
        actions = {
            IconButton(onClick = onFavoriteIconClick) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = stringResource(R.string.favorite)
                )
            }
        }
    )
}
