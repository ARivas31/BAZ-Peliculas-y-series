package com.andresrivas.bazpeliculasyseries.presentation.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.andresrivas.bazpeliculasyseries.R
import com.andresrivas.bazpeliculasyseries.databinding.ActivityMovieDetailBinding
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer

class MovieDetailActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {

    private lateinit var binding: ActivityMovieDetailBinding

    companion object {
        const val MOVIE_RESULT_TITLE_EXTRA = "movieResultTitleExtra"
        const val MOVIE_RESULT_POSTER_EXTRA = "movieResultPosterExtra"
        const val MOVIE_RESULT_OVERVIEW_EXTRA = "movieResultOverviewExtra"
        const val MOVIE_RESULT_MOVIE_ID_EXTRA = "movieResultIdExtra"
        const val MOVIE_RESULT_VIDEO_KEY_EXTRA = "movieResultVideoKeyExtra"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            setView()
        }
        binding.youtubePlayerContainer.initialize(getString(R.string.youtube_api_key), this)
    }

    private fun setView() {
        binding.movieTitleLabel.text = intent.getStringExtra(MOVIE_RESULT_TITLE_EXTRA)
        binding.movieOverviewLabel.text = intent.getStringExtra(MOVIE_RESULT_OVERVIEW_EXTRA)
    }

    override fun onInitializationSuccess(p0: YouTubePlayer.Provider?, youtubePlayer: YouTubePlayer?, isRestored: Boolean) {
        when(!isRestored) {
            true -> {
                intent.getStringExtra(MOVIE_RESULT_VIDEO_KEY_EXTRA)?.let {
                    youtubePlayer?.cueVideo(intent.getStringExtra(MOVIE_RESULT_VIDEO_KEY_EXTRA))
                } ?: Toast.makeText(this, "No video for current movie!", Toast.LENGTH_LONG).show()
            }
            false -> {}
        }
    }

    override fun onInitializationFailure(p0: YouTubePlayer.Provider?, p1: YouTubeInitializationResult?) {
        p1?.let {
            if (p1.isUserRecoverableError) {
                p1.getErrorDialog(this, 1).show()
            } else {
                Toast.makeText(this, "Error al iniciar Youtube $p1", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == 1) {
            getYoutubePlayerProvider().initialize(getString(R.string.youtube_api_key), this)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun getYoutubePlayerProvider(): YouTubePlayer.Provider = binding.youtubePlayerContainer
}