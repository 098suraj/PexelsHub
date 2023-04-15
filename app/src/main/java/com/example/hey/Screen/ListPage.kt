package com.example.hey.Screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.hey.Screen.Home.HomeViewModel

import com.example.hey.Screen.Movie.PhotoListContent

import com.example.hey.ui.theme.AppContentColor
import com.example.hey.ui.theme.AppThemeColor
import com.google.accompanist.pager.*
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
@Destination
fun ListPage(navHostController: DestinationsNavigator) {
    val pagerState = rememberPagerState(0)



    Column() {
        Spacer(modifier = Modifier.height(40.dp))
        Tabs(pagerState = pagerState)
       // TabsContent(pagerState, navHostController)
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
    val allPhotos = viewModel.photo().collectAsLazyPagingItems()
    //  val allVideos=viewModel.video().collectAsLazyPagingItems()

    if (data == "Photo") {
   //     PhotoListContent(navController = navHostController, allPhotos)
    }
}
