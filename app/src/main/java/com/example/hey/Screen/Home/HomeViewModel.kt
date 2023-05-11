package com.example.hey.Screen.Home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.domain.model.Photos

import com.example.domain.model.VideoDataModel

import com.example.domain.useCases.ImvUseCases

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCases: ImvUseCases,
) : ViewModel() {


    fun photo(): Flow<PagingData<Photos.Photo>> {
        return useCases.getPhotosUseCase().cachedIn(viewModelScope)
    }


    fun video(): Flow<PagingData<VideoDataModel.Video>> {
        return useCases.getVideosUseCase()
    }

    sealed class PhotoAppState {
        object Empty : PhotoAppState()
        object Loading : PhotoAppState()
        class Loaded(val list: Flow<PagingData<Photos.Photo>>) : PhotoAppState()
        class Error(val message: String) : PhotoAppState()
    }

    sealed class VideoAppState {
        object Empty : VideoAppState()
        object Loading : VideoAppState()
        class Loaded(val list: Flow<PagingData<VideoDataModel.Video>>) : VideoAppState()
        class Error(val message: String) : VideoAppState()
    }


}