package com.example.hey.Screen.Video

import androidx.lifecycle.ViewModel
import com.example.domain.useCases.ImvUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class VerticalPlayerViewModel @Inject constructor(
    val imv:ImvUseCases
): ViewModel() {
    val videoList=imv.getVideosUseCase()
}