package com.example.proyectofinalkvh.model.dataclass.movievideos

import androidx.room.TypeConverter
import com.google.gson.Gson

class VideoResultConverter {
    @TypeConverter
    fun listToJson(value: List<Result>?): String {

        return Gson().toJson(value)
    }

    @TypeConverter
    fun jsonToList(value: String): List<Result>? {

        val objects = Gson().fromJson(value, Array<Result>::class.java) as Array<Result>
        val list = objects.toList()
        return list
    }
}