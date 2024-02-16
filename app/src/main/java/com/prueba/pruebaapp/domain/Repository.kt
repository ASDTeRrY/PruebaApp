package com.prueba.pruebaapp.domain

import com.prueba.pruebaapp.domain.model.DetailModel
import com.prueba.pruebaapp.domain.model.MovieModel

interface Repository {
    suspend fun getMovies(page: String, apiKey: String): MovieModel?

    suspend fun getDetail(id: Int): DetailModel?
}