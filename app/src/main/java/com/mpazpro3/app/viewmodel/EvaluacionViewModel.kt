package com.mpazpro3.app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mpazpro3.app.data.entity.Actividad
import com.mpazpro3.app.data.entity.Evaluacion
import com.mpazpro3.app.data.repository.EvaluacionRepository
import com.mpazpro3.app.util.ResultadoValidacion
import com.mpazpro3.app.util.Validador
import kotlinx.coroutines.launch

class EvaluacionViewModel(private val repository: EvaluacionRepository) : ViewModel() {

    private val _actividades = MutableLiveData<List<Actividad>>()
    val actividades: LiveData<List<Actividad>> = _actividades

    private val _indiceActual = MutableLiveData(0)
    val indiceActual: LiveData<Int> = _indiceActual

    private val _respuestaSeleccionada = MutableLiveData<String?>()
    val respuestaSeleccionada: LiveData<String?> = _respuestaSeleccionada

    private val _errorRespuesta = MutableLiveData<String?>()
    val errorRespuesta: LiveData<String?> = _errorRespuesta

    private val _resultado = MutableLiveData<Pair<Boolean, String>?>()
    val resultado: LiveData<Pair<Boolean, String>?> = _resultado

    private val _evaluacionFinal = MutableLiveData<Evaluacion?>()
    val evaluacionFinal: LiveData<Evaluacion?> = _evaluacionFinal

    private val _progreso = MutableLiveData(0f)
    val progreso: LiveData<Float> = _progreso

    fun cargarActividades(objetivoCodigo: String) {
        viewModelScope.launch {
            _actividades.value = repository.obtenerActividades(objetivoCodigo)
            actualizarProgreso()
        }
    }

    fun seleccionarRespuesta(respuesta: String) {
        _respuestaSeleccionada.value = respuesta
        _errorRespuesta.value = null
    }

    fun confirmarRespuesta(estudianteId: Int) {
        val seleccion = _respuestaSeleccionada.value
        val validacion = Validador.validarRespuesta(seleccion)

        when (validacion) {
            is ResultadoValidacion.Invalido -> {
                _errorRespuesta.value = validacion.mensaje
            }
            ResultadoValidacion.Valido -> {
                val actividades = _actividades.value ?: return
                val indice = _indiceActual.value ?: 0
                val actividad = actividades[indice]

                viewModelScope.launch {
                    val resultadoRespuesta = repository.registrarRespuesta(
                        estudianteId = estudianteId,
                        actividadId = actividad.id,
                        respuesta = seleccion!!
                    )
                    resultadoRespuesta.onSuccess { esCorrecta ->
                        _resultado.value = Pair(
                            esCorrecta,
                            if (esCorrecta) "Correcto!" else "Incorrecto. La respuesta era ${actividad.respuestaCorrecta}"
                        )
                    }
                }
            }
        }
    }

    fun siguienteActividad() {
        val indice = _indiceActual.value ?: 0
        val total = _actividades.value?.size ?: 0
        if (indice < total - 1) {
            _indiceActual.value = indice + 1
            _respuestaSeleccionada.value = null
            _resultado.value = null
            actualizarProgreso()
        }
    }

    fun finalizarEvaluacion(estudianteId: Int, objetivoCodigo: String, maxIntentos: Int) {
        viewModelScope.launch {
            val resultado = repository.finalizarEvaluacion(estudianteId, objetivoCodigo, maxIntentos)
            resultado.onSuccess { evaluacion ->
                _evaluacionFinal.value = evaluacion
            }.onFailure { error ->
                _errorRespuesta.value = error.message
            }
        }
    }

    private fun actualizarProgreso() {
        val indice = (_indiceActual.value ?: 0) + 1
        val total = _actividades.value?.size ?: 1
        _progreso.value = indice.toFloat() / total.toFloat()
    }
}
