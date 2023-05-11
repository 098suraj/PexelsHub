package com.example.data.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.domain.model.Photos
import kotlinx.coroutines.flow.Flow


@Dao
interface PhotoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(photo: List<Photos.Photo>)

    @Query("SELECT * FROM photos")
    fun getAll(): PagingSource<Int, Photos.Photo>

    @Query("DELETE FROM photos")
    suspend fun deleteAll()

    @Query("SELECT * FROM photos WHERE id = :id")
    fun getById(id: Int): Flow<Photos.Photo>

}