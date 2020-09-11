package com.example.proyectofinalkvh.model.dataclass.moviedetails

import androidx.room.TypeConverter
import com.google.gson.Gson

class ProductionCompanyConverter {
    @TypeConverter
    fun listToJson(value: List<ProductionCompany>?): String {

        return Gson().toJson(value)
    }

    @TypeConverter
    fun jsonToList(value: String): List<ProductionCompany>? {

        val objects = Gson().fromJson(value, Array<ProductionCompany>::class.java) as Array<ProductionCompany>
        val list = objects.toList()
        return list
    }
}