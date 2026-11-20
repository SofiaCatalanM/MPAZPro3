package com.mpazpro3.app.util

import org.junit.Assert.assertTrue
import org.junit.Test

class ValidadorTest {

    @Test
    fun nombre_valido_debe_retornar_Valido() {
        val nombre = "Sofia"
        val resultado = Validador.validarNombre(nombre)
        assertTrue("Se esperaba un resultado Valido", resultado is ResultadoValidacion.Valido)
    }

    @Test
    fun nombre_muy_corto_debe_retornar_Invalido() {
        val nombre = "So"
        val resultado = Validador.validarNombre(nombre)
        assertTrue("Se esperaba un resultado Invalido", resultado is ResultadoValidacion.Invalido)
    }

    @Test
    fun nombre_vacio_debe_retornar_Invalido() {
        val nombre = ""
        val resultado = Validador.validarNombre(nombre)
        assertTrue("Se esperaba un resultado Invalido para nombre vacio", resultado is ResultadoValidacion.Invalido)
    }
}