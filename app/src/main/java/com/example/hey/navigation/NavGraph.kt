package com.example.hey.navigation


import androidx.compose.animation.*
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.hey.Screen.Details

import com.example.hey.Screen.Home
import com.example.hey.Screen.ListPage
//
//
//@OptIn(ExperimentalAnimationApi::class)
//@Composable
//fun SetupNavGraph(navHostController: NavHostController, route: String) {
//    NavHost(
//        navController = navHostController,
//        startDestination = route
//    )
//    {
//        composable(
//            route = Screen.Home.route,
//
//            ) {
//            Home(navHostController = navHostController)
//        }
//        composable(
//            route = Screen.Photo.route,
//        )
//        {
//          //  VideoScreen()
//        }
//        composable(
//            route = Screen.Video.route,
//        ) {
//            ListPage(navHostController = navHostController)
//      }
//
//        composable(route = Screen.Details.route) {
//            Details(navController = navHostController)
//        }
//        composable(route = Screen.VideoDetails.route) {
//
//           // VideoDetailScreen()
//        }
//
//
//    }
//
//}