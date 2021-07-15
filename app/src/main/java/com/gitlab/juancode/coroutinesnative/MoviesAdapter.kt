package com.gitlab.juancode.coroutinesnative

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gitlab.juancode.coroutinesnative.databinding.ViewMovieBinding
import com.gitlab.juancode.coroutinesnative.model.Movie

class MoviesAdapter(var movies: List<Movie>) : RecyclerView.Adapter<MoviesAdapter.MoviesHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_movie, parent, false)
        return MoviesHolder(view)
    }

    override fun onBindViewHolder(holder: MoviesHolder, position: Int) {
        val movie = movies[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int {
        return movies.size
    }


    class MoviesHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ViewMovieBinding.bind(view)

        fun bind(movie: Movie) {
            with(binding) {
                txtTitle.text = movie.title
                imgPoster.loadUrl("https://image.tmdb.org/t/p/w185/${movie.posterPath}")
            }
        }
    }
}