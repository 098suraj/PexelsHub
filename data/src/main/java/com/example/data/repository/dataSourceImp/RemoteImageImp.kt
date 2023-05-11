package com.example.data.repository.dataSourceImp

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.data.api.ImvApi
import com.example.data.db.ImvDatabase
import com.example.data.paging.PhotoPaging
import com.example.data.repository.dataSource.PhotoRemoteDataSource
import com.example.domain.model.Photos
import kotlinx.coroutines.flow.Flow

class RemoteImageImp(private val db: ImvDatabase, private val api: ImvApi) : PhotoRemoteDataSource {
    private val imageDao = db.photoDao()
    @OptIn(ExperimentalPagingApi::class)
    override fun getPhotoFromRemote(): Flow<PagingData<Photos.Photo>> {
        val pageSourceFactory = { imageDao.getAll() }
        return Pager(
            config = PagingConfig(pageSize = 1),
            remoteMediator = PhotoPaging(
                api, db
            ),
            pagingSourceFactory = pageSourceFactory
        ).flow
    }

}