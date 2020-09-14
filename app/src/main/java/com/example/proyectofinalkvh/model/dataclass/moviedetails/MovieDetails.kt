package com.example.proyectofinalkvh.model.dataclass.moviedetails

import androidx.room.*

@Entity(tableName = "movie_details_table")
data class MovieDetails(
    val adult: Boolean?=false,
    val backdrop_path: String?=null,
    @Embedded val belongs_to_collection: BelongsToCollection?=null,
    val budget: Int?=0,
    @TypeConverters(GenreConverter::class) val genres: List<Genre?>?=null,
    val homepage: String?=null,
    @PrimaryKey val id: Int?=0,
    val imdb_id: String?=null,
    val original_language: String?=null,
    val original_title: String?=null,
    val overview: String?=null,
    val popularity: Double?=0.0,
    val poster_path: String?=null,
    @TypeConverters(ProductionCompanyConverter::class) val production_companies: List<ProductionCompany?>?=null,
    @TypeConverters(ProductionCountryConverter::class) val production_countries: List<ProductionCountry?>?=null,
    val release_date: String?=null,
    val revenue: Int?=0,
    val runtime: Int?=0,
    @TypeConverters(SpokenLanguageConverter::class) val spoken_languages: List<SpokenLanguage?>?=null,
    val status: String?=null,
    val tagline: String?=null,
    val title: String?=null,
    val video: Boolean?=false,
    val vote_average: Double?=0.0,
    val vote_count: Int?=0
)