package com.andresrivas.bazpelculasyseries.data.repository

import com.andresrivas.bazpelculasyseries.data.mapper.transformToDomain
import com.andresrivas.bazpelculasyseries.data.services.APIService
import com.andresrivas.bazpelculasyseries.domain.model.MoviesVideoModel
import com.andresrivas.bazpelculasyseries.domain.model.TrendingMoviesModel
import com.andresrivas.bazpelculasyseries.domain.respository.MoviesRepository
import com.andresrivas.bazpelculasyseries.tools.ResultAPI
import io.reactivex.rxjava3.core.Single
import java.lang.Exception

class MoviesRepositoryRemote(private val apiService: APIService) : MoviesRepository {

    override fun getMoviesTrending(): Single<ResultAPI<TrendingMoviesModel>> {
        return apiService.getTrendingMovies("15c5e8d6cc45dcc891087fddd84bdaa9")
            .map { response ->
                return@map response.body()?.let {
                    if(response.isSuccessful) {
                        ResultAPI.OnSuccess(it.transformToDomain())
                    } else {
                        ResultAPI.OnFailure(Exception())
                    }
                }?: ResultAPI.OnFailure(Exception())
            }
    }

    override fun getMoviesVideo(movieId: String): Single<ResultAPI<MoviesVideoModel>> {
        return apiService.getTrendingMoviesVideo(movieId, "15c5e8d6cc45dcc891087fddd84bdaa9")
            .map { response ->
                return@map response.body()?.let {
                    if(response.isSuccessful) {
                        ResultAPI.OnSuccess(it.transformToDomain())
                    } else {
                        ResultAPI.OnFailure(Exception())
                    }
                }?: ResultAPI.OnFailure(Exception())
            }
    }

    override fun getMoviesPlayingNow(): Single<ResultAPI<TrendingMoviesModel>> {
        return apiService.getPlayingNowMovies("15c5e8d6cc45dcc891087fddd84bdaa9")
            .map { response ->
                return@map response.body()?.let {
                    if(response.isSuccessful) {
                        ResultAPI.OnSuccess(it.transformToDomain())
                    } else {
                        ResultAPI.OnFailure(Exception())
                    }
                }?: ResultAPI.OnFailure(Exception())
            }
    }
}
