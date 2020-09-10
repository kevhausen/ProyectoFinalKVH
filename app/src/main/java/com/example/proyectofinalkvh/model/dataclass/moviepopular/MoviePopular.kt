package com.example.proyectofinalkvh.model.dataclass.moviepopular

data class MoviePopular(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)