package com.example.proyectofinalkvh.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.proyectofinalkvh.model.dataclass.moviedetails.MovieDetails
import com.example.proyectofinalkvh.model.dataclass.moviefavorite.MovieFavorite
import com.example.proyectofinalkvh.model.dataclass.moviepopular.MoviePopular
import com.example.proyectofinalkvh.model.dataclass.moviepopular.Result
import com.example.proyectofinalkvh.model.dataclass.movievideos.MovieVideos
import com.example.proyectofinalkvh.model.repository.MovieRepo

class MovieVM(application: Application):AndroidViewModel(application) {
    private val repository=MovieRepo(application)

        //tendria que llegar el numero de paginas con una interface o llamar este metodo directamente desde el fragment



    fun cachePopularData(page:Int){
        repository.insertWebDataToDB(page)
    }

    fun getPopularMovies():LiveData<List<Result>>{
        return repository.getPopularMoviesFromDB()
    }


    //MOVIE DETAILS
    fun getMovieDetailsById(id:Int):LiveData<MovieDetails>{
        return repository.getMovieDetailsFromDB(id)
    }
    fun cacheDetailData(id:Int){
        repository.insertMovieDetailsInDB(id)
    }

    //MOVIE VIDEOS
    fun getMovieVideosById(id: Int):LiveData<MovieVideos>{
        return repository.getMovieVideosFromDB(id)
    }

    fun cacheVideoData(id:Int){
        repository.insertMovieVideosIntoDB(id)
    }

    //MOVIE FAVORITE
    fun saveFavoriteMovieIntoLocalDB(movieFav:MovieFavorite){
        repository.insertFavoriteMovieIntoDB(movieFav)
    }

    fun getAllFavoriteMoviesFromDB():LiveData<List<MovieFavorite>>{
        return repository.getFavoriteMovies()
    }
}