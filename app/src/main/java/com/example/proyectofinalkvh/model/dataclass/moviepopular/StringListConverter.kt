package com.example.proyectofinalkvh.model.dataclass.moviepopular

import androidx.room.TypeConverter

class StringListConverter {
    @TypeConverter
    fun fromString(stringListString: String): ArrayList<String> {
        return stringListString.split(",").map { it } as ArrayList<String>
    }

    @TypeConverter
    fun toString(stringList: ArrayList<String>): String {
        return stringList.joinToString(separator = ",")
    }


}