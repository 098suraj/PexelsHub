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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.domain.model.PhotoFire
import com.example.hey.Screen.Details.DogInfoCard
import com.example.hey.ui.theme.AppContentColor
import com.example.hey.ui.theme.AppThemeColor
import com.example.hey.ui.theme.ItemBackgroundColor
import com.example.hey.ui.theme.TitleColor


@Composable
fun Details(navController: NavController, photoFire: PhotoFire) {
    var dog by remember {
        mutableStateOf<PhotoFire?>(null)
    }
    dog=photoFire
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
            DetailsView(dog)
        }
    )
}

@Composable
fun DetailsView(dog: PhotoFire?) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colors.AppThemeColor)
    ) {



        if (dog != null) {
            print(dog.Content)
        }
        // Basic details
        item {
            dog?.apply {

                val dogImage: Painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current).data(data = photourl)
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


                by?.let { DogInfoCard(it) }
            }
        }

        // My story details
        item {
            dog?.apply {
                Title(title = "Content")
                Spacer(modifier = Modifier.height(16.dp))
                Content?.let {
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
