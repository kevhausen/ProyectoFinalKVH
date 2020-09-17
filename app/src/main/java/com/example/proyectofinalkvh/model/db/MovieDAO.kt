package com.example.proyectofinalkvh.model.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.proyectofinalkvh.model.dataclass.moviedetails.MovieDetails
import com.example.proyectofinalkvh.model.dataclass.moviepopular.MoviePopular
import com.example.proyectofinalkvh.model.dataclass.moviepopular.Result
import com.example.proyectofinalkvh.model.dataclass.movievideos.MovieVideos

@Dao
interface MovieDAO {

    //MOVIE POPULAR
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPopularMoviesResultInDB(listResult:List<Result>) //moviepopular ya entrega una lista con peliculas, por lo tanto no es necesario poner list<moviepopular>

    @Query("SELECT * FROM result_table")
    fun getPopularMoviesResultsFromDB():LiveData<List<Result>>

    //MOVIE DETAILS
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieDetailsInDB(movieDetails: MovieDetails)

    @Query("SELECT * FROM movie_details_table WHERE id =:idObtained")
    fun getMovieDetailById(idObtained:Int):LiveData<MovieDetails>

    //MOVIE VIDEOS
    @Insert (onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieVideosInDB(movieVideos: MovieVideos)

    @Query("SELECT * FROM movie_videos_table WHERE id =:idObtained")
    fun getMovieVideosById(idObtained: Int):LiveData<MovieVideos>

}