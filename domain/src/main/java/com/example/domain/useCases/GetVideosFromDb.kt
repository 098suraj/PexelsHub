package com.example.domain.useCases

import com.example.domain.repository.ImViRepository

class GetVideosFromDb(private val repository: ImViRepository) {
     operator fun invoke(id:Int) = repository.getVideoFromDB(id)
}