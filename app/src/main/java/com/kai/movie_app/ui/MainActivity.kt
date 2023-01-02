package com.kai.movie_app.ui

import android.os.Bundle
import android.telecom.Call
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.kai.movie_app.R
import com.kai.movie_app.databinding.ActivityMainBinding
import com.kai.movie_app.model.Constant
import com.kai.movie_app.model.MovieResponse
import com.kai.movie_app.retrofit.ApiService
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)

        setSupportActionBar(binding.toolbar)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    override fun onStart(){
        super.onStart()
        getMovie()
    }

    fun getMovie(){
        ApiService().endpoint.getMovieNowPlaying(Constant.API_KEY, 1)
            .enqueue(object: Callback<MovieResponse>{
                override fun onResponse(
                    call: retrofit2.Call<MovieResponse>,
                    response: Response<MovieResponse>
                ) {
                    if(response.isSuccessful){
                        showMovie(response.body()!!)
                    }
                }

                override fun onFailure(call: retrofit2.Call<MovieResponse>, t: Throwable) {
                    Log.d("", t.toString())
                }

            })
    }

    fun showMovie(response:MovieResponse){
        Log.d("aaaaaaaa", "responseMovie: $response")

        for (movie in response.results){
            Log.d("aab", "movie title : ${movie.title}")
        }
    }

    fun showMessage(msg:String){
        Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

//    override fun onSupportNavigateUp(): Boolean {
//        val navController = findNavController(R.id.nav_host_fragment_content_main)
//        return navController.navigateUp(appBarConfiguration)
//                || super.onSupportNavigateUp()
//    }
}