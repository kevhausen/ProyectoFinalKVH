package com.example.proyectofinalkvh.model.dataclass.moviedetails

import androidx.room.TypeConverter
import com.google.gson.Gson

class GenreConverter {
    @TypeConverter
    fun listToJson(value: List<Genre>?): String {

        return Gson().toJson(value)
    }

    @TypeConverter
    fun jsonToList(value: String): List<Genre>? {

        val objects = Gson().fromJson(value, Array<Genre>::class.java) as Array<Genre>
        val list = objects.toList()
        return list
    }

}