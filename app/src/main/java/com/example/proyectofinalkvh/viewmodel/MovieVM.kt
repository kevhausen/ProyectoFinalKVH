package com.example.proyectofinalkvh.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.proyectofinalkvh.model.dataclass.moviepopular.MoviePopular
import com.example.proyectofinalkvh.model.repository.MovieRepo

class MovieVM(application: Application):AndroidViewModel(application) {
    private val repository=MovieRepo(application)

    init {
        loadDBData()
    }

    fun loadDBData(){
        repository.InsertWebDataToDB()
    }

    fun getPopularMovies():LiveData<MoviePopular>{
        return repository.getPopularMoviesFromDB()
    }
}