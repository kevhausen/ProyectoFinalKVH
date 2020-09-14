package com.example.proyectofinalkvh.viewmodel

import android.app.Application
import androidx.fragment.app.Fragment
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.proyectofinalkvh.R
import com.example.proyectofinalkvh.model.dataclass.moviedetails.MovieDetails
import com.example.proyectofinalkvh.model.dataclass.moviepopular.MoviePopular
import com.example.proyectofinalkvh.model.dataclass.movievideos.MovieVideos
import com.example.proyectofinalkvh.model.repository.MovieRepo
import com.example.proyectofinalkvh.view.MainActivity
import kotlinx.android.synthetic.main.activity_main.*

class MovieVM(application: Application):AndroidViewModel(application) {
    private val repository=MovieRepo(application)



    init {
        loadDBData()
    }

    fun loadDBData(){
        repository.insertWebDataToDB()
    }

    fun getPopularMovies():LiveData<MoviePopular>{
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
}