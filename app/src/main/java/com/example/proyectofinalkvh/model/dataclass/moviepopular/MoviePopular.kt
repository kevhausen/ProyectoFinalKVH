package com.example.proyectofinalkvh.model.dataclass.moviepopular

import androidx.room.*


@Entity(tableName = "movie_popular_table")
data class MoviePopular(
    @PrimaryKey val page: Int?=0,
    @TypeConverters(ResultConverter::class) val results: List<Result?>?=null,
    val total_pages: Int?=0,
    val total_results: Int?=0
)