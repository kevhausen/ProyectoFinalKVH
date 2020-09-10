package com.example.proyectofinalkvh.model.retrofit

import com.example.proyectofinalkvh.model.dataclass.moviedetails.MovieDetails
import com.example.proyectofinalkvh.model.dataclass.moviepopular.MoviePopular
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieAPI {

    //dejar api como constante cada vez que se llame a la funcion groupList
    @GET("/movie/{movie_id}")
    fun movieDetails(@Path("movie_id") movieId: Int): Call<MutableList<MovieDetails?>?>?

    @GET("movie/popular")
    fun moviePopular():Call<MoviePopular>

}