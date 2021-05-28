package com.andresrivas.bazpelculasyseries.data.services

import com.google.gson.annotations.SerializedName

data class MovieResponse (
    @SerializedName("page") var page: Int,
    @SerializedName("results") var resultList: List<MovieResult>)

data class MovieResult (
    @SerializedName("title") var title: String?,
    @SerializedName("poster_path") var posterPath: String,
    @SerializedName("overview") var overView: String,
    @SerializedName("id") var id: String)