package com.andresrivas.bazpelculasyseries.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.andresrivas.bazpelculasyseries.domain.usecases.GetMovieVideoUseCase
import com.andresrivas.bazpelculasyseries.domain.usecases.GetPlayingNowUseCase

class PlayingNowViewModelFactory (
    private val getPlayingNowUseCase: GetPlayingNowUseCase,
    private val movieVideoUseCase: GetMovieVideoUseCase
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(
                GetPlayingNowUseCase::class.java,
                GetMovieVideoUseCase::class.java
            ).newInstance(getPlayingNowUseCase, movieVideoUseCase)
        }
}