package com.example.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.data.api.ImvApi
import com.example.data.db.ImvDatabase
import com.example.domain.model.RemoteKeysVideo
import com.example.domain.model.VideoDataModel
import java.lang.Exception

@OptIn(ExperimentalPagingApi::class)
class VideoPaging(
    private val db: ImvDatabase,
    private val videoApi: ImvApi
) : RemoteMediator<Int, VideoDataModel.Video>() {
    val videoDao = db.videoDao()
    val videoRemoteKeysDao = db.remoteKeysVideoDao()
    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, VideoDataModel.Video>
    ): MediatorResult {
        return try {
            val page = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = closeToCurrentPosition(state)
                    remoteKeys?.nextKey?.minus(1) ?: 1
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getFirstPosition(state)
                    val prev = remoteKeys?.prevKey ?: MediatorResult.Success(
                        endOfPaginationReached = remoteKeys != null
                    )
                    prev
                }
                LoadType.APPEND -> {
                    val remoteKeys = getLastKey(state)
                    val nextKeys = remoteKeys?.nextKey ?: MediatorResult.Success(
                        endOfPaginationReached = remoteKeys != null
                    )
                    nextKeys
                }
            }

            val response = videoApi.getVideos(page as Int, 3).videos
            response.forEach{
                println(it)
            }
            val endOfPaginationReached = response.isEmpty()
            val nextPage = if (endOfPaginationReached) null else page + 1
            val prevKeys = if (page == 1) null else page - 1

            db.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    videoDao.deleteAll()
                    videoRemoteKeysDao.deleteAll()
                }
                val keys = response.map {
                    RemoteKeysVideo(
                        prevKey = prevKeys,
                        nextKey = nextPage,
                        id = it.id,
                        lastUpdated = System.currentTimeMillis()
                    )
                }
                videoRemoteKeysDao.insertKeys(keys)
                videoDao.insert(response)

            }
            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }

    }

    suspend fun closeToCurrentPosition(state: PagingState<Int, VideoDataModel.Video>): RemoteKeysVideo? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position).let { videoDataClass ->
                videoDataClass?.let { videoRemoteKeysDao.getRemoteKeys(it.id) }
            }
        }
    }

    suspend fun getFirstPosition(state: PagingState<Int, VideoDataModel.Video>): RemoteKeysVideo? {
        return state.pages.firstOrNull { pagingSource -> pagingSource.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { videoDataClass ->
                videoRemoteKeysDao.getRemoteKeys(videoDataClass.id)
            }
    }


    private suspend fun getLastKey(
        state: PagingState<Int, VideoDataModel.Video>
    ): RemoteKeysVideo? {
        return state.pages.lastOrNull {
            it.data.isNotEmpty()
        }?.data?.lastOrNull()?.let {
            videoRemoteKeysDao.getRemoteKeys(it.id)
        }
    }
}






