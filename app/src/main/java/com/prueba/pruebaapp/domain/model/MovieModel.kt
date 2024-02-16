package com.prueba.pruebaapp.domain.model

import com.google.gson.annotations.SerializedName
import com.prueba.pruebaapp.data.network.response.DetailResponse

data class MovieModel(
    val dates: String,
    val page: Int,
    val detailResponse: List<DetailModel>,
)

data class DetailModel(
    val posterPath: String,
    val title: String,
    val voteAverage: Int,
    val releaseDate: String,
    val overview: String
)