package com.example.hey.di

import com.example.data.repository.dataSource.VideoLocalDataSource
import com.example.data.repository.dataSourceImp.*
import com.example.domain.repository.ImViRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepoImlModule {
    @Provides
    fun provideRepoIml(
      localVideoImp: LocalVideoImp,
       localPhotoImp: LocalPhotoImp,
       remoteImageImp: RemoteImageImp,
       remoteVideoImp: RemoteVideoImp
    ):ImViRepository=RepoIml(
        localPhotoImp=localPhotoImp,
        localVideoImp=localVideoImp,
        remoteImageImp=remoteImageImp,
        remoteVideoImp=remoteVideoImp
    )
}