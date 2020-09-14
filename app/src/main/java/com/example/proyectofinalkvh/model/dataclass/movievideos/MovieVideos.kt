package com.example.proyectofinalkvh.model.dataclass.movievideos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.proyectofinalkvh.model.dataclass.moviepopular.ResultConverter

@Entity(tableName = "movie_videos_table")
data class MovieVideos(
    @PrimaryKey val id: Int?,
    @ColumnInfo(name = "results_video")@TypeConverters(VideoResultConverter::class) val results: List<Result?>?
)