package com.example.proyectofinalkvh.model.dataclass.moviedetails

import androidx.room.ColumnInfo

data class BelongsToCollection(
    @ColumnInfo(name="backdrop_path_BTC")
    val backdrop_path: String?,
    @ColumnInfo(name="id_BTC")
    val id: Int?,
    val name: String?,
    @ColumnInfo(name="poster_path_BTC")
    val poster_path: String?
)