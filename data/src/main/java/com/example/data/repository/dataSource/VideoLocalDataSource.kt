package com.example.data.repository.dataSource

import com.example.domain.model.VideoDataModel
import kotlinx.coroutines.flow.Flow

interface VideoLocalDataSource {
    fun getVideosFromDb(id:Int):Flow<VideoDataModel.Video>
}