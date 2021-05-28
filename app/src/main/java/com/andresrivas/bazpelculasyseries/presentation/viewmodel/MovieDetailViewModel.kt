package com.andresrivas.bazpelculasyseries.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.andresrivas.bazpelculasyseries.domain.model.MoviesVideoModel
import com.andresrivas.bazpelculasyseries.domain.usecases.GetMovieVideoUseCase
import com.andresrivas.bazpelculasyseries.domain.usecases.Params
import com.andresrivas.bazpelculasyseries.tools.ResultAPI
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MovieDetailViewModel (
    private val getMovieVideoUseCase: GetMovieVideoUseCase
) : ViewModel() {
    private val disposable = CompositeDisposable()

    private val _videoMovie = MutableLiveData<ResultAPI<MoviesVideoModel>>()
    val videoMovie: LiveData<ResultAPI<MoviesVideoModel>> get() = _videoMovie

    fun getMovieVideo(movieId: String) {
        disposable.add(
            getMovieVideoUseCase.execute(Params(movieId))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _videoMovie.value = it
                }, { error ->
                    Log.e("MovieVieModel", "Error")
                })
        )
    }
}