package com.kai.movie_app.retrofit

import com.kai.movie_app.model.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiEndpoint {

    @GET("now_playing")
    fun getMovieNowPlaying(
        @Query("api_key") api_key:String,
        @Query("page") page:Int
    ): Call<MovieResponse>

    @GET("popular")
    fun getMoviePopular(
        @Query("api_key") api_key:String,
        @Query("page") page:Int
    ): Call<MovieResponse>

    @GET("top_rated")
    fun getMovieTopRated(
        @Query("api_key") api_key:String,
        @Query("page") page:Int
    ): Call<MovieResponse>


    @GET("upcoming")
    fun getMovieUpcoming(
        @Query("api_key") api_key:String,
        @Query("page") page:Int
    ): Call<MovieResponse>
}