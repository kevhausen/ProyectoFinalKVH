package com.example.proyectofinalkvh.model.dataclass.moviedetails

import androidx.room.TypeConverter
import com.google.gson.Gson

class SpokenLanguageConverter {
    @TypeConverter
    fun listToJson(value: List<SpokenLanguage>?): String {

        return Gson().toJson(value)
    }

    @TypeConverter
    fun jsonToList(value: String): List<SpokenLanguage>? {

        val objects = Gson().fromJson(value, Array<SpokenLanguage>::class.java) as Array<SpokenLanguage>
        val list = objects.toList()
        return list
    }
}