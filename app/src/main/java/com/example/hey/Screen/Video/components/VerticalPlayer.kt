package com.example.hey.Screen.Video

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.domain.model.VideoFire
import com.example.hey.Screen.Video.components.rememberExoPlayerWithLifeCycle
import com.example.hey.Screen.Video.components.rememberPlayerView
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun VerticalPlayer(
    video: VideoFire,
    shouldPlay: Boolean,
    isMuted: Boolean,
    onMuted: (Boolean) -> Unit,
    isScrolling: Boolean
) {
    val exoPlayer = rememberExoPlayerWithLifeCycle(videoUrl = video.Videourl!!)
    val playerView = rememberPlayerView(exoPlayer = exoPlayer)
    var volumeIconVisibility by remember {
        mutableStateOf(false)
    }
    val coroutineScope = rememberCoroutineScope()
    Box {
        AndroidView(
            factory = { playerView },
            modifier = Modifier
                .pointerInput(isMuted) {
                    detectTapGestures(
                        onTap = {
                            if (exoPlayer.playWhenReady) {
                                if (isMuted.not()) {
                                    exoPlayer.volume = 0f
                                    onMuted(true)
                                } else {
                                    exoPlayer.volume = 1f
                                    onMuted(false)
                                }
                                coroutineScope.launch {
                                    volumeIconVisibility = true
                                    delay(800)
                                    volumeIconVisibility = false
                                }
                            }
                        },
                        onPress = {
                            if (!isScrolling) {
                                exoPlayer.playWhenReady = false
                                awaitRelease()
                                exoPlayer.playWhenReady = true
                            }
                        }
                    )
                },
            update = {
                exoPlayer.volume = if (isMuted) 0f else 1f
                exoPlayer.playWhenReady = shouldPlay
            }
        )
        AnimatedVisibility(
            visible = volumeIconVisibility,
            enter = scaleIn(
                spring(Spring.DampingRatioMediumBouncy)
            ),
            exit = scaleOut(tween(150)),
            modifier = Modifier.align(Alignment.Center)
        ) {
            if (volumeIconVisibility) {
                Icon(
                    painter = painterResource(id = if (isMuted) com.example.hey.R.drawable.ic_volume_off else com.example.hey.R.drawable.ic_volume_on),
                    contentDescription = null,
                    tint = Color.White.copy(0.75f),
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(100.dp)
                )
            }
        }


    }
    DisposableEffect(key1 = true) {
        onDispose {
            exoPlayer.release()
        }
    }
}
