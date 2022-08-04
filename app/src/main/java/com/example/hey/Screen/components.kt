package com.example.hey.Screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.domain.model.VideoFire
import com.example.hey.navigation.Screen

@Composable
fun HorizontalScreen(allPhotos: MutableList<VideoFire?>, navHostController: NavHostController,) {
    LazyRow(

    ) {
        items(
            items = allPhotos,

        ) { Photos ->
            if (Photos != null) {
                ImageBox(Photos,navHostController)
                Spacer(modifier = Modifier.width(20.dp))
            }

        }
    }
}


@Composable
fun ImageBox(video: VideoFire?, navController: NavHostController) {
    val screenWidth = LocalConfiguration.current.screenWidthDp

    Card(
        modifier = Modifier.height(480.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Box(
            modifier = Modifier
                .width((screenWidth - 57).dp)
                .height(480.dp)

        ) {
            if (video != null) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(video.photourl)
                        .crossfade(true)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.drawWithCache {
                        val gradient = Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Black.copy(.7f)),
                            startY = size.height/4,
                            endY = size.height
                        )
                        onDrawWithContent {
                            drawContent()
                            drawRect(gradient,blendMode = BlendMode.Multiply)
                        }
                    }
                )
            }

        }
    }
}