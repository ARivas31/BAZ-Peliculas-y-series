package com.andresrivas.bazpeliculasyseries.presentation.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.andresrivas.bazpeliculasyseries.databinding.FragmentTrendingBinding
import com.andresrivas.bazpeliculasyseries.presentation.view.adapters.MovieAdapter
import com.andresrivas.bazpeliculasyseries.presentation.viewmodel.TrendingViewModel
import com.andresrivas.bazpeliculasyseries.tools.ResultAPI
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TrendingFragment : Fragment() {

    private lateinit var binding: FragmentTrendingBinding
    private lateinit var moviesAdapter: MovieAdapter
    private lateinit var intent: Intent
    private val trendingViewModel: TrendingViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTrendingBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        trendingViewModel.getTrendingMovies()
        trendingViewModel.imagesMovies.observe(viewLifecycleOwner) {
            when (it) {
                is ResultAPI.OnSuccess -> {
                    moviesAdapter =
                        MovieAdapter(
                            it.data.resultList
                        ) { trendingMoviesResultModel ->
                            intent = Intent(context, MovieDetailActivity::class.java)
                            intent.putExtra(
                                MovieDetailActivity.MOVIE_RESULT_TITLE_EXTRA,
                                trendingMoviesResultModel.title
                            )
                            intent.putExtra(
                                MovieDetailActivity.MOVIE_RESULT_POSTER_EXTRA,
                                trendingMoviesResultModel.posterPath
                            )
                            intent.putExtra(
                                MovieDetailActivity.MOVIE_RESULT_OVERVIEW_EXTRA,
                                trendingMoviesResultModel.overView
                            )
                            intent.putExtra(
                                MovieDetailActivity.MOVIE_RESULT_MOVIE_ID_EXTRA,
                                trendingMoviesResultModel.id
                            )
                            trendingViewModel.getMovieVideo(trendingMoviesResultModel.id)
                        }
                    binding.trendingRV.setHasFixedSize(true)
                    binding.trendingRV.layoutManager = LinearLayoutManager(context)
                    binding.trendingRV.adapter = moviesAdapter
                }
                else -> {
                    Toast.makeText(context, "Couldn't load movies", Toast.LENGTH_SHORT).show()
                }
            }
        }
        trendingViewModel.videoMovie.observe(viewLifecycleOwner) { resultAPI ->
            when (resultAPI) {
                is ResultAPI.OnSuccess -> {
                    if (resultAPI.data.resultList.isNotEmpty()) {
                        intent.putExtra(
                            MovieDetailActivity.MOVIE_RESULT_VIDEO_KEY_EXTRA,
                            resultAPI.data.resultList[0].key
                        )
                    }
                    context?.let {
                        startActivity(intent)
                    }
                }
                else -> {
                    Toast.makeText(context, "Couldn't load movie details", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }
}