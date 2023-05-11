package com.example.data.repository.dataSourceImp

import com.example.data.db.PhotoDao
import com.example.data.repository.dataSource.PhotoLocalDataSource
import com.example.domain.model.Photos
import kotlinx.coroutines.flow.Flow

class LocalPhotoImp(private val photoDao: PhotoDao) : PhotoLocalDataSource {
    override fun getPhotoFromDB(id: Int): Flow<Photos.Photo> {
        return photoDao.getById(id)
    }

}
