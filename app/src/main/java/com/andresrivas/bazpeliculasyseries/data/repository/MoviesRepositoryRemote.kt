package com.andresrivas.bazpeliculasyseries.data.repository

import com.andresrivas.bazpeliculasyseries.data.mapper.transformToDomain
import com.andresrivas.bazpeliculasyseries.data.services.APIService
import com.andresrivas.bazpeliculasyseries.domain.model.MoviesVideoModel
import com.andresrivas.bazpeliculasyseries.domain.model.TrendingMoviesModel
import com.andresrivas.bazpeliculasyseries.domain.repository.MoviesRepository
import com.andresrivas.bazpeliculasyseries.tools.ResultAPI
import javax.inject.Inject

class MoviesRepositoryRemote @Inject constructor(private val apiService: APIService) : MoviesRepository {

    override suspend fun getMoviesTrending(): ResultAPI<TrendingMoviesModel> {
        val response = apiService.getTrendingMovies("15c5e8d6cc45dcc891087fddd84bdaa9")
        return response.body()?.let {
            if (response.isSuccessful) {
                ResultAPI.OnSuccess(it.transformToDomain())
            } else {
                ResultAPI.OnFailure(Exception())
            }
        } ?: ResultAPI.OnFailure(Exception())
    }

    override suspend fun getMoviesVideo(movieId: String): ResultAPI<MoviesVideoModel> {
        val response = apiService.getTrendingMoviesVideo(movieId,"15c5e8d6cc45dcc891087fddd84bdaa9")
        return response.body()?.let {
            if (response.isSuccessful) {
                ResultAPI.OnSuccess(it.transformToDomain())
            } else {
                ResultAPI.OnFailure(Exception())
            }
        } ?: ResultAPI.OnFailure(Exception())
    }

    override suspend fun getMoviesPlayingNow(): ResultAPI<TrendingMoviesModel> {
        val response = apiService.getPlayingNowMovies("15c5e8d6cc45dcc891087fddd84bdaa9")
        return response.body()?.let {
            if (response.isSuccessful) {
                ResultAPI.OnSuccess(it.transformToDomain())
            } else {
                ResultAPI.OnFailure(Exception())
            }
        } ?: ResultAPI.OnFailure(Exception())
    }
}
