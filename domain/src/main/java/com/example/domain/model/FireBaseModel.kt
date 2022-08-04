package com.example.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PhotoFire(
    var photourl:String?="",
    var by:String?="",
    var Content:String?="",

    ):Parcelable


@Parcelize
data class VideoFire(
    var Videourl:String?="",
    var photourl:String?="",
    var by:String?="",
    var Content:String?="",
):Parcelable
