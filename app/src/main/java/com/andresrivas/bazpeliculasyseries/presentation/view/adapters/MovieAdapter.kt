package com.andresrivas.bazpeliculasyseries.presentation.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andresrivas.bazpeliculasyseries.databinding.ItemMovieBinding
import com.andresrivas.bazpeliculasyseries.domain.model.TrendingMoviesResultModel
import com.andresrivas.bazpeliculasyseries.utilities.fromUrl

class MovieAdapter(
    private val images: List<TrendingMoviesResultModel>,
    private val onMovieItemClickListener: OnMovieItemClickListener
) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(images[position], onMovieItemClickListener)
    }

    override fun getItemCount(): Int = images.size

    class ViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            result: TrendingMoviesResultModel,
            onMovieItemClickListener: OnMovieItemClickListener
        ) {
            binding.apply {
                movieImageView.fromUrl("https://image.tmdb.org/t/p/w500/${result.posterPath}")
                movieTitleLabel.text = result.title
                root.setOnClickListener { onMovieItemClickListener.onItemClick(result) }
            }
        }
    }

    fun interface OnMovieItemClickListener {
        fun onItemClick(trendingMoviesResultModel: TrendingMoviesResultModel)
    }
}