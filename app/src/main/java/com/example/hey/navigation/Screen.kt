package com.example.hey.navigation

sealed class Screen(val route:String,){
    object Home:Screen(route = "Home")
    object Video:Screen(route = "Video")
    object Photo :Screen(route="photos")
    object Details:Screen(route="Details")
    object VideoDetails:Screen(route="VideoDetails")
}
