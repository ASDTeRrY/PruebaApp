package com.prueba.pruebaapp.ui.movies

import com.prueba.pruebaapp.domain.model.MovieModel

sealed class MoviesState {
    data object Loading: MoviesState()
    data class Error(val error: String): MoviesState()
    data class Success(val movie: MovieModel): MoviesState()
}