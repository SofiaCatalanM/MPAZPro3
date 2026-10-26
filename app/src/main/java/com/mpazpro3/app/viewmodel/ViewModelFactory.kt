package com.mpazpro3.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mpazpro3.app.data.repository.ContenidoRepository
import com.mpazpro3.app.data.repository.EvaluacionRepository
import com.mpazpro3.app.data.repository.UsuarioRepository

class ViewModelFactory(
    private val usuarioRepository: UsuarioRepository? = null,
    private val contenidoRepository: ContenidoRepository? = null,
    private val evaluacionRepository: EvaluacionRepository? = null
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(usuarioRepository!!) as T
            }
            modelClass.isAssignableFrom(ContenidoViewModel::class.java) -> {
                ContenidoViewModel(contenidoRepository!!) as T
            }
            modelClass.isAssignableFrom(EvaluacionViewModel::class.java) -> {
                EvaluacionViewModel(evaluacionRepository!!) as T
            }
            else -> throw IllegalArgumentException("ViewModel desconocido")
        }
    }
}
