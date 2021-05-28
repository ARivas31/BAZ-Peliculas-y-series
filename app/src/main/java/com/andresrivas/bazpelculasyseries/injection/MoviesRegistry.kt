package com.andresrivas.bazpelculasyseries.injection

import com.andresrivas.bazpelculasyseries.data.services.APIService
import com.andresrivas.bazpelculasyseries.gateway.MovieRetrofit

class MoviesRegistry {
    val apiServiceFactory: APIService by lazy {
        MovieRetrofit.getMovieRetrofit().create(APIService::class.java)
    }
}