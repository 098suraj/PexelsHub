package com.example.hey.di

import com.example.data.db.PhotoDao
import com.example.data.db.VideoDao
import com.example.data.repository.dataSourceImp.LocalPhotoImp
import com.example.data.repository.dataSourceImp.LocalVideoImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object LocalDataSourceModule {

    @Provides
    fun provideLocalPhoto(photoDao: PhotoDao)=LocalPhotoImp(photoDao)

    @Provides
    fun provideLocalVideo(videoDao: VideoDao)=LocalVideoImp(videoDao)

}