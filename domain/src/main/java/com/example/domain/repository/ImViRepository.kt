package com.example.domain.repository

import androidx.paging.PagingData
import com.example.domain.model.PhotoDataModel
import com.example.domain.model.VideoDataModel
import kotlinx.coroutines.flow.Flow

interface ImViRepository {
    fun getImages(): Flow<PagingData<PhotoDataModel.Photo>>
    fun getVideo(): Flow<PagingData<VideoDataModel.Video>>
    fun getImageFromDB(id: Int): Flow<PhotoDataModel.Photo>
    fun getVideoFromDB(id: Int): Flow<VideoDataModel.Video>
}