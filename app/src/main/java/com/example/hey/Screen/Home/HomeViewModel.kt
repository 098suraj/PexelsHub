package com.example.hey.Screen.Home

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.data.api.ImvApi
import com.example.domain.model.PhotoDataModel
import com.example.domain.model.PhotoFire
import com.example.domain.model.VideoDataModel
import com.example.domain.model.VideoFire
import com.example.domain.useCases.ImvUseCases
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCases: ImvUseCases,

    ) : ViewModel() {
    private val _photolist: MutableList<PhotoFire?> = mutableListOf(null)
    val photolist = _photolist
    private val _videolist: MutableList<VideoFire?> = mutableListOf(null)
    val videoList = _videolist
    private val _uiState = MutableStateFlow<PhotoAppState>(PhotoAppState.Empty)
    val uiState: StateFlow<PhotoAppState> = _uiState
    private val _uiVideoState = MutableStateFlow<VideoAppState>(VideoAppState.Empty)
    val uiVideoState: StateFlow<VideoAppState> = _uiVideoState


    val database = Firebase.database
    val myRef = database.reference

    init {
        getFireVideo()
        getFirePhoto()
    }

    fun getFirePhoto() {
        viewModelScope.launch {
            _uiState.value = PhotoAppState.Loading
            myRef.child("Photo").addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    _photolist.clear()

                    snapshot.children.forEach {
                        val photo = it.getValue<PhotoFire>()
                        if (photo != null) {
                            _photolist.add(photo)
                            _uiState.value = PhotoAppState.Loaded(photolist)
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })
        }
    }


    fun getFireVideo() {
        viewModelScope.launch {
            _uiVideoState.value = VideoAppState.Loading
            myRef.child("Video").addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    _videolist.clear()

                    snapshot.children.forEach {
                        val video = it.getValue<VideoFire>()
                        if (video != null) {
                            _videolist.add(video)
                            _uiVideoState.value = VideoAppState.Loaded(videoList)
                        }

                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })
        }
    }

    sealed class PhotoAppState {
        object Empty : PhotoAppState()
        object Loading : PhotoAppState()
        class Loaded(val list: MutableList<PhotoFire?>) : PhotoAppState()
        class Error(val message: String) : PhotoAppState()
    }

    sealed class VideoAppState {
        object Empty : VideoAppState()
        object Loading : VideoAppState()
        class Loaded(val list: MutableList<VideoFire?>) : VideoAppState()
        class Error(val message: String) : VideoAppState()
    }


    //
//    val getPhotos = useCases.getPhotosUseCase()
    //  val getVideos = useCases.getVideosUseCase()


}