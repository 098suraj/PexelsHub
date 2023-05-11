package com.example.domain.useCases

import com.example.domain.repository.PixelsRepository

class GetVideos(private val videosRepository: PixelsRepository) {
     operator fun invoke() = videosRepository.getVideo()
}
