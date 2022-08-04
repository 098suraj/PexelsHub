package com.example.data.repository.dataSource

import androidx.paging.PagingData
import com.example.domain.model.PhotoDataModel
import kotlinx.coroutines.flow.Flow

interface PhotoRemoteDataSource {
    fun getPhotoFromRemote(): Flow<PagingData<PhotoDataModel.Photo>>
}