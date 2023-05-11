package com.example.domain.useCases

import com.example.domain.repository.PixelsRepository

class GetVideosFromDb(private val repository: PixelsRepository) {
     operator fun invoke(id:Int) = repository.getVideoFromDB(id)
}