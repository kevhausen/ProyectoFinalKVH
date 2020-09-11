package com.example.proyectofinalkvh.model.dataclass.moviedetails

import androidx.room.TypeConverter
import com.google.gson.Gson

class ProductionCountryConverter {
    @TypeConverter
    fun listToJson(value: List<ProductionCountry>?): String {

        return Gson().toJson(value)
    }

    @TypeConverter
    fun jsonToList(value: String): List<ProductionCountry>? {

        val objects = Gson().fromJson(value, Array<ProductionCountry>::class.java) as Array<ProductionCountry>
        val list = objects.toList()
        return list
    }
}