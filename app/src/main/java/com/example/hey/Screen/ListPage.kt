package com.example.hey.Screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.domain.model.PhotoFire
import com.example.domain.model.VideoFire
import com.example.hey.Screen.Home.HomeViewModel
import com.example.hey.Screen.Movie.MovieListContent
import com.example.hey.Screen.Video.VideoListContent
import com.example.hey.ui.theme.AppContentColor
import com.example.hey.ui.theme.AppThemeColor
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ListPage(navHostController: NavHostController) {
    val pagerState = rememberPagerState(0)


    //  val allPhotos=viewModel.getPhotos.collectAsLazyPagingItems()
    // val allVideos=viewModel.getVideos.collectAsLazyPagingItems()

    Column() {
        Spacer(modifier = Modifier.height(40.dp))
        Tabs(pagerState = pagerState)
        TabsContent(pagerState, navHostController)
    }

}

val list = listOf(
    "Photos",
    "Videos",
)

@ExperimentalPagerApi
@Composable
fun Tabs(pagerState: PagerState) {
    val scope = rememberCoroutineScope()
    TabRow(

        selectedTabIndex = pagerState.currentPage,
        backgroundColor = MaterialTheme.colors.AppThemeColor,
        contentColor = MaterialTheme.colors.AppContentColor,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
                height = 2.dp,
                color = MaterialTheme.colors.AppContentColor
            )
        }
    ) {

        list.forEachIndexed { index, _ ->
            Tab(
                text = {
                    Text(
                        list[index], color = MaterialTheme.colors.AppContentColor
                    )
                },

                selected = pagerState.currentPage == index,

                onClick = {

                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                }
            )
        }
    }
}


@ExperimentalPagerApi
@Composable
fun TabsContent(
    pagerState: PagerState,
    navHostController: NavHostController,
) {

    HorizontalPager(state = pagerState, count = list.size) { page ->
        when (page) {

            0 -> TabContentScreen(data = "Photo", navHostController)
            1 -> TabContentScreen(data = "Video", navHostController)

        }
    }
}

@Composable
fun TabContentScreen(
    data: String,
    navHostController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel()
    ) {

    val state=viewModel.uiState.collectAsState().value
    val Videostate=viewModel.uiVideoState.collectAsState().value
    if (data == "Photo") {
        when(state){
            is HomeViewModel.PhotoAppState.Loading ->    Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
            }
            is HomeViewModel.PhotoAppState.Loaded-> {
                MovieListContent(navController = navHostController,state.list)
            }
            else -> {}
        }
    } else {
        when(Videostate){
            is HomeViewModel.VideoAppState.Loading ->    Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
            }
            is HomeViewModel.VideoAppState.Loaded-> {
                VideoListContent(allVideos = Videostate.list, navController = navHostController)
            }
            else -> {}
        }

    }

}