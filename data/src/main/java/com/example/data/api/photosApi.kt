package com.example.data.api

import com.example.domain.model.PhotoDataModel
import com.example.domain.model.VideoDataModel
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ImvApi {

    @Headers("Authorization:563492ad6f91700001000001ec27ec48dda5474c831c7dba0bcc717c")
    @GET("/v1/curated")
    suspend fun getPhotos(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): PhotoDataModel



    @Headers("Authorization:563492ad6f91700001000001ec27ec48dda5474c831c7dba0bcc717c")
    @GET("/videos/popular")
    suspend fun getVideos(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): VideoDataModel


}