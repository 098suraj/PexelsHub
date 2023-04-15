package com.example.domain.model


import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class PhotoDataModel(
    val photos: List<Photo>,
) {
    @Entity(tableName = "photos")
    data class Photo(
        var alt: String,
        @PrimaryKey(autoGenerate = true)
        var id: Int,
        var photographer: String,
        @SerializedName("photographer_id")
        var photographerId: Int,
        @SerializedName("photographer_url")
        var photographerUrl: String,
        @Embedded
        var src: Src,
        var url: String,

    ) {
        data class Src(
            var landscape: String,
            var large: String,
            var large2x: String,
            var medium: String,
            var original: String,
            var portrait: String,
            var small: String,
            var tiny: String
        )
    }
}