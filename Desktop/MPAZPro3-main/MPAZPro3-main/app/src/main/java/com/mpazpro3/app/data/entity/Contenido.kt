package com.mpazpro3.app.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contenidos")
data class Contenido(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val titulo: String,
    val explicacion: String,
    val ejemplo: String,
    val objetivoCodigo: String,
    val aprobadoUtp: Boolean = false
)
