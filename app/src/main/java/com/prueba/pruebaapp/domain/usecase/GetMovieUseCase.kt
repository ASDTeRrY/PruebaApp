package com.prueba.pruebaapp.domain.usecase

import com.prueba.pruebaapp.domain.Repository
import javax.inject.Inject

class GetMovieUseCase @Inject constructor(private val repository: Repository){
    suspend operator fun invoke(page: String, apiKey: String) = repository.getMovies(page, apiKey)
}