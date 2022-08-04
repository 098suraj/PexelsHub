package com.example.domain.useCases

import com.example.domain.repository.ImViRepository

class GetVideos(private val videosRepository: ImViRepository) {
     operator fun invoke() = videosRepository.getVideo()
}
