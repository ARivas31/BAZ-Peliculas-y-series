package com.andresrivas.bazpelculasyseries.presentation.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.andresrivas.bazpelculasyseries.R
import com.andresrivas.bazpelculasyseries.common.domain.UseCase
import com.andresrivas.bazpelculasyseries.domain.model.TrendingMoviesResultModel
import com.andresrivas.bazpelculasyseries.injection.UseCaseProvider
import com.andresrivas.bazpelculasyseries.presentation.view.adapters.MovieAdapter
import com.andresrivas.bazpelculasyseries.presentation.view.adapters.MovieAdapter.OnMovieItemClickListener
import com.andresrivas.bazpelculasyseries.presentation.viewmodel.TrendingViewModel
import com.andresrivas.bazpelculasyseries.presentation.viewmodel.TrendingViewModelFactory
import com.andresrivas.bazpelculasyseries.tools.ResultAPI
import kotlinx.android.synthetic.main.fragment_trending.*

class TrendingFragment : Fragment() {

    private val trendingViewModel: TrendingViewModel by viewModels {
        TrendingViewModelFactory(
            UseCaseProvider().provideGetTrendingUseCase(),
            UseCaseProvider().provideGetMovieVideoUseCase()
        )
    }
    private lateinit var root: View
    private lateinit var moviesAdapter: MovieAdapter
    private lateinit var intent: Intent
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        trendingViewModel.getTrendingMovies()
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_trending, container, false)
        trendingViewModel.imagesMovies.observe(viewLifecycleOwner, {
            when(it) {
                is ResultAPI.OnSuccess -> {
                    moviesAdapter = MovieAdapter(it.data.resultList, object: OnMovieItemClickListener{
                        override fun onItemClick(trendingMoviesResultModel: TrendingMoviesResultModel) {
                            intent = Intent(context, MovieDetailActivity::class.java)
                            intent.putExtra(MovieDetailActivity.MOVIE_RESULT_TITLE_EXTRA, trendingMoviesResultModel.title)
                            intent.putExtra(MovieDetailActivity.MOVIE_RESULT_POSTER_EXTRA, trendingMoviesResultModel.posterPath)
                            intent.putExtra(MovieDetailActivity.MOVIE_RESULT_OVERVIEW_EXTRA, trendingMoviesResultModel.overView.plus(trendingMoviesResultModel.overView).plus(trendingMoviesResultModel.overView).plus(trendingMoviesResultModel.overView))
                            intent.putExtra(MovieDetailActivity.MOVIE_RESULT_MOVIE_ID_EXTRA, trendingMoviesResultModel.id)
                            trendingViewModel.getMovieVideo(trendingMoviesResultModel.id)
                        }
                    })
                    trendingRV.setHasFixedSize(true)
                    trendingRV.layoutManager = LinearLayoutManager(context)
                    trendingRV.adapter = moviesAdapter
                } else -> {
                    Toast.makeText(context, "Couldn't load movies", Toast.LENGTH_SHORT).show()
                }
            }
        })
        trendingViewModel.videoMovie.observe(viewLifecycleOwner, { resultAPI ->
            when(resultAPI) {
                is ResultAPI.OnSuccess -> {
                    if (!resultAPI.data.resultList.isNullOrEmpty()) {
                        intent.putExtra(MovieDetailActivity.MOVIE_RESULT_VIDEO_KEY_EXTRA, resultAPI.data.resultList[0].key)
                    }
                    context?.let {
                        startActivity(intent)
                    }
                } else -> {
                    Toast.makeText(context, "Couldn't load movie details", Toast.LENGTH_SHORT).show()
                }
            }
        })
        return root
    }
}