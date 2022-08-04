package com.example.domain.useCases

data class ImvUseCases (
    val getVideosUseCase: GetVideos,
    val getPhotosUseCase: GetPhotos,
    val getVideosFromDbCase: GetVideosFromDb,
    val getPhotosFromDbCase: GetPhotosFromDb,
)