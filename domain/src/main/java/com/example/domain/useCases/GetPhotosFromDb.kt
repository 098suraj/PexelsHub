package com.example.domain.useCases

import com.example.domain.repository.PixelsRepository

class GetPhotosFromDb(private val repository: PixelsRepository) {
    operator fun invoke(id:Int) = repository.getImageFromDB(id)
}
