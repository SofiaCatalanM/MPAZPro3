package com.mpazpro3.app.network


data class PuntajeRequest(
    val estudianteId: Int,
    val nombre: String,
    val puntaje: Int,
    val asignatura: String
)

data class PuntajeResponse(
    val id: Int,
    val success: Boolean,
    val message: String
)