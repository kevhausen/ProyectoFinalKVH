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


@Database(entities = [MoviePopular::class,MovieDetails::class,MovieVideos::class],version=17)
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
                    .fallbackToDestructiveMigrationFrom(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26).build()
                INSTANCE=newInstance
                return newInstance
            }
        }

    }
}