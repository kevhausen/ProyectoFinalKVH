package com.example.proyectofinalkvh.model.dataclass.moviefavorite

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_favorite_table")
data class MovieFavorite(
                         val backdrop_path: String?,
                         @PrimaryKey val id: Int?,
                         val original_language: String?,
                         val original_title: String?,
                         val overview: String?,
                         val popularity: Double?,
                         val poster_path: String?,
                         val release_date: String?,
                         val title: String?,
                         val vote_count: Int?) {
}