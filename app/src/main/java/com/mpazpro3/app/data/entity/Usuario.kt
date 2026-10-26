package com.mpazpro3.app.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usuarios")
data class Usuario(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nombre: String,
    val rol: String, // "estudiante", "docente", "utp"
    val curso: String? = null
)
