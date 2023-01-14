package com.kai.movie_app.retrofit

import com.kai.movie_app.model.DetailResponse
import com.kai.movie_app.model.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiEndpoint {

    @GET("movie/now_playing")
    fun getMovieNowPlaying(
        @Query("api_key") api_key:String,
        @Query("page") page:Int
    ): Call<MovieResponse>

    @GET("movie/popular")
    fun getMoviePopular(
        @Query("api_key") api_key:String,
        @Query("page") page:Int
    ): Call<MovieResponse>

    @GET("movie/top_rated")
    fun getMovieTopRated(
        @Query("api_key") api_key:String,
        @Query("page") page:Int
    ): Call<MovieResponse>


    @GET("movie/upcoming")
    fun getMovieUpcoming(
        @Query("api_key") api_key:String,
        @Query("page") page:Int
    ): Call<MovieResponse>

    @GET("movie/{movie_id}")
    fun getMovieDetail(
        //Replace movie id
        @Path("movie_id") movie_id:Int,
        @Query("api_key") api_key:String,
    ): Call<DetailResponse>
}