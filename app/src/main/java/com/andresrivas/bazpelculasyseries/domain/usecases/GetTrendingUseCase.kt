package com.andresrivas.bazpelculasyseries.domain.usecases

import com.andresrivas.bazpelculasyseries.common.domain.UseCase
import com.andresrivas.bazpelculasyseries.domain.model.TrendingMoviesModel
import com.andresrivas.bazpelculasyseries.domain.respository.MoviesRepository
import com.andresrivas.bazpelculasyseries.tools.ResultAPI
import io.reactivex.rxjava3.core.Single

class GetTrendingUseCase(
    private val moviesRepository: MoviesRepository
    ) : UseCase<Nothing, TrendingMoviesModel>{
    override fun execute(params: Nothing?): Single<ResultAPI<TrendingMoviesModel>> {
        return moviesRepository.getMoviesTrending()
    }
}