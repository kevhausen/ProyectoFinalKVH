package com.example.proyectofinalkvh.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.proyectofinalkvh.R
import com.example.proyectofinalkvh.model.dataclass.moviepopular.MoviePopular
import com.example.proyectofinalkvh.model.retrofit.IMAGE_BASE_URL
import com.example.proyectofinalkvh.model.retrofit.RetrofitClient
import com.example.proyectofinalkvh.viewmodel.MovieVM
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class MainActivity : AppCompatActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //PRUEBA PARA OBTENER DATOS DE API, HACIENDO LLAMDA A RETROFIT DESDE MAIN CAREPALO
        /*RetrofitClient.getRetrofitClientInstance().moviePopular().
        enqueue(object: retrofit2.Callback<MoviePopular>{

            override fun onResponse(call: Call<MoviePopular>, response: Response<MoviePopular>) {
                val list=response.body()
                Log.d("kevin onResponse", list?.results?.size.toString())
            }

            override fun onFailure(call: Call<MoviePopular>, t: Throwable) {
                Log.d("kevin onFail","no llega")
            }

        })*/
            //PRUEBA PARA OBTENER DATOS DE DB, HACIENDO LLAMADA A VIEWMODEL
            val movieVM=ViewModelProvider(this).get(MovieVM::class.java)
            movieVM.getPopularMovies().observe(this,{
                Log.d("kevin VM",it.toString())

            })

    }
}