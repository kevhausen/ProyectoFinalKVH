package com.example.proyectofinalkvh.model.dataclass.moviepopular

import androidx.room.*


@Entity(tableName = "movie_popular_table")
data class MoviePopular(
    @PrimaryKey val page: Int,
    val results: List<Result>?,
    val total_pages: Int,
    val total_results: Int
)