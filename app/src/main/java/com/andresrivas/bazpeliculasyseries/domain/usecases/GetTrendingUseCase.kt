package com.andresrivas.bazpeliculasyseries.domain.usecases

import com.andresrivas.bazpeliculasyseries.common.domain.UseCase
import com.andresrivas.bazpeliculasyseries.domain.model.TrendingMoviesModel
import com.andresrivas.bazpeliculasyseries.domain.repository.MoviesRepository
import com.andresrivas.bazpeliculasyseries.tools.ResultAPI
import javax.inject.Inject

class GetTrendingUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
) : UseCase<Nothing, TrendingMoviesModel> {
    override suspend fun execute(params: Nothing?): ResultAPI<TrendingMoviesModel> {
        return moviesRepository.getMoviesTrending()
    }
}