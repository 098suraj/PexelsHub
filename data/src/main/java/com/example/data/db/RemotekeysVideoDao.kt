package com.example.data.db

import androidx.paging.PagingData
import androidx.room.Dao
import androidx.room.OnConflictStrategy
import androidx.room.Insert

import androidx.room.Query
import com.example.domain.model.RemoteKeysVideo

@Dao
interface RemoteKeysVideoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertKeys(remotekeys: List<RemoteKeysVideo>)

    @Query("SELECT * FROM RemoteKeysVideo Where id = :id")
    suspend fun getRemoteKeys(id: Int): RemoteKeysVideo

    @Query("DELETE FROM RemoteKeysVideo")
    suspend fun deleteAll()

}