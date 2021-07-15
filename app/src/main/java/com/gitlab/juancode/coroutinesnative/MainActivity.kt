package com.gitlab.juancode.coroutinesnative

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.gitlab.juancode.coroutinesnative.databinding.ActivityMainBinding
import com.gitlab.juancode.coroutinesnative.server.MovieClient
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

        thread {
            val apiKey = getString(R.string.api_key)
            val listPopularMovies = MovieClient.service.listPopularMovies(apiKey)
            val body = listPopularMovies.execute().body()


            runOnUiThread {
                if (body != null) {
                    adapter.movies = body.results
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }
}