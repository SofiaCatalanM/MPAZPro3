package com.mpazpro3.app.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "actividades")
data class Actividad(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val pregunta: String,
    val alternativaA: String,
    val alternativaB: String,
    val alternativaC: String,
    val alternativaD: String,
    val respuestaCorrecta: String,
    val objetivoCodigo: String,
    val dificultad: String // "facil", "medio", "dificil"
)
