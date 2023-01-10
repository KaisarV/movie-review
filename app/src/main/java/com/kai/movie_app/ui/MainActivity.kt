package com.kai.movie_app.ui

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
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kai.movie_app.R
import com.kai.movie_app.adapter.MainAdapter
import com.kai.movie_app.databinding.ActivityMainBinding
import com.kai.movie_app.model.Constant
import com.kai.movie_app.model.MovieResponse
import com.kai.movie_app.retrofit.ApiService
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var mainAdapter: MainAdapter

    private lateinit var binding: ActivityMainBinding

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
        mainAdapter = MainAdapter(arrayListOf())
        val recyclerView:RecyclerView = findViewById(R.id.list_movie)

        recyclerView.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = mainAdapter
        }

    }
    private fun getMovie(){
        showLoading(true)
        ApiService().endpoint.getMovieNowPlaying(Constant.API_KEY, 1)
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
                    Log.d("ERRORR", "errorResponse : ${t.toString()}")
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
        Log.d("OPTION", "KEPANGGIL NIH")
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_popular -> {
                showMessage("Popular Selected")
                true
            }
            R.id.action_now_playing -> {
                showMessage("Now Playing Selected")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}