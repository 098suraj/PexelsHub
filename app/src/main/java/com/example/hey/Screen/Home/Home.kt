package com.example.hey.Screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.PlayArrow
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
import androidx.paging.compose.collectAsLazyPagingItems

import com.example.hey.R
import com.example.hey.Screen.Home.HomeViewModel
import com.example.hey.Screen.Movie.PhotoListContent
import com.example.hey.navigation.Screen
import com.example.hey.ui.theme.AppContentColor
import com.example.hey.ui.theme.AppThemeColor
import com.example.hey.utils.DataProvider
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@RootNavGraph(start = true)
@Destination
@Composable
fun Home(navController: DestinationsNavigator, homeViewModel: HomeViewModel = hiltViewModel()) {
    val systemUiController = rememberSystemUiController()
    val systemBarColor = MaterialTheme.colors.AppThemeColor
    val getPhotos = homeViewModel.photo().collectAsLazyPagingItems()
  //  val allVideos = homeViewModel.video().collectAsLazyPagingItems()


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
                PhotoListContent( photo =getPhotos )
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
        Screen.Photo -> Icon(imageVector = Icons.Filled.PlayArrow, contentDescription = null)
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

