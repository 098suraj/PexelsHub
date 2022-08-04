package com.example.hey.di

import com.example.domain.repository.ImViRepository
import com.example.domain.useCases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCasesModule {
    @Provides
    fun provideUSeCases(
        repository: ImViRepository,
    ) =ImvUseCases(
        getPhotosFromDbCase = GetPhotosFromDb(repository),
        getPhotosUseCase = GetPhotos(repository),
        getVideosUseCase = GetVideos(repository),
        getVideosFromDbCase = GetVideosFromDb(repository)
    )
}