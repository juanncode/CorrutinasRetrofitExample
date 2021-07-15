package com.gitlab.juancode.coroutinesnative

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.gitlab.juancode.coroutinesnative.databinding.ActivityMainBinding
import com.gitlab.juancode.coroutinesnative.model.MovieResult
import com.gitlab.juancode.coroutinesnative.server.MovieClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
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

        val apiKey = getString(R.string.api_key)

        MovieClient.service.listPopularMovies(apiKey).enqueue(object : Callback<MovieResult>{
            override fun onResponse(call: Call<MovieResult>, response: Response<MovieResult>) {
                val body = response.body()
                if (body != null) {
                    adapter.movies = body.results
                    adapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<MovieResult>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Algo salió mal", Toast.LENGTH_SHORT).show()
            }
        })
    }
}