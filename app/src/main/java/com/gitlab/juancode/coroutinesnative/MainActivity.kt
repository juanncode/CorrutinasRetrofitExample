package com.gitlab.juancode.coroutinesnative

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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

        Thread {
            val apiKey = getString(R.string.api_key)
            val listPopularMovies = MovieClient.service.listPopularMovies(apiKey)
            val body = listPopularMovies.execute().body()

            runOnUiThread(object : Runnable {
                override fun run() {
                    if (body != null) {
                        adapter.movies = body.results
                        adapter.notifyDataSetChanged()
                    }
                }
            })
        }.start()
    }
}