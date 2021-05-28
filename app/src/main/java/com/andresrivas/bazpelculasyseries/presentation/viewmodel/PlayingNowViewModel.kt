package com.andresrivas.bazpelculasyseries.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.andresrivas.bazpelculasyseries.domain.model.MoviesVideoModel
import com.andresrivas.bazpelculasyseries.domain.model.TrendingMoviesModel
import com.andresrivas.bazpelculasyseries.domain.usecases.GetMovieVideoUseCase
import com.andresrivas.bazpelculasyseries.domain.usecases.GetPlayingNowUseCase
import com.andresrivas.bazpelculasyseries.domain.usecases.Params
import com.andresrivas.bazpelculasyseries.tools.ResultAPI
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class PlayingNowViewModel(
    private val getPlayingNowUseCase: GetPlayingNowUseCase,
    private val getMovieVideoUseCase: GetMovieVideoUseCase
) : ViewModel() {

    private val disposable = CompositeDisposable()

    private val _imagesMovies = MutableLiveData<ResultAPI<TrendingMoviesModel>>()
    val imagesMovies: LiveData<ResultAPI<TrendingMoviesModel>> get() = _imagesMovies

    private val _videoMovie = MutableLiveData<ResultAPI<MoviesVideoModel>>()
    val videoMovie: LiveData<ResultAPI<MoviesVideoModel>> get() = _videoMovie

    fun getPlayingNowMovies() {
        disposable.add(
            getPlayingNowUseCase.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _imagesMovies.value = it
                }, { error ->
                    Log.e("PlayingNowViewModel", "Error")
                })
        )
    }

    fun getMovieVideo(idMovie: String) {
        disposable.add(
            getMovieVideoUseCase.execute(Params(idMovie))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _videoMovie.value = it
                }, { error ->
                    Log.e("PlayingNowViewModel", "Error")
                })
        )
    }
}