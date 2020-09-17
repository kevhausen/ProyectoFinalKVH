package com.example.proyectofinalkvh.model.dataclass.moviepopular

import androidx.room.TypeConverter
import com.google.gson.Gson

import com.google.gson.reflect.TypeToken




class IntListConverter {
    @TypeConverter
    fun listToJson(value: List<Int>?): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun jsonToList(value: String): List<Int>? {
        val objects = Gson().fromJson(value, Array<Int>::class.java) as Array<Int>
        val list = objects.toList()
        return list
    }

}