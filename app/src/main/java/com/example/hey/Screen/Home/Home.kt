package com.example.hey.Screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.domain.model.PhotoFire
import com.example.domain.model.VideoFire
import com.example.hey.R
import com.example.hey.Screen.Home.HomeViewModel
import com.example.hey.Screen.Movie.MovieListContent
import com.example.hey.navigation.Screen
import com.example.hey.ui.theme.AppContentColor
import com.example.hey.ui.theme.AppThemeColor
import com.example.hey.utils.DataProvider
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Home(navHostController: NavHostController, homeViewModel: HomeViewModel = hiltViewModel()) {
    val systemUiController = rememberSystemUiController()
    val systemBarColor = MaterialTheme.colors.AppThemeColor
//    val allPhotos = homeViewModel.getPhotos.collectAsLazyPagingItems();
    val state=homeViewModel.uiState.collectAsState().value
    val Videostate=homeViewModel.uiVideoState.collectAsState().value

    // val allVideos = homeViewModel.getVideos.collectAsLazyPagingItems()


    SideEffect {
        systemUiController.setStatusBarColor(
            color = systemBarColor
        )

    }

    Scaffold(
        backgroundColor = MaterialTheme.colors.AppThemeColor,
        contentColor = MaterialTheme.colors.AppContentColor,

        content = {
            Column(
                Modifier.padding(start = 20.dp, end = 20.dp, top = 20.dp),
                horizontalAlignment = Alignment.Start,
            ) {
                Top()
                Spacer(modifier = Modifier.height(20.dp))
                when(Videostate){
                    is HomeViewModel.VideoAppState.Loading ->    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CircularProgressIndicator()
                    }
                    is HomeViewModel.VideoAppState.Loaded-> {
                        HorizontalScreen(allPhotos = Videostate.list, navHostController)
                    }
                    else -> {}
                }
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




            }


        }
    )

}


@Composable
fun Top() {
    Column(
        horizontalAlignment = Alignment.Start,

        ) {
        Text(
            text = buildAnnotatedString {
                append("HEY \nSnigdha")
                addStyle(
                    style = SpanStyle(
                        color = MaterialTheme.colors.AppContentColor
                    ),
                    start = 0,
                    end = 4
                )
                addStyle(
                    style = SpanStyle(
                        color = colorResource(id = R.color.teal_200)
                    ),
                    start = 5,
                    end = 7
                )
            },
            fontSize = 20.sp,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Bold,
            color = Color.Cyan,
            textAlign = TextAlign.Start

        )
    }
}


@Composable
fun BottomNavigation(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.arguments?.getString("route")

    BottomNavigation(backgroundColor = MaterialTheme.colors.AppThemeColor) {
        DataProvider.botttomItems.forEach { Screen ->
            BottomNavigationItem(
                icon = { BottomBarIcon(Screen) },
                selected = currentRoute == Screen.route,
                onClick = {
                    navController.popBackStack(navController.graph.startDestinationId, false)
                    if (currentRoute != Screen.route) {
                        navController.navigate(Screen.route)
                    }
                },

                )
        }
    }
}


@Composable
fun BottomBarIcon(screen: Screen) {
    when (screen) {
        Screen.Home -> Icon(imageVector = Icons.Filled.Home, contentDescription = null)
        Screen.Photo -> Icon(imageVector =Icons.Filled.PlayArrow, contentDescription = null)
        Screen.Video -> Icon(imageVector = Icons.Filled.Favorite, contentDescription = null)
        else -> {}
    }
}


//@Preview(showBackground = true)
//@Composable
//fun NameDemo() {
//    HeyTheme() {
//        Top(Modifier)
//    }
//}

