package com.prueba.pruebaapp.ui.detail

import com.prueba.pruebaapp.domain.model.DetailModel

sealed class DetailState {
    data object Loading: DetailState()
    data class Error(val error: String): DetailState()
    data class Success(val movie: DetailModel): DetailState()
}