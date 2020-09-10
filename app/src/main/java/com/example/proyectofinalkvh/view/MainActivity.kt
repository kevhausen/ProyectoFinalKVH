package com.example.proyectofinalkvh.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.proyectofinalkvh.R
import com.example.proyectofinalkvh.model.dataclass.moviepopular.MoviePopular
import com.example.proyectofinalkvh.model.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //PRUEBA PARA OBTENER DATOS DE API, HACIENDO LLAMDA A RETROFIT DESDE MAIN CAREPALO
        RetrofitClient.getRetrofitClientInstance().moviePopular().
        enqueue(object: retrofit2.Callback<MoviePopular>{

            override fun onResponse(call: Call<MoviePopular>, response: Response<MoviePopular>) {
                val list=response.body()
                Log.d("kevin onResponse", list?.results?.get(1).toString())
            }

            override fun onFailure(call: Call<MoviePopular>, t: Throwable) {
                Log.d("kevin onFail","no llega")
            }

        })

        //Log.d("kevin", lista.toString())
    }
}