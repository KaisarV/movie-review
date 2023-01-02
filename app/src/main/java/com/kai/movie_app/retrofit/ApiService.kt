package com.kai.movie_app.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = ""

class ApiService {

    val endpoint:ApiEndpoint

    get(){
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()

        return retrofit.create(ApiEndpoint::class.java)
    }
}