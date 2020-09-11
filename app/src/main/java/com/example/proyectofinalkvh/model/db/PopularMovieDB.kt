package com.example.proyectofinalkvh.model.db

import android.content.Context
import androidx.room.*
import com.example.proyectofinalkvh.model.dataclass.moviepopular.MoviePopular
import com.example.proyectofinalkvh.model.dataclass.moviepopular.ResultConverter
import com.example.proyectofinalkvh.model.dataclass.moviepopular.StringListConverter


@Database(entities = [MoviePopular::class],version=6)
@TypeConverters(StringListConverter::class,ResultConverter::class)
abstract class PopularMovieDB:RoomDatabase() {
abstract fun daoPopularMovie():MovieDAO
    companion object{
        @Volatile
        private var INSTANCE: PopularMovieDB?=null

        fun getMovieDB(context : Context):PopularMovieDB{
            val createdInstance = INSTANCE
            if(createdInstance!=null){
                return createdInstance
            }
            synchronized(this){
                val newInstance = Room.databaseBuilder(context.applicationContext,PopularMovieDB::class.java,"popular_movies_db")
                    .fallbackToDestructiveMigrationFrom(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18).build()
                INSTANCE=newInstance
                return newInstance
            }
        }

    }
}