package com.example.data.db

import androidx.paging.PagingSource
import androidx.room.*
import com.example.domain.model.ListToData
import com.example.domain.model.VideoDataModel
import kotlinx.coroutines.flow.Flow

@Dao

interface VideoDao {
    @TypeConverters(ListToData::class)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(video: List<VideoDataModel.Video>)

    @Query("SELECT * FROM video")
    fun getAll(): PagingSource<Int, VideoDataModel.Video>

    @Query("DELETE FROM video")
    suspend fun deleteAll()

    @Query("SELECT * FROM video WHERE id = :id")
    fun getVideo(id: Int): Flow<VideoDataModel.Video>

}