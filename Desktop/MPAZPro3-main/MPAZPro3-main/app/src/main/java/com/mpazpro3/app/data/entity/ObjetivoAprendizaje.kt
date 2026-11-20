package com.mpazpro3.app.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "objetivos_aprendizaje")
data class ObjetivoAprendizaje(
    @PrimaryKey
    val codigo: String,
    val descripcion: String,
    val asignaturaCodigo: String,
    val unidad: Int,
    val ejeCurricular: String
)
