package com.example.proyectofinalkvh.model.retrofit

import com.example.proyectofinalkvh.model.dataclass.moviedetails.MovieDetails
import com.example.proyectofinalkvh.model.dataclass.moviepopular.MoviePopular
import com.example.proyectofinalkvh.model.dataclass.movievideos.MovieVideos
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieAPI {

    @GET("movie/{movie_id}")
    fun movieDetails(@Path("movie_id") movieId: Int): Call<MovieDetails?>?

    @GET("movie/popular")
    fun moviePopular():Call<MoviePopular>

    @GET("movie/{movie_id}/videos")
    fun movieVideos(@Path("movie_id") movieId: Int): Call<MovieVideos?>?

}