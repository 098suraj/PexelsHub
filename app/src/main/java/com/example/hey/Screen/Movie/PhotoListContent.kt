package com.example.hey.Screen.Movie

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.domain.model.Photos
import com.example.hey.Screen.Home.HomeViewModel

import com.example.hey.ui.theme.ItemBackgroundColor


@Composable
fun PhotoListContent(
  viewModel: HomeViewModel = hiltViewModel<HomeViewModel>()
) {
    val spacing = 20.dp
    val halfSpacing = spacing / 2
    val photo=viewModel.photo().collectAsLazyPagingItems()
    LazyColumn(
        contentPadding = PaddingValues(top = halfSpacing, bottom = 10.dp),
        modifier = Modifier.fillMaxSize(),
        state = rememberLazyListState(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(items = photo) { phot ->
            if (phot != null) {
                PhotoListItem(photo = phot)
            }

        }
        when (photo.loadState.refresh) { //FIRST LOAD
            is LoadState.Error -> {
                //TODO Error Item
                //state.error to get error message
            }

            is LoadState.Loading -> { // Loading UI
                item {
                    Column(
                        modifier = Modifier
                            .fillParentMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(8.dp),
                            text = "Refresh Loading"
                        )

                        CircularProgressIndicator(color = Color.Black)
                    }
                }
            }

            else -> {}
        }

        when (photo.loadState.append) { // Pagination
            is LoadState.Error -> {
                //TODO Pagination Error Item
                //state.error to get error message
            }

            is LoadState.Loading -> { // Pagination Loading UI
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Text(text = "Pagination Loading")

                        CircularProgressIndicator(color = Color.Black)
                    }
                }
            }

            else -> {}
        }
    }

}

@Composable
fun PhotoListItem(photo: Photos.Photo) {
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
                    Text(
                        text = photo.id.toString(),
                        style = MaterialTheme.typography.subtitle1
                    )
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
