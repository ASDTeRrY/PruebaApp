package com.prueba.pruebaapp.ui.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prueba.pruebaapp.domain.usecase.GetMovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val getMovieUseCase: GetMovieUseCase) :ViewModel(){

    private var _state = MutableStateFlow<MoviesState>(MoviesState.Loading)
    val state: StateFlow<MoviesState> = _state

    fun getMovie(page: String, apiKey: String){
        viewModelScope.launch {
            _state.value = MoviesState.Loading
            val result = withContext(Dispatchers.IO){ getMovieUseCase(page, apiKey) }
            if (result!= null){
                _state.value = MoviesState.Success(result)
            }else{
                _state.value = MoviesState.Error("Ha ocurrido un error intentelo mas tarde")
            }
        }
    }

}