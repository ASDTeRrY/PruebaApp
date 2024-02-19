package com.prueba.pruebaapp.ui.movies

import com.prueba.pruebaapp.domain.model.MovieModel

sealed class MoviesState<out T> {
    data object Loading: MoviesState<Nothing>()
    data class Error(val error: String): MoviesState<Nothing>()
    data class Success<T>(val movie: T): MoviesState<T>()
}