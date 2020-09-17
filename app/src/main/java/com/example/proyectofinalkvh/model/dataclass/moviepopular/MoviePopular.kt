package com.example.proyectofinalkvh.model.dataclass.moviepopular

import androidx.room.*



data class MoviePopular(
    val page: Int?=0,
    val results: List<Result>?=null,
    val total_pages: Int?=0,
    val total_results: Int?=0
)