package com.prueba.pruebaapp.data.network.response

import com.google.gson.annotations.SerializedName
import com.prueba.pruebaapp.domain.model.DetailModel
import com.prueba.pruebaapp.domain.model.MovieModel

data class MovieResponse(
    @SerializedName("dates") val dates: String,
    @SerializedName("page") val page: Int,
    @SerializedName("results") val detailResponse: ArrayList<DetailResponse>,
){
    fun toDomain(): MovieModel{
        return MovieModel(
            dates = dates,
            page = page,
            detailResponse = detailResponse.toDataModelList()
        )
    }
}
data class DetailResponse(
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("title") val title: String,
    @SerializedName("vote_average") val voteAverage: Int,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("overview") val overview: String
)

fun DetailResponse.toDataModel(): DetailModel{
    return DetailModel(
        "https://image.tmdb.org/t/p/w500$posterPath",
        title,
        voteAverage,
        releaseDate,
        overview
    )
}

fun List<DetailResponse>.toDataModelList(): List<DetailModel>{
    return map { it.toDataModel() }
}