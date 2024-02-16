package com.prueba.pruebaapp.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.prueba.pruebaapp.domain.usecase.GetDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DetailViewModel  @Inject constructor(private val getDetailUseCase: GetDetailUseCase): ViewModel(){
    private var _state = MutableStateFlow<DetailState>(DetailState.Loading)
    val state: StateFlow<DetailState> = _state

    fun getDetail(id: Int){
        viewModelScope.launch {
            _state.value = DetailState.Loading
            val result = withContext(Dispatchers.IO){ getDetailUseCase(id) }
            if (result!= null){
                _state.value = DetailState.Success(result)
            }else{
                _state.value = DetailState.Error("Ha ocurrido un error intentelo mas tarde")
            }
        }

    }
}