package com.example.proyectofinalkvh.model.dataclass.moviedetails

import androidx.room.*

@Entity(tableName = "movie_details_table")
data class MovieDetails(
    val adult: Boolean?,
    val backdrop_path: String?,
    //@Embedded val belongs_to_collection: BelongsToCollection?,
    val budget: Int?,
    //@TypeConverters(GenreConverter::class) val genres: List<Genre?>?,
    val homepage: String?,
    @PrimaryKey val id: Int?,
    val imdb_id: String?,
    val original_language: String?,
    val original_title: String?,
    val overview: String?,
    val popularity: Double?,
    val poster_path: String?,
    //@TypeConverters(ProductionCompanyConverter::class) val production_companies: List<ProductionCompany?>?,
    //@TypeConverters(ProductionCountryConverter::class) val production_countries: List<ProductionCountry?>?,
    val release_date: String?,
    val revenue: Int?,
    val runtime: Int?,
    //@TypeConverters(SpokenLanguageConverter::class) val spoken_languages: List<SpokenLanguage?>?,
    val status: String?,
    val tagline: String?,
    val title: String?,
    val video: Boolean?,
    val vote_average: Double?,
    val vote_count: Int?
)