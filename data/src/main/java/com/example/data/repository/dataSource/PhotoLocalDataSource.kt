package com.example.data.repository.dataSource

import com.example.domain.model.Photos
import kotlinx.coroutines.flow.Flow

interface PhotoLocalDataSource {
    fun getPhotoFromDB(id:Int): Flow<Photos.Photo>
}