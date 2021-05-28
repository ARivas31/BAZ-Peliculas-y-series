package com.andresrivas.bazpelculasyseries.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.andresrivas.bazpelculasyseries.domain.usecases.GetMovieVideoUseCase
import com.andresrivas.bazpelculasyseries.domain.usecases.GetTrendingUseCase

class TrendingViewModelFactory(
    private val trendingUseCase: GetTrendingUseCase,
    private val movieVideoUseCase: GetMovieVideoUseCase
    ) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(
            GetTrendingUseCase::class.java,
            GetMovieVideoUseCase::class.java
        ).newInstance(trendingUseCase,movieVideoUseCase)
    }
}