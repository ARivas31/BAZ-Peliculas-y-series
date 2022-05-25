package com.andresrivas.bazpeliculasyseries.domain.model

class MoviesVideoModel (
    var id: Int,
    var resultList: List<MoviesVideoResultModel>)

data class MoviesVideoResultModel(
    var id: String,
    var key: String,
    var name: String,
    var site: String,
    var size: Int,
    var type: String)