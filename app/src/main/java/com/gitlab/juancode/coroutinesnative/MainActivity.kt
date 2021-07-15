package com.gitlab.juancode.coroutinesnative

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.gitlab.juancode.coroutinesnative.databinding.ActivityMainBinding
import com.gitlab.juancode.coroutinesnative.server.MovieClient
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MoviesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = MoviesAdapter(emptyList())
        binding.recycler.adapter = adapter

        lifecycleScope.launch {
            val apiKey = getString(R.string.api_key)
            val listPopularMovies = MovieClient.service.listPopularMovies(apiKey)
            adapter.movies = listPopularMovies.results
            adapter.notifyDataSetChanged()
        }
    }
}