package com.example.domain.model

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "RemoteKeysPhoto")
data class RemoteKeysPhotos(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val nextPage: Int?,
    val prevPage: Int?,
    val lastUpdated: Long?,
)