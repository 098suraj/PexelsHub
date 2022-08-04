package com.example.data.repository.dataSource

import androidx.paging.PagingData
import com.example.domain.model.VideoDataModel
import kotlinx.coroutines.flow.Flow

interface VideoRemoteDataSource {
    fun getVideos():Flow<PagingData<VideoDataModel.Video>>
}