package com.example.data.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.domain.model.PhotoDataModel
import kotlinx.coroutines.flow.Flow


@Dao
interface PhotoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(photo: List<PhotoDataModel.Photo>)

    @Query("SELECT * FROM photos")
    fun getAll(): PagingSource<Int, PhotoDataModel.Photo>

    @Query("DELETE FROM photos")
    suspend fun deleteAll()

    @Query("SELECT * FROM photos WHERE id = :id")
    fun getById(id: Int): Flow<PhotoDataModel.Photo>

}