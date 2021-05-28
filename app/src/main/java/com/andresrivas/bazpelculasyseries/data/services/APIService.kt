package com.andresrivas.bazpelculasyseries.data.services

import com.andresrivas.bazpelculasyseries.data.services.response.MovieVideoResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIService {
    @GET ("trending/all/day")
    fun getTrendingMovies(@Query("api_key") apiKey: String): Single<Response<MovieResponse>>
    @GET ("movie/now_playing")
    fun getPlayingNowMovies(@Query("api_key") apiKey: String): Single<Response<MovieResponse>>
    @GET ("movie/{movieId}/videos")
    fun getTrendingMoviesVideo(@Path("movieId") movieId: String,@Query("api_key") apiKey: String): Single<Response<MovieVideoResponse>>
}