package com.example.hey.Screen.Movie

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
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.domain.model.PhotoDataModel

import com.example.hey.ui.theme.ItemBackgroundColor
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


@Composable
fun PhotoListContent(

    photo: LazyPagingItems<PhotoDataModel.Photo>
) {
    val spacing = 20.dp
    val halfSpacing = spacing / 2
    Column(modifier = Modifier
        .padding(16.dp)
        .fillMaxSize()


        )
    {
        LazyColumn(
            contentPadding = PaddingValues(top = halfSpacing),
        ) {
            items(items= photo) { photo ->
                if (photo != null) {

                    PhotoListItem(photo = photo)
                    Spacer(modifier = Modifier.height(36.dp))
                }
            }
        }
    }
}

@Composable
fun PhotoListItem( photo: PhotoDataModel.Photo) {
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
                    photo.photographer?.let { Text(text = it, style = MaterialTheme.typography.subtitle1) }
                    Spacer(modifier = Modifier.height(4.dp))
                    photo.photographerId?.let {
                        Text(
                            text = it.toString(),
                            style = MaterialTheme.typography.caption,
                            maxLines = 4,
                            overflow = TextOverflow.Ellipsis
                        )
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
            Image(
                painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current).data(data = photo.src.medium)
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
