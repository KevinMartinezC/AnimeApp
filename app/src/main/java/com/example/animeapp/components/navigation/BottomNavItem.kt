package com.example.animeapp.components.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Search : BottomNavItem(
        route = "search",
        title = "Search",
        icon = Icons.Default.Search
    )

    object Favorite : BottomNavItem(
        route = "favorite",
        title = "Favorite",
        icon = Icons.Default.Star
    )

    companion object {
        val ALL: List<BottomNavItem>
            get() = listOf(
                Search,
                Favorite
            )
    }
}
