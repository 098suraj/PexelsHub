package com.example.hey.Screen.Details

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import com.example.domain.model.VideoFile
import com.example.domain.model.VideoFire
import com.example.hey.Screen.Video.VerticalPlayer
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.VerticalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlin.math.abs


@OptIn(ExperimentalPagerApi::class)
@Composable
fun VideoDetailScreen(result: VideoFire?) {
    val pagerState = rememberPagerState()
    var isMuted by remember {
        mutableStateOf(false)
    }
    val isFristItem by remember(pagerState) {
        derivedStateOf {
            pagerState.currentPage == 0
        }
    }
    LaunchedEffect(key1 = pagerState) {
        snapshotFlow { pagerState.currentPage }.distinctUntilChanged().collect {
            pagerState.animateScrollToPage(it)
        }
    }
    Box {
        VerticalPager(
            count = 1,
            state = pagerState,
            horizontalAlignment = Alignment.CenterHorizontally,
            itemSpacing = 10.dp
        ) {index->
            val shouldPlay by remember(pagerState) {
                derivedStateOf {
                    (abs(currentPageOffset) < .5 && currentPage == index) || (abs(
                        currentPageOffset
                    ) > .5 && pagerState.targetPage == index)
                }
            }
            if (result != null) {
                VerticalPlayer(
                    video =result,
                    shouldPlay = shouldPlay ,
                    isMuted = isMuted,
                    onMuted = {
                        isMuted=it
                    },
                    isScrolling =pagerState.isScrollInProgress
                )
            }

        }
    }

}
