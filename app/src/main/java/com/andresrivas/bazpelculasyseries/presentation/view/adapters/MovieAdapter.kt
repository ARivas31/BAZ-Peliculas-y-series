package com.andresrivas.bazpelculasyseries.presentation.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andresrivas.bazpelculasyseries.R
import com.andresrivas.bazpelculasyseries.utilities.fromUrl
import com.andresrivas.bazpelculasyseries.data.services.MovieResult
import com.andresrivas.bazpelculasyseries.domain.model.TrendingMoviesResultModel
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter(
    private val images: List<TrendingMoviesResultModel>,
    private val onMovieItemClickListener: OnMovieItemClickListener
) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_movie, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = images[position]
        holder.bind(item, onMovieItemClickListener)
    }

    override fun getItemCount(): Int {
        return images.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(result: TrendingMoviesResultModel, onMovieItemClickListener: OnMovieItemClickListener) {
            itemView.movieImageView.fromUrl("https://image.tmdb.org/t/p/w500/${result.posterPath}")
            itemView.movieTitleLabel.text = result.title
            itemView.setOnClickListener { onMovieItemClickListener.onItemClick(result) }
        }
    }

    interface OnMovieItemClickListener {
        fun onItemClick(trendingMoviesResultModel: TrendingMoviesResultModel)
    }
}