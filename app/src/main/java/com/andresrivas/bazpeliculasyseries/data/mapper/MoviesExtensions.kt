package com.andresrivas.bazpeliculasyseries.data.mapper

import com.andresrivas.bazpeliculasyseries.data.services.MovieResponse
import com.andresrivas.bazpeliculasyseries.data.services.MovieResult
import com.andresrivas.bazpeliculasyseries.data.services.response.MovieVideoResponse
import com.andresrivas.bazpeliculasyseries.data.services.response.MovieVideoResult
import com.andresrivas.bazpeliculasyseries.domain.model.MoviesVideoModel
import com.andresrivas.bazpeliculasyseries.domain.model.MoviesVideoResultModel
import com.andresrivas.bazpeliculasyseries.domain.model.TrendingMoviesModel
import com.andresrivas.bazpeliculasyseries.domain.model.TrendingMoviesResultModel

fun MovieResponse.transformToDomain(): TrendingMoviesModel {
    return TrendingMoviesModel(page, resultList.map {
        it.transformToDomain()
    })
}

fun MovieResult.transformToDomain(): TrendingMoviesResultModel {
    return title?.let { TrendingMoviesResultModel(it, posterPath, overView, id) }
        ?: TrendingMoviesResultModel("", posterPath, overView, id)
}

fun MovieVideoResponse.transformToDomain(): MoviesVideoModel {
    return MoviesVideoModel(id, resultList.map {
        it.transformToDomain()
    })
}

fun MovieVideoResult.transformToDomain(): MoviesVideoResultModel {
    return MoviesVideoResultModel(id, key, name, site, size, type)
}