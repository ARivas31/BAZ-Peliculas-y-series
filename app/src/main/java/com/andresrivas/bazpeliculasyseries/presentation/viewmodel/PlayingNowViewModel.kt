package com.andresrivas.bazpeliculasyseries.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andresrivas.bazpeliculasyseries.domain.model.MoviesVideoModel
import com.andresrivas.bazpeliculasyseries.domain.model.TrendingMoviesModel
import com.andresrivas.bazpeliculasyseries.domain.usecases.GetMovieVideoUseCase
import com.andresrivas.bazpeliculasyseries.domain.usecases.GetPlayingNowUseCase
import com.andresrivas.bazpeliculasyseries.domain.usecases.Params
import com.andresrivas.bazpeliculasyseries.tools.ResultAPI
import com.andresrivas.bazpeliculasyseries.utilities.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayingNowViewModel @Inject constructor(
    private val getPlayingNowUseCase: GetPlayingNowUseCase,
    private val getMovieVideoUseCase: GetMovieVideoUseCase
) : ViewModel() {

    private val _imagesMovies = SingleLiveEvent<ResultAPI<TrendingMoviesModel>>()
    val imagesMovies: LiveData<ResultAPI<TrendingMoviesModel>> get() = _imagesMovies

    private val _videoMovie = SingleLiveEvent<ResultAPI<MoviesVideoModel>>()
    val videoMovie: LiveData<ResultAPI<MoviesVideoModel>> get() = _videoMovie

    fun getPlayingNowMovies() {
        viewModelScope.launch {
            _imagesMovies.value = getPlayingNowUseCase.execute()
        }
    }

    fun getMovieVideo(idMovie: String) {
        viewModelScope.launch {
            _videoMovie.value = getMovieVideoUseCase.execute(Params(idMovie))
        }
    }
}