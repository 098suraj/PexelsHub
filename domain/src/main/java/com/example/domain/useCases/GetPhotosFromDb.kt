package com.example.domain.useCases

import com.example.domain.repository.ImViRepository

class GetPhotosFromDb(private val repository: ImViRepository) {
    operator fun invoke(id:Int) = repository.getImageFromDB(id)
}
