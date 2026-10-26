package com.mpazpro3.app.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mpazpro3.app.data.entity.Usuario
import com.mpazpro3.app.data.repository.UsuarioRepository
import com.mpazpro3.app.util.ResultadoValidacion
import com.mpazpro3.app.util.Validador
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: UsuarioRepository) : ViewModel() {

    private val _nombre = MutableLiveData("")
    val nombre: LiveData<String> = _nombre

    private val _rol = MutableLiveData("estudiante")
    val rol: LiveData<String> = _rol

    private val _errorNombre = MutableLiveData<String?>()
    val errorNombre: LiveData<String?> = _errorNombre

    private val _usuarioLogueado = MutableLiveData<Usuario?>()
    val usuarioLogueado: LiveData<Usuario?> = _usuarioLogueado

    private val _cargando = MutableLiveData(false)
    val cargando: LiveData<Boolean> = _cargando

    fun actualizarNombre(valor: String) {
        _nombre.value = valor
        _errorNombre.value = null
    }

    fun actualizarRol(valor: String) {
        _rol.value = valor
    }

    fun validarYLogin() {
        val nombreActual = _nombre.value ?: ""
        val validacion = Validador.validarNombre(nombreActual)

        when (validacion) {
            is ResultadoValidacion.Invalido -> {
                _errorNombre.value = validacion.mensaje
            }
            ResultadoValidacion.Valido -> {
                viewModelScope.launch {
                    _cargando.value = true
                    val resultado = repository.registrar(
                        nombre = nombreActual,
                        rol = _rol.value ?: "estudiante"
                    )
                    resultado.onSuccess { usuario ->
                        _usuarioLogueado.value = usuario
                    }.onFailure { error ->
                        _errorNombre.value = error.message
                    }
                    _cargando.value = false
                }
            }
        }
    }
}
