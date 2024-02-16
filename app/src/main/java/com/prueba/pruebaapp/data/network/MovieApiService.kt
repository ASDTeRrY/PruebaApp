package com.prueba.pruebaapp.data.network

import com.prueba.pruebaapp.data.network.response.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApiService {
    @GET("?page={page}&api_key={api_key}")
    suspend fun getMovies(@Path("page") page: String, @Path("api_key") apiKey: String): MovieResponse
}