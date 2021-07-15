package com.gitlab.juancode.coroutinesnative

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.gitlab.juancode.coroutinesnative.databinding.ActivityMainBinding
import com.gitlab.juancode.coroutinesnative.server.MovieClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.concurrent.thread

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
            val body = withContext(Dispatchers.IO) { listPopularMovies.execute().body() }
            if (body != null) {
                adapter.movies = body.results
                adapter.notifyDataSetChanged()
            }
        }
    }
}