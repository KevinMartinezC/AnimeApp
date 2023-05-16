package com.example.animeapp.components.favorite

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerDefaults
import androidx.compose.foundation.pager.PagerSnapDistance
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.animeapp.R
import com.example.animeapp.components.favorite.utils.isLandscape
import com.example.animeapp.theme.MyApplicationTheme
import com.example.domain.model.favorite.FavoriteAnime
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


const val CARD_WIDTH_FACTOR = 0.7f
const val CARD_HEIGHT_FACTOR = 0.7f
const val CARD_WIDTH_FACTOR_LANDSCAPE = 0.4f
const val CARD_HEIGHT_FACTOR_LANDSCAPE = 0.5f
const val PAGER_SNAP_DISTANCE = 4

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FavoriteScreen(
    favoriteAnimeFlow: StateFlow<List<FavoriteAnime>>,
    removeFromFavorites: (Int) -> Unit,
) {

    val favoriteArticles by favoriteAnimeFlow.collectAsState(initial = emptyList())
    val pagerState = rememberPagerState()
    val fling = PagerDefaults.flingBehavior(
        state = pagerState,
        pagerSnapDistance = PagerSnapDistance.atMost(PAGER_SNAP_DISTANCE)
    )
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val isLandscape = isLandscape()
    val cardWidthFactor = if (isLandscape) CARD_WIDTH_FACTOR_LANDSCAPE else CARD_WIDTH_FACTOR
    val cardHeightFactor = if (isLandscape) CARD_HEIGHT_FACTOR_LANDSCAPE else CARD_HEIGHT_FACTOR
    val cardWidth = screenWidth * cardWidthFactor
    val cardHeight = screenHeight * cardHeightFactor
    val padding = (screenWidth - cardWidth) / 2

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        HorizontalPager(
            pageCount = if (favoriteArticles.isEmpty()) 1 else favoriteArticles.size,
            state = pagerState,
            flingBehavior = fling,
            contentPadding = PaddingValues(start = padding, end = padding)
        ) { page ->
            if (favoriteArticles.isEmpty()) {
                Text(
                    text = stringResource(R.string.no_favorites_added_yet),
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )
            } else {
                val favoriteArticle = favoriteArticles[page]
                FavoriteAnimeItem(
                    favoriteArticle = favoriteArticle,
                    pagerState = pagerState,
                    currentPage = page,
                    modifier = Modifier.size(cardWidth, cardHeight),
                    removeFromFavorites = { article -> removeFromFavorites(article.id) },
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewFavoriteScreen() {
    val favoriteAnimeList = MutableStateFlow(
        listOf(
            FavoriteAnime(
                id = 1,
                title = "Anime 1",
                imageUrl = "https://i.blogs.es/bc1dd2/naruto/840_560.png"
            ),
            FavoriteAnime(
                id = 2,
                title = "Anime 2",
                imageUrl = "https://i.blogs.es/bc1dd2/naruto/840_560.png"
            ),
            FavoriteAnime(
                id = 3,
                title = "Anime 3",
                imageUrl = "https://i.blogs.es/bc1dd2/naruto/840_560.png"
            )
        )
    )
    MyApplicationTheme {
        FavoriteScreen(
            favoriteAnimeFlow = favoriteAnimeList,
            removeFromFavorites = { /* Do something here */ }
        )
    }
}



