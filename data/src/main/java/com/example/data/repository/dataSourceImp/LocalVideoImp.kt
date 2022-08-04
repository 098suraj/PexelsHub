package com.example.data.repository.dataSourceImp

import com.example.data.db.VideoDao
import com.example.data.repository.dataSource.VideoLocalDataSource
import com.example.domain.model.VideoDataModel
import kotlinx.coroutines.flow.Flow

class LocalVideoImp(private val videoDao: VideoDao): VideoLocalDataSource {
    override fun getVideosFromDb(id: Int): Flow<VideoDataModel.Video> {
        return videoDao.getVideo(id)
    }
}