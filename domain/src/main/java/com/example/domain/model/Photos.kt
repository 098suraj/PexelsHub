package com.example.domain.model


import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class Photos(
    @SerializedName("next_page")
    val nextPage: String,
    @SerializedName("page")
    val page: Int,
    @SerializedName("per_page")
    val perPage: Int,
    @SerializedName("photos")
    val photos: List<Photo>,
    @SerializedName("prev_page")
    val prevPage: String,
    @SerializedName("total_results")
    val totalResults: Int
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