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
import androidx.compose.ui.unit.dp
import com.example.animeapp.ui.theme.AnimeAppTheme
import com.example.domain.FavoriteAnime
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

    AnimeAppTheme {
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
                        text = "No Favorite Add it yet",
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                } else {
                    val favoriteArticle = favoriteArticles[page]
                    FavoriteArticleItem(
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
}
