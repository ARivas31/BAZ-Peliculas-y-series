package com.andresrivas.bazpelculasyseries.domain.model

data class TrendingMoviesModel (
    var page: Int,
    var resultList: List<TrendingMoviesResultModel>)

data class TrendingMoviesResultModel(
    var title: String,
    var posterPath: String,
    var overView: String,
    var id: String
)