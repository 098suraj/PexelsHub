package com.example.hey.navigation


import androidx.compose.animation.*
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.domain.model.PhotoFire
import com.example.domain.model.VideoFire
import com.example.hey.Screen.Details
import com.example.hey.Screen.Details.VideoDetailScreen

import com.example.hey.Screen.Home
import com.example.hey.Screen.ListPage
import com.example.hey.Screen.Video.VideoScreen


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SetupNavGraph(navHostController: NavHostController,route:String) {
    NavHost(
        navController = navHostController,
        startDestination = route
    )
    {
        composable(route = Screen.Home.route,

        ){
          Home(navHostController = navHostController)
        }
        composable(route=Screen.Photo.route,
           )
        {
         VideoScreen()
        }
        composable(route=Screen.Video.route,
           ){
          ListPage(navHostController = navHostController)
        }
        composable(route = Screen.Details.route){
            var result by remember {
                mutableStateOf<PhotoFire?>(null) }
            LaunchedEffect(key1 = it){
             result = navHostController.previousBackStackEntry?.savedStateHandle?.get<PhotoFire>("photoData")!!
                print(result!!.Content)
            }
            result?.let { it1 -> Details(navController = navHostController, it1) }
        }
        composable(route=Screen.VideoDetails.route){
            var result by remember {
                mutableStateOf<VideoFire?>(null) }
            LaunchedEffect(key1 = it){
                result = navHostController.previousBackStackEntry?.savedStateHandle?.get<VideoFire>("VideoData")!!

            }
            VideoDetailScreen(result)
        }


    }

}