package com.example.proyectofinalkvh.model.retrofit

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val API_KEY="0b58f5bbe43507bdf4cb53f4b646e940"
const val BASE_URL="https://api.themoviedb.org/3/"

//object es parecido a companion object
object RetrofitClient {

    fun getRetrofitClientInstance():MovieAPI{
        //interceptor programacion fncional
        val apiInterceptor = Interceptor{chain ->

            val apiUrl=chain.request().url().newBuilder()
                .addQueryParameter("api_key", API_KEY).build()
            val mRequest =chain.request().newBuilder()
                .url(apiUrl).build()

            return@Interceptor chain.proceed(mRequest)
        }

        val mClient=OkHttpClient.Builder().addInterceptor(apiInterceptor)
            .connectTimeout(60,TimeUnit.SECONDS).build()

        return Retrofit.Builder().client(mClient).baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build().create(MovieAPI::class.java)
    }

}