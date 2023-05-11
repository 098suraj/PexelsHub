package com.example.data.repository.dataSourceImp

import androidx.paging.PagingData
import com.example.domain.model.Photos
import com.example.domain.model.VideoDataModel
import com.example.domain.repository.PixelsRepository
import kotlinx.coroutines.flow.Flow

class RepoIml(
    private val localPhotoImp: LocalPhotoImp,
    private val localVideoImp: LocalVideoImp,
    private val remoteImageImp: RemoteImageImp,
    private val remoteVideoImp: RemoteVideoImp
) :PixelsRepository{
    override fun getImages(): Flow<PagingData<Photos.Photo>> =
        remoteImageImp.getPhotoFromRemote()


    override fun getVideo(): Flow<PagingData<VideoDataModel.Video>> {
       return remoteVideoImp.getVideos()
    }

    override fun getImageFromDB(id: Int): Flow<Photos.Photo> {
      return localPhotoImp.getPhotoFromDB(id)
    }

    override fun getVideoFromDB(id: Int): Flow<VideoDataModel.Video> {
      return  localVideoImp.getVideosFromDb(id)
    }
}