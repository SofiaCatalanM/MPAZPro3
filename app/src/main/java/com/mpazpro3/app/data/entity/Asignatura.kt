package com.mpazpro3.app.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "asignaturas")
data class Asignatura(
    @PrimaryKey
    val codigo: String,
    val nombre: String
)
