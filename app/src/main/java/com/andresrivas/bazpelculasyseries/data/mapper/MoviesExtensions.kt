package com.andresrivas.bazpelculasyseries.data.mapper

import com.andresrivas.bazpelculasyseries.data.services.MovieResponse
import com.andresrivas.bazpelculasyseries.data.services.MovieResult
import com.andresrivas.bazpelculasyseries.data.services.response.MovieVideoResponse
import com.andresrivas.bazpelculasyseries.data.services.response.MovieVideoResult
import com.andresrivas.bazpelculasyseries.domain.model.*
import com.google.gson.annotations.SerializedName

fun MovieResponse.transformToDomain() : TrendingMoviesModel {
    return TrendingMoviesModel(page, resultList.map {
        it.transformToDomain()
    })
}

fun MovieResult.transformToDomain() : TrendingMoviesResultModel {
    return title?.let { TrendingMoviesResultModel(it, posterPath, overView, id) } ?: TrendingMoviesResultModel("", posterPath, overView, id)
}

fun MovieVideoResponse.transformToDomain() : MoviesVideoModel {
    return MoviesVideoModel(id, resultList.map {
        it.transformToDomain()
    })
}

fun MovieVideoResult.transformToDomain() : MoviesVideoResultModel {
    return MoviesVideoResultModel(id, key, name, site, size, type)
}