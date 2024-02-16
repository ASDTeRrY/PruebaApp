package com.prueba.pruebaapp.data

import android.util.Log
import com.prueba.pruebaapp.data.network.MovieApiService
import com.prueba.pruebaapp.domain.Repository
import com.prueba.pruebaapp.domain.model.MovieModel
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val apiService: MovieApiService): Repository {
    override suspend fun getMovies(page: String, apiKey: String): MovieModel? {
        runCatching { apiService.getMovies() }
            .onSuccess {return it.toDomain()}
            .onFailure { Log.i("ASD.sLyon", "Error getMovies ${it.message}") }
        return null
    }
}