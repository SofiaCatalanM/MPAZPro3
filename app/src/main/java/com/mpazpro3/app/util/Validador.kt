package com.mpazpro3.app.util

sealed class ResultadoValidacion {
    object Valido : ResultadoValidacion()
    data class Invalido(val mensaje: String) : ResultadoValidacion()
}

object Validador {

    fun validarNombre(nombre: String): ResultadoValidacion {
        return when {
            nombre.isBlank() -> ResultadoValidacion.Invalido("El nombre no puede estar vacio")
            nombre.length < 3 -> ResultadoValidacion.Invalido("El nombre debe tener al menos 3 caracteres")
            nombre.length > 50 -> ResultadoValidacion.Invalido("El nombre es muy largo")
            !nombre.all { it.isLetterOrDigit() || it == ' ' } ->
                ResultadoValidacion.Invalido("El nombre contiene caracteres invalidos")
            else -> ResultadoValidacion.Valido
        }
    }

    fun validarRespuesta(respuesta: String?): ResultadoValidacion {
        return when {
            respuesta == null -> ResultadoValidacion.Invalido("Debes seleccionar una alternativa")
            respuesta !in listOf("A", "B", "C", "D") ->
                ResultadoValidacion.Invalido("Respuesta no valida")
            else -> ResultadoValidacion.Valido
        }
    }

    fun validarCodigoObjetivo(codigo: String): ResultadoValidacion {
        return when {
            codigo.isBlank() -> ResultadoValidacion.Invalido("El codigo es obligatorio")
            !codigo.matches(Regex("^[A-Z]{2}[0-9]{2}$")) ->
                ResultadoValidacion.Invalido("Formato: 2 letras + 2 digitos (ej: OA01)")
            else -> ResultadoValidacion.Valido
        }
    }
}
