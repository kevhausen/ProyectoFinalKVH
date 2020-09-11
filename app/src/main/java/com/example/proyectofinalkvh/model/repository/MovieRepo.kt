package com.example.proyectofinalkvh.model.repository

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import com.example.proyectofinalkvh.model.dataclass.moviepopular.MoviePopular
import com.example.proyectofinalkvh.model.db.PopularMovieDB
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
    private val db=PopularMovieDB.getMovieDB(context)
    private val dao=db.daoPopularMovie()

    fun InsertWebDataToDB(){
        retrofit.moviePopular().enqueue(object : Callback<MoviePopular>{
            override fun onResponse(call: Call<MoviePopular>, response: Response<MoviePopular>) {
                response.body()?.let {
                    CoroutineScope(IO).launch {
                        dao.insertPopularMoviesInDB(it)
                    }
                }
            }

            override fun onFailure(call: Call<MoviePopular>, t: Throwable) {
                Toast.makeText(mContext,"Internet request failed",Toast.LENGTH_LONG).show()
            }
        })
    }

    fun getPopularMoviesFromDB():LiveData<MoviePopular>{
        return dao.getPopularMoviesFromDB()
    }

}