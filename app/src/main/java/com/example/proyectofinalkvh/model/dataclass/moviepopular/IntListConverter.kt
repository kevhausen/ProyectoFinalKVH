package com.example.proyectofinalkvh.model.dataclass.moviepopular

import androidx.room.TypeConverter

class IntListConverter {
    @TypeConverter
    fun toString(list: List<Int>): String? {
        var genreIds = ""
        for (i in list) {
            genreIds += ",$i"
        }
        return genreIds
    }
}