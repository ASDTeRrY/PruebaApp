package com.prueba.pruebaapp.data

import android.util.Log
import com.prueba.pruebaapp.data.db.LocalDataBase
import com.prueba.pruebaapp.data.db.entity.fromEntity
import com.prueba.pruebaapp.data.network.MovieApiService
import com.prueba.pruebaapp.domain.Repository
import com.prueba.pruebaapp.domain.model.DetailModel
import com.prueba.pruebaapp.domain.model.MovieModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

class RepositoryImpl @OptIn(ExperimentalCoroutinesApi::class)
@Inject constructor(private val apiService: MovieApiService, private val localDB: LocalDataBase): Repository {
    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun getMovies(page: String, apiKey: String): MovieModel? {
        runCatching { apiService.getMovies(page, apiKey) }
            .onSuccess {
                runCatching {
                    localDB.saveMovie(it.toDomainEntity())
                }.onSuccess {
                    Log.i("ASD.sLyon", "Se Guardo Correctamente en la BD")
                }.onFailure {
                    Log.i("ASD.sLyon", "Error al guarda en base de datos ${it.message}")
                }
                return it.toDomain()
            }
            .onFailure { Log.i("ASD.sLyon", "Error getMovies ${it.message}") }
        return null
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override suspend fun getDetail(id: Int): DetailModel? {
        runCatching {
            localDB.getMovie(id)
        }.onSuccess {
            Log.i("ASD.sLyon", "Se Recupero la pelicula ${it.title}")
            return  fromEntity(it)
        }.onFailure {
            Log.i("ASD.sLyon", "Error al recuperar la pelicula ${it.message}")
        }
        return null
    }
}