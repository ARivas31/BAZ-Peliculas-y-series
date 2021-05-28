package com.andresrivas.bazpelculasyseries.domain.usecases

import com.andresrivas.bazpelculasyseries.common.domain.UseCase
import com.andresrivas.bazpelculasyseries.domain.model.MoviesVideoModel
import com.andresrivas.bazpelculasyseries.domain.respository.MoviesRepository
import com.andresrivas.bazpelculasyseries.tools.ResultAPI
import io.reactivex.rxjava3.core.Single

class GetMovieVideoUseCase (
    private val moviesRepository: MoviesRepository
    ) : UseCase<Params, MoviesVideoModel> {
        override fun execute(params: Params?): Single<ResultAPI<MoviesVideoModel>> {
            return params?.let {
                moviesRepository.getMoviesVideo(it.movieId)
            } ?: moviesRepository.getMoviesVideo("")
        }
    }
data class Params (
    val movieId: String)
