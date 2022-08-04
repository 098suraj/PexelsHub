package com.example.hey

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

import com.example.hey.Screen.BottomNavigation
import com.example.hey.Screen.Home.HomeViewModel
import com.example.hey.navigation.Screen
import com.example.hey.navigation.SetupNavGraph
import com.example.hey.ui.theme.AppContentColor
import com.example.hey.ui.theme.AppThemeColor
import com.example.hey.ui.theme.HeyTheme

import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            HeyTheme() {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                   val  navController = rememberNavController()

                  Main(navController)

                }
            }
        }
    }
}

@Composable
fun Main(navController: NavHostController,homeViewModel: HomeViewModel= hiltViewModel()) {
    Scaffold(
        backgroundColor = MaterialTheme.colors.AppThemeColor,
        contentColor = MaterialTheme.colors.AppContentColor,
        bottomBar ={ BottomNavigation(navController = navController) },
    ){
        it
        LaunchedEffect(Unit ){
            delay(2000)
        }
        SetupNavGraph(navHostController = navController,Screen.Home.route)
    }
}

