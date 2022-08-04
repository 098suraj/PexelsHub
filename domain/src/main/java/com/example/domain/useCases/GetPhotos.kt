package com.example.domain.useCases

import com.example.domain.repository.ImViRepository

class GetPhotos(private val repository: ImViRepository) {
    operator fun invoke() = repository.getImages()
}