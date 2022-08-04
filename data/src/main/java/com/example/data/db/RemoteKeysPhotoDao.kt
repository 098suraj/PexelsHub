package com.example.data.db

import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.domain.model.RemoteKeysPhotos

@Dao
interface RemoteKeysPhotoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addKeys (remoteKeyPhoto: List<RemoteKeysPhotos>)

    @Query("DELETE FROM RemoteKeysPhoto")
    suspend fun deleteKeysPhotos()

    @Query("SELECT * FROM RemoteKeysPhoto WHERE id = :id")
    suspend fun getRemoteKeysPhotosById(id: Int): RemoteKeysPhotos
}