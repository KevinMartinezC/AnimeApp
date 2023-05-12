package com.example.animeapp.components

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.animeapp.components.navigation.BottomBar
import com.example.animeapp.components.navigation.BottomNavGraph

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomBar(navController = navController) },
    ) { innerPadding ->
        BottomNavGraph(
            navController = navController,
            contentPadding = innerPadding
        )
    }
}

