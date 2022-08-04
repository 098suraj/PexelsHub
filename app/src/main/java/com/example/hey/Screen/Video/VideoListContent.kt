package com.example.hey.Screen.Video

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.domain.model.PhotoFire
import com.example.domain.model.VideoFire
import com.example.hey.navigation.Screen
import com.example.hey.ui.theme.ItemBackgroundColor


@Composable
fun VideoListContent(
    allVideos: MutableList<VideoFire?>,
    navController: NavHostController
) {
    val spacing = 20.dp
    val halfSpacing = spacing / 2
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),

        )
    {
        LazyColumn(
            contentPadding = PaddingValues(top = halfSpacing),
        ) {
            items(
                items = allVideos,

            ) { video ->
                if (video != null) {
                    Spacer(modifier = Modifier.height(36.dp))
                    MovieListItem(video = video, navController = navController)
                }
            }
        }
    }
}

@Composable
fun MovieListItem(video: VideoFire?, navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Card(
            modifier = Modifier
                .padding(top = 8.dp, bottom = 8.dp)
                .height(120.dp)
                .fillMaxWidth(),
            elevation = 6.dp,
            backgroundColor = MaterialTheme.colors.ItemBackgroundColor,
            shape = RoundedCornerShape(10.dp)
        ) {
            Row(
                modifier = Modifier
                    .height(IntrinsicSize.Max)
                    .fillMaxWidth()
                    .clickable {
                        val videoData=video
                        navController.currentBackStackEntry?.savedStateHandle?.set("VideoData",videoData)
                        navController.navigate(route = Screen.VideoDetails.route)
                    },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier
                        .weight(.35f)
                ) {

                }
                Column(
                    Modifier
                        .height(IntrinsicSize.Max)
                        .padding(
                            end = 2.dp,
                        )
                        .weight(0.6f)
                ) {
                    if (video != null) {
                        video.by?.let { Text(text = it, style = MaterialTheme.typography.subtitle1) }
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    if (video != null) {
                        video.Content?.let {
                            Text(
                                text = it,
                                style = MaterialTheme.typography.caption,
                                maxLines = 4,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))

                }
            }
        }
        Card(
            modifier = Modifier
                .offset(16.dp, (-44).dp)
                .fillMaxWidth(0.3f)
                .height(164.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            if (video != null) {
                Image(
                    painter = rememberAsyncImagePainter(
                        ImageRequest.Builder(LocalContext.current).data(data = video.photourl)
                            .apply(block = fun ImageRequest.Builder.() {
                                crossfade(true)
                            }).build()
                    ),
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth
                )
            }

        }
    }

}
