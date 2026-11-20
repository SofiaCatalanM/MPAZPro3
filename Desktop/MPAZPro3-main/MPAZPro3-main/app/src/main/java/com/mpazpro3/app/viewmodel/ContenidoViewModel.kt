package com.mpazpro3.app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mpazpro3.app.data.entity.Contenido
import com.mpazpro3.app.data.entity.ObjetivoAprendizaje
import com.mpazpro3.app.data.repository.ContenidoRepository
import kotlinx.coroutines.launch

class ContenidoViewModel(private val repository: ContenidoRepository) : ViewModel() {

    private val _objetivos = MutableLiveData<List<ObjetivoAprendizaje>>()
    val objetivos: LiveData<List<ObjetivoAprendizaje>> = _objetivos

    private val _contenidos = MutableLiveData<List<Contenido>>()
    val contenidos: LiveData<List<Contenido>> = _contenidos

    private val _cargando = MutableLiveData(false)
    val cargando: LiveData<Boolean> = _cargando

    fun cargarObjetivos(asignatura: String) {
        viewModelScope.launch {
            _cargando.value = true
            _objetivos.value = repository.obtenerObjetivosPorAsignatura(asignatura)
            _cargando.value = false
        }
    }

    fun cargarContenidos(objetivoCodigo: String) {
        viewModelScope.launch {
            _cargando.value = true
            _contenidos.value = repository.obtenerContenidosPorObjetivo(objetivoCodigo)
            _cargando.value = false
        }
    }
}
