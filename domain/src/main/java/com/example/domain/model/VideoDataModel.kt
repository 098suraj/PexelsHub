package com.example.domain.model


import android.media.session.MediaSession.Token
import androidx.room.*
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import java.io.Serializable

data class VideoDataModel(
    val videos: List<Video>
) {
    @Entity(tableName = "video")
    data class Video(
        var duration: Int,
        @PrimaryKey(autoGenerate = true)
        var id: Int,
        var image: String,
        @SerializedName("url")
        var url: String,
        @Embedded
        var user: User,
        @TypeConverters(ListToData::class)
        @SerializedName("video_files")
        var videoFile: List<VideoFile>,
        var width: Int
    )
}

data class User(
   @SerializedName("id")
    var userId: Int,
    var name: String,
   @SerializedName("url")
    var userUrl: String
)

data class VideoFile(
    @SerializedName("file_type")
    var fileType: String,
    @SerializedName("id")
    var id: Int,
    var link: String,
    var quality: String,

    ):Serializable

 class   ListToData {
     @TypeConverter
     fun listToJson(v:List<VideoFile>):String  =
         Gson().toJson(v)

     @TypeConverter
     fun GsonTolist(listOfString: String):List<VideoFile> =
         Gson().fromJson(
             listOfString,
             object : TypeToken<List<VideoFile>>() {}.type
         )
 }