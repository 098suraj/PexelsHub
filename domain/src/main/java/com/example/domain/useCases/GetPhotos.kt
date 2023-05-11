package com.example.domain.useCases

import com.example.domain.repository.PixelsRepository

class GetPhotos(private val repository: PixelsRepository) {
    operator fun invoke() = repository.getImages()
}