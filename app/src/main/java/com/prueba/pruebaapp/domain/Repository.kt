package com.prueba.pruebaapp.domain

import com.prueba.pruebaapp.domain.model.MovieModel

interface Repository {
    suspend fun getMovies(page: String, apiKey: String): MovieModel?
}