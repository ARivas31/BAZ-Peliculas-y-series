package com.andresrivas.bazpelculasyseries.injection

import com.andresrivas.bazpelculasyseries.domain.usecases.GetMovieVideoUseCase
import com.andresrivas.bazpelculasyseries.domain.usecases.GetPlayingNowUseCase
import com.andresrivas.bazpelculasyseries.domain.usecases.GetTrendingUseCase

class UseCaseProvider {
    fun provideGetTrendingUseCase() : GetTrendingUseCase{
        return GetTrendingUseCase(RepositoryProvider().provideMoviesRepository())
    }

    fun provideGetMovieVideoUseCase() : GetMovieVideoUseCase{
        return GetMovieVideoUseCase(RepositoryProvider().provideMoviesRepository())
    }

    fun providePlayingNowUseCase() : GetPlayingNowUseCase{
        return GetPlayingNowUseCase(RepositoryProvider().provideMoviesRepository())
    }
}