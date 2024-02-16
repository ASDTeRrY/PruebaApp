package com.prueba.pruebaapp.domain.usecase

import com.prueba.pruebaapp.domain.Repository
import javax.inject.Inject

class GetDetailUseCase  @Inject constructor(private val repository: Repository){
    suspend operator fun invoke(id: Int) = repository.getDetail(id)
}