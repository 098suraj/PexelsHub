package com.example.data.repository.dataSourceImp


import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.data.api.ImvApi
import com.example.data.db.ImvDatabase
import com.example.data.paging.VideoPaging
import com.example.data.repository.dataSource.VideoRemoteDataSource
import com.example.domain.model.VideoDataModel
import kotlinx.coroutines.flow.Flow

class RemoteVideoImp(private val db: ImvDatabase, private val api: ImvApi) : VideoRemoteDataSource {
    private val videoDao = db.videoDao()

    @OptIn(ExperimentalPagingApi::class)
    override fun getVideos(): Flow<PagingData<VideoDataModel.Video>> {
        val pagingSourceFactory = { videoDao.getAll() }
        return Pager(
            PagingConfig(
                pageSize = 1,
            ),
            remoteMediator = VideoPaging(
                db, api
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }


}
