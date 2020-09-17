package com.example.proyectofinalkvh.model.repository

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import com.example.proyectofinalkvh.model.dataclass.moviedetails.MovieDetails
import com.example.proyectofinalkvh.model.dataclass.moviepopular.MoviePopular
import com.example.proyectofinalkvh.model.dataclass.moviepopular.Result
import com.example.proyectofinalkvh.model.dataclass.movievideos.MovieVideos
import com.example.proyectofinalkvh.model.db.MovieDB
import com.example.proyectofinalkvh.model.retrofit.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
/*aca quiero guardar lo que viene desde retrofit directo a la base de datos, para eso tengo que:
1.- instanciar retrofit para usar su metodo que hace el "fetch" a la api
2.- instanciar base de datos para poder usar el dao, el cual tiene la funcion "insert" y "mostrar movies"
3.- insertar datos obtenidos de internet directamente a la base de datos con una funcion
4.- hacer un tipo getter de la lista de peliculas que estan en la base de datos
*/
class MovieRepo(context:Context) {
    private val mContext=context
    private val retrofit=RetrofitClient.getRetrofitClientInstance()
    private val db=MovieDB.getMovieDB(context)
    private val dao=db.daoPopularMovie()

    fun insertWebDataToDB(){
        retrofit.moviePopular().enqueue(object : Callback<MoviePopular>{
            override fun onResponse(call: Call<MoviePopular>, response: Response<MoviePopular>) {
                response.body()?.let {
                    CoroutineScope(IO).launch {
                        it.results?.let { it1 -> dao.insertPopularMoviesResultInDB(it1) }
                    }
                }
            }

            override fun onFailure(call: Call<MoviePopular>, t: Throwable) {
                Toast.makeText(mContext,"Internet request failed",Toast.LENGTH_LONG).show()
            }
        })
    }

    fun getPopularMoviesFromDB():LiveData<List<Result>>{
        return dao.getPopularMoviesResultsFromDB()
    }


    //tengo que pasarle el id que llega al fragmentDetails desde el adapter
    //le llega la id, pero no se por que retrofit no hace la llamada. el throwable del onfailure me dice que le llega "vote_average" en ves de la id, y eso es mentira
    //DESPUES DE TRES HORAS CON EL ERROR DE ARRIBA, lo que decia del vote_average era verdad, el problema no era la id que le llegaba sino, que el vote_average en el pojo estaba como "Int"
    //cuando en realidad es un "Double". y el throwable lo dijo tode el rato jajja
    fun insertMovieDetailsInDB(id:Int){
        retrofit.movieDetails(id)?.enqueue(object : Callback<MovieDetails?>{
            override fun onResponse(call: Call<MovieDetails?>, response: Response<MovieDetails?>) {
                response.body()?.let {
                    CoroutineScope(IO).launch {
                        dao.insertMovieDetailsInDB(it)
                    }
                }
            }

            override fun onFailure(call: Call<MovieDetails?>, t: Throwable) {
                Log.d("kevin","Internet Request Failed $t")
            }
        })
    }

    fun getMovieDetailsFromDB(id:Int):LiveData<MovieDetails>{
        return dao.getMovieDetailById(id)
    }

    //MOVIE VIDEOS
    fun insertMovieVideosIntoDB(id:Int){
        retrofit.movieVideos(id)?.enqueue(object : Callback<MovieVideos?>{
            override fun onResponse(call: Call<MovieVideos?>, response: Response<MovieVideos?>) {
                response.body()?.let {
                    CoroutineScope(IO).launch {
                        dao.insertMovieVideosInDB(it)
                        Log.d("kevin","llega videos de internet ${it}")
                    }
                }
            }

            override fun onFailure(call: Call<MovieVideos?>, t: Throwable) {
                Log.d("kevin","Internet Request Failed $t")
            }

        })

    }
    fun getMovieVideosFromDB(id:Int):LiveData<MovieVideos>{
        return dao.getMovieVideosById(id)
    }

}