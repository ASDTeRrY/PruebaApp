package com.prueba.pruebaapp.data.db

import com.prueba.pruebaapp.data.db.entity.MovieEntity

interface LocalDataBaseImpl {
    suspend fun saveMovie(movieEntity: List<MovieEntity>)
    suspend fun getMovie(id: Int): MovieEntity
}