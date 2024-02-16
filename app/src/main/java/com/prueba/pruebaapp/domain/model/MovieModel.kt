package com.prueba.pruebaapp.domain.model

import com.google.gson.annotations.SerializedName
import com.prueba.pruebaapp.data.db.entity.MovieEntity
import com.prueba.pruebaapp.data.network.response.DetailResponse

data class MovieModel(
    val page: Int,
    val detailResponse: List<DetailModel>,
){
    companion object {
        fun fromEntity(entity: MovieEntity): DetailModel{
            return DetailModel(
                entity.id,
                entity.posterPath,
                entity.title,
                entity.voteAverage,
                entity.releaseDate,
                entity.overview

            )
        }
    }
}

data class DetailModel(
    val id: Int,
    val posterPath: String,
    val title: String,
    val voteAverage: Double,
    val releaseDate: String,
    val overview: String
)

