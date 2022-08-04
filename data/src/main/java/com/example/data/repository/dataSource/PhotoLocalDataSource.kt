package com.example.data.repository.dataSource

import com.example.domain.model.PhotoDataModel
import kotlinx.coroutines.flow.Flow

interface PhotoLocalDataSource {
    fun getPhotoFromDB(id:Int): Flow<PhotoDataModel.Photo>
}