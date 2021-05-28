package com.andresrivas.bazpelculasyseries.domain.respository

import com.andresrivas.bazpelculasyseries.domain.model.MoviesVideoModel
import com.andresrivas.bazpelculasyseries.domain.model.TrendingMoviesModel
import com.andresrivas.bazpelculasyseries.tools.ResultAPI
import io.reactivex.rxjava3.core.Single

interface MoviesRepository {
    fun getMoviesTrending() : Single<ResultAPI<TrendingMoviesModel>>
    fun getMoviesVideo(movieId: String) : Single<ResultAPI<MoviesVideoModel>>
    fun getMoviesPlayingNow() : Single<ResultAPI<TrendingMoviesModel>>
}