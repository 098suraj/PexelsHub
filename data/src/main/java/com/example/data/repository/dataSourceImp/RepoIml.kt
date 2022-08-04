package com.example.data.repository.dataSourceImp

import androidx.paging.PagingData
import com.example.domain.model.PhotoDataModel
import com.example.domain.model.VideoDataModel
import com.example.domain.repository.ImViRepository
import kotlinx.coroutines.flow.Flow

class RepoIml(
    private val localPhotoImp: LocalPhotoImp,
    private val localVideoImp: LocalVideoImp,
    private val remoteImageImp: RemoteImageImp,
    private val remoteVideoImp: RemoteVideoImp
) :ImViRepository{
    override fun getImages(): Flow<PagingData<PhotoDataModel.Photo>> =
        remoteImageImp.getPhotoFromRemote()


    override fun getVideo(): Flow<PagingData<VideoDataModel.Video>> {
       return remoteVideoImp.getVideos()
    }

    override fun getImageFromDB(id: Int): Flow<PhotoDataModel.Photo> {
      return localPhotoImp.getPhotoFromDB(id)
    }

    override fun getVideoFromDB(id: Int): Flow<VideoDataModel.Video> {
      return  localVideoImp.getVideosFromDb(id)
    }
}