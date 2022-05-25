package com.andresrivas.bazpeliculasyseries.domain.repository

import com.andresrivas.bazpeliculasyseries.domain.model.MoviesVideoModel
import com.andresrivas.bazpeliculasyseries.domain.model.TrendingMoviesModel
import com.andresrivas.bazpeliculasyseries.tools.ResultAPI

interface MoviesRepository {
    suspend fun getMoviesTrending(): ResultAPI<TrendingMoviesModel>
    suspend fun getMoviesVideo(movieId: String): ResultAPI<MoviesVideoModel>
    suspend fun getMoviesPlayingNow(): ResultAPI<TrendingMoviesModel>
}