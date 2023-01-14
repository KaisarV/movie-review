package com.kai.movie_app.model

data class DetailResponse (
    val id:Int?,
    val title:String?,
    val backdrop_path:String?,
    val poster_path:String?,
    val overview:String?,
    val release_date:String?,
    val genres:List<Genre>?,
    val vote_average:Double?,
    )
