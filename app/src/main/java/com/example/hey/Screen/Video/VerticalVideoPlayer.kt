package com.example.hey.Screen.Video

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.domain.model.VideoFire
import com.example.hey.Screen.Home.HomeViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.VerticalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlin.math.abs

var videoFile = VideoFire(
    Videourl ="https://drive.google.com/file/d/1TQ7fmLSF-ocVDaBxTtzPVKtugJSKwCgg/view?usp=sharing",
    photourl = "",
    by="Suraj",
    Content = "hey"
)

@OptIn(ExperimentalPagerApi::class)
@Composable
fun VideoScreen(homeViewModel: HomeViewModel = hiltViewModel()) {
    //  val allVideos=viewModel.videoList.collectAsLazyPagingItems()
    val Videostate=homeViewModel.uiVideoState.collectAsState().value

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

    when(Videostate){
        is HomeViewModel.VideoAppState.Loading ->    Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator()
        }
        is HomeViewModel.VideoAppState.Loaded-> {

            Box {
                VerticalPager(
                    count = Videostate.list.size,
                    state = pagerState,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    itemSpacing = 10.dp
                ) { index ->
                    val shouldPlay by remember(pagerState) {
                        derivedStateOf {
                            (abs(currentPageOffset) < .5 && currentPage == index) || (abs(
                                currentPageOffset
                            ) > .5 && pagerState.targetPage == index)
                        }
                    }

                    VerticalPlayer(
                        video = Videostate.list[index]?: videoFile,
                        shouldPlay = shouldPlay,
                        isMuted = isMuted,
                        onMuted = {
                            isMuted = it
                        },
                        isScrolling = pagerState.isScrollInProgress
                    )

                }
            }




        }
        else -> {}
    }




}
