package com.example.proyectofinalkvh.model.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.proyectofinalkvh.model.dataclass.moviepopular.MoviePopular

@Dao
interface MovieDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPopularMoviesInDB(movies:MoviePopular) //moviepopular ya entrega una lista con peliculas, por lo tanto no es necesario poner list<moviepopular>

    @Query("SELECT * FROM movie_popular_table")
    fun getPopularMoviesFromDB():LiveData<MoviePopular>
}