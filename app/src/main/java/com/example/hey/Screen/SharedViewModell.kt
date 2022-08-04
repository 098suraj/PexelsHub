package com.example.hey.Screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.domain.model.PhotoFire
import com.example.domain.model.VideoFire
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor() : ViewModel() {
    var photoFiree by mutableStateOf<PhotoFire?>(null)
        private set
    var videoFiree by mutableStateOf<VideoFire?>(null)
        private set

    fun putPhoto(photoFire: PhotoFire) {
        photoFiree = photoFire
        print(photoFiree!!.Content)
    }

    fun putVideo(videoFire: VideoFire) {
        videoFiree = videoFire
    }

}