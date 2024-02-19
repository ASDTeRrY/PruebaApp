package com.prueba.pruebaapp.data.network

import com.prueba.pruebaapp.data.network.response.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiService {
    @GET("upcoming?")
    suspend fun getMovies(@Query("page") page: String, @Query("api_key") apiKey: String): MovieResponse
}