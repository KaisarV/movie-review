package com.kai.movie_app.ui

import android.content.Intent
import android.os.Bundle
import android.telecom.Call
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity

import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kai.movie_app.R
import com.kai.movie_app.adapter.MainAdapter
import com.kai.movie_app.databinding.ActivityMainBinding
import com.kai.movie_app.model.Constant
import com.kai.movie_app.model.Movie
import com.kai.movie_app.model.MovieResponse
import com.kai.movie_app.retrofit.ApiService
import retrofit2.Callback
import retrofit2.Response


const val MOVIE_POPULAR = 0
const val MOVIE_NOW_PLAYING = 1
const val MOVIE_TOP_RATED = 2
const val MOVIE_UPCOMING = 3

class MainActivity : AppCompatActivity() {

    private lateinit var mainAdapter: MainAdapter

    private lateinit var binding: ActivityMainBinding

    private var movieCategory = 0
    private val apiService = ApiService().endpoint

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        setupRecyclerView()
    }

    override fun onStart(){
        super.onStart()
        getMovie()
    }

//    private fun setUpView(){
//        binding.fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//        }
//    }

    private fun setupRecyclerView(){
        mainAdapter = MainAdapter(arrayListOf(), object: MainAdapter.OnAdapterListener{
            override fun onClick(movie: Movie){
                showMessage(movie.title!!)
                Constant.MOVIE_ID = movie.id!!
                Constant.MOVIE_TITLE = movie.title

                startActivity(Intent(applicationContext, DetailActivity::class.java))
            }
        })
        val recyclerView:RecyclerView = findViewById(R.id.list_movie)

        recyclerView.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = mainAdapter
        }

    }
    private fun getMovie(){
        showLoading(true)

        var apiCall:retrofit2.Call<MovieResponse>? = null

        when(movieCategory){
            MOVIE_POPULAR ->{
                apiCall = ApiService().endpoint.getMoviePopular(Constant.API_KEY, 1)
            }

            MOVIE_NOW_PLAYING ->{
                apiCall = ApiService().endpoint.getMovieNowPlaying(Constant.API_KEY, 1)
            }

            MOVIE_TOP_RATED -> {
                apiCall = ApiService().endpoint.getMovieTopRated(Constant.API_KEY, 1)
            }

            MOVIE_UPCOMING -> {
                apiCall = ApiService().endpoint.getMovieUpcoming(Constant.API_KEY, 1)
            }
        }

        apiCall!!
            .enqueue(object: Callback<MovieResponse>{
                override fun onResponse(
                    call: retrofit2.Call<MovieResponse>,
                    response: Response<MovieResponse>
                ) {
                    showLoading(false)
                    if(response.isSuccessful){
                        showMovie(response.body()!!)
                    }
                }

                override fun onFailure(call: retrofit2.Call<MovieResponse>, t: Throwable) {
                    Log.d("ERROR GET DATA", "errorResponse : ${t.toString()}")
                    showLoading(false)
                }

            })
    }

    fun showLoading(loading:Boolean){
        val progressMovie:ProgressBar = findViewById(R.id.progress_movie)
        when (loading){
            true -> progressMovie.visibility = View.VISIBLE
            false -> progressMovie.visibility = View.GONE
        }
    }

    fun showMovie(response:MovieResponse){
//        Log.d("aaaaaaaa", "responseMovie: $response")
//
//        for (movie in response.results){
//            Log.d("aab", "movie title : ${movie.title}")
//        }

        mainAdapter.setData(response.results)
    }

    fun showMessage(msg:String){
        Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_popular -> {
                showMessage("Popular Movie Selected")
                movieCategory = MOVIE_POPULAR
                getMovie()
                true
            }
            R.id.action_now_playing -> {
                showMessage("Now Playing Movie Selected")
                movieCategory = MOVIE_NOW_PLAYING
                getMovie()
                true
            }
            R.id.action_top_rated -> {
                showMessage("Top Rated Movie Selected")
                movieCategory = MOVIE_TOP_RATED
                getMovie()
                true
            }

            R.id.action_upcoming -> {
                showMessage("Upcoming Movie Selected")
                movieCategory = MOVIE_UPCOMING
                getMovie()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}