package com.example.domain.repository

import androidx.paging.PagingData
import com.example.domain.model.Photos
import com.example.domain.model.VideoDataModel
import kotlinx.coroutines.flow.Flow

interface PixelsRepository {
    fun getImages(): Flow<PagingData<Photos.Photo>>
    fun getVideo(): Flow<PagingData<VideoDataModel.Video>>
    fun getImageFromDB(id: Int): Flow<Photos.Photo>
    fun getVideoFromDB(id: Int): Flow<VideoDataModel.Video>
}