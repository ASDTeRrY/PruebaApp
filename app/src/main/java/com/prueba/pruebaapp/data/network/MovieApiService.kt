package com.prueba.pruebaapp.data.network

import com.prueba.pruebaapp.data.network.response.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApiService {
    @GET("upcoming?page=1&api_key=f46b58478f489737ad5a4651a4b25079")
    suspend fun getMovies(): MovieResponse
}