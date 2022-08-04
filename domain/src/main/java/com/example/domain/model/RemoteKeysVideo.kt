package com.example.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "RemoteKeysVideo")
data class RemoteKeysVideo(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val prevKey: Int?,
    val nextKey: Int?,
    val lastUpdated: Long?,
)
