package com.example.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.domain.model.*

@Database(
    entities = [VideoDataModel.Video::class, Photos.Photo::class, RemoteKeysPhotos::class, RemoteKeysVideo::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(ListToData::class)
abstract class ImvDatabase : RoomDatabase() {
    abstract fun videoDao(): VideoDao
    abstract fun photoDao(): PhotoDao
    abstract fun remoteKeysPhotosDao(): RemoteKeysPhotoDao
    abstract fun remoteKeysVideoDao(): RemoteKeysVideoDao
}