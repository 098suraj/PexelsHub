package com.example.hey.Screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.domain.model.PhotoDataModel
import com.example.hey.Screen.Details.DogInfoCard
import com.example.hey.ui.theme.AppContentColor
import com.example.hey.ui.theme.AppThemeColor
import com.example.hey.ui.theme.TitleColor
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


@Composable
@Destination
fun Details(navController:DestinationsNavigator) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Details") },
                backgroundColor = MaterialTheme.colors.AppThemeColor,
                contentColor = MaterialTheme.colors.AppContentColor,
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null,
                        modifier = Modifier
                            .size(24.dp, 24.dp)
                            .clickable {
                                navController.navigateUp()
                            },
                        tint = MaterialTheme.colors.AppContentColor
                    )
                }
            )
        },

        content = {
//            DetailsView(dog)
        }
    )
}

@Composable
fun DetailsView(dog: PhotoDataModel.Photo) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.AppThemeColor)
    ) {



        if (dog != null) {
            print(dog.photographerId)
        }
        // Basic details
        item {
            dog?.apply {

                val dogImage: Painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current).data(data = url)
                        .apply(block = fun ImageRequest.Builder.() {
                            crossfade(true)
                        }).build()
                )
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(346.dp),
                    painter = dogImage,

                    contentDescription = "",
                    contentScale = ContentScale.FillHeight
                )


                photographerId?.let { DogInfoCard(it.toString()) }
            }
        }

        // My story details
        item {
            dog?.apply {
                Title(title = "Content")
                Spacer(modifier = Modifier.height(16.dp))
                photographer?.let {
                    Text(
                        text = it,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp, 0.dp, 16.dp, 0.dp),
                        color = MaterialTheme.colors.AppContentColor,
                        style = MaterialTheme.typography.body2,
                        textAlign = TextAlign.Start
                    )
                }
            }
        }
    }
}

@Composable
fun Title(title: String) {
    Text(
        text = title,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 0.dp, 0.dp, 0.dp),
        color = MaterialTheme.colors.TitleColor,
        style = MaterialTheme.typography.subtitle1,
        fontWeight = FontWeight.W600,
        textAlign = TextAlign.Start
    )
}
