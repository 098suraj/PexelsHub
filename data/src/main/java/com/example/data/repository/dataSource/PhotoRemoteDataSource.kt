package com.example.data.repository.dataSource

import androidx.paging.PagingData
import com.example.domain.model.Photos
import kotlinx.coroutines.flow.Flow

interface PhotoRemoteDataSource {
    fun getPhotoFromRemote(): Flow<PagingData<Photos.Photo>>
}