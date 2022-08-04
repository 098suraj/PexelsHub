package com.example.data.paging

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.data.api.ImvApi
import com.example.data.db.ImvDatabase
import com.example.domain.model.PhotoDataModel
import com.example.domain.model.RemoteKeysPhotos

@OptIn(ExperimentalPagingApi::class)
class PhotoPaging(private val photoApi: ImvApi, private val db: ImvDatabase) :
    RemoteMediator<Int, PhotoDataModel.Photo>() {

    private val remotePhotoKeysDao = db.remoteKeysPhotosDao()
    private val photoDao = db.photoDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PhotoDataModel.Photo>
    ): MediatorResult {
        return try {
            val page = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKey = getRemoteClosestToCurrentPosition(state)
                    remoteKey?.nextPage?.minus(1) ?: 1
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getFirstKey(state)
                    val prevPage = remoteKeys?.prevPage ?: MediatorResult.Success(
                        endOfPaginationReached = remoteKeys != null
                    )
                    prevPage
                }
                LoadType.APPEND -> {
                    val remoteKeys = getLastKey(state)
                    val nextPage = remoteKeys?.nextPage ?: MediatorResult.Success(
                        endOfPaginationReached = remoteKeys != null
                    )
                    nextPage
                }
            }

            val response = photoApi.getPhotos(page as Int, 3).photos
            response.forEach {
                Log.i("Response",it.toString())}
            val endOfPaginationReached = response.isEmpty()
            val prevPage = if (page == 1) null else page - 1
            val nextPage = if (endOfPaginationReached) null else page + 1

            response.let {
                db.withTransaction {
                    if (loadType == LoadType.REFRESH) {
                        remotePhotoKeysDao.deleteKeysPhotos()
                        photoDao.deleteAll()
                    }
                    val keys=response.map {
                        RemoteKeysPhotos(it.id, prevPage, nextPage,System.currentTimeMillis())
                    }
                    remotePhotoKeysDao.addKeys(keys)
                    photoDao.insert(response)

                }
            }

            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }

    }

    private suspend fun getRemoteClosestToCurrentPosition(
        state: PagingState<Int, PhotoDataModel.Photo>
    ): RemoteKeysPhotos? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position).let { photoDataClass ->
                photoDataClass?.let {
                    db.remoteKeysPhotosDao().getRemoteKeysPhotosById(it.id) }
            }
        }
    }

    private suspend fun getFirstKey(
        state: PagingState<Int, PhotoDataModel.Photo>
    ): RemoteKeysPhotos? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()?.let {
            remotePhotoKeysDao.getRemoteKeysPhotosById(it.id)
        }
    }

    private suspend fun getLastKey(
        state: PagingState<Int, PhotoDataModel.Photo>
    ): RemoteKeysPhotos? {
        return state.pages.lastOrNull {
            it.data.isNotEmpty()
        }?.data?.lastOrNull()?.let {
            remotePhotoKeysDao.getRemoteKeysPhotosById(it.id)
        }
    }


}