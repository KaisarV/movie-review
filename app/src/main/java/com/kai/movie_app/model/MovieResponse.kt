package com.kai.movie_app.model

data class MovieResponse (
    val total_page:Int,
    val results:List<Movie>,
    )