package com.example.hey.di

import com.example.data.api.ImvApi
import com.example.data.db.ImvDatabase
import com.example.data.db.PhotoDao
import com.example.data.repository.dataSourceImp.RemoteImageImp
import com.example.data.repository.dataSourceImp.RemoteVideoImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RemoteDataIMVModule{
    @Provides
    fun provideRemotePhotoModule(db:ImvDatabase,api:ImvApi)=RemoteImageImp(db,api);

    @Provides
    fun provideRemoteVideoModule(db: ImvDatabase,api: ImvApi)=RemoteVideoImp(db,api);
}