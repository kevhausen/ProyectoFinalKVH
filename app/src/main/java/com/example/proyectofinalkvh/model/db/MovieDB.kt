package com.example.proyectofinalkvh.model.db

import android.content.Context
import androidx.room.*
import com.example.proyectofinalkvh.model.dataclass.moviedetails.*
import com.example.proyectofinalkvh.model.dataclass.moviepopular.IntListConverter
import com.example.proyectofinalkvh.model.dataclass.moviepopular.MoviePopular
import com.example.proyectofinalkvh.model.dataclass.moviepopular.ResultConverter
import com.example.proyectofinalkvh.model.dataclass.moviepopular.StringListConverter
import com.example.proyectofinalkvh.model.dataclass.movievideos.MovieVideos
import com.example.proyectofinalkvh.model.dataclass.movievideos.VideoResultConverter


@Database(entities = [MoviePopular::class,MovieDetails::class,MovieVideos::class],version=1)
@TypeConverters(
    StringListConverter::class,
    ResultConverter::class,
    GenreConverter::class,
    ProductionCompanyConverter::class,
    ProductionCountryConverter::class,
    SpokenLanguageConverter::class,
    IntListConverter::class,
    VideoResultConverter::class)
abstract class MovieDB:RoomDatabase() {
abstract fun daoPopularMovie():MovieDAO
    companion object{
        @Volatile
        private var INSTANCE: MovieDB?=null

        fun getMovieDB(context : Context):MovieDB{
            val createdInstance = INSTANCE
            if(createdInstance!=null){
                return createdInstance
            }
            synchronized(this){
                val newInstance = Room.databaseBuilder(context.applicationContext,MovieDB::class.java,"movies_db")
                    .build()
                INSTANCE=newInstance
                return newInstance
            }
        }

    }
}