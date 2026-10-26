package com.mpazpro3.app.ui.docente

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.mpazpro3.app.R

class DocenteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val nombre = intent.getStringExtra("USUARIO_NOMBRE") ?: "Docente"

        // Creamos la vista por código para evitar errores de XML faltantes y ahorrar tiempo
        val textView = TextView(this).apply {
            text = "👨‍🏫 Panel de Docente\n\nBienvenido/a: $nombre\n\n(El flujo completo del docente se implementará en la Evaluación Final Transversal)"
            textSize = 20f
            setPadding(60, 60, 60, 60)
            setTextColor(ContextCompat.getColor(context, R.color.negro))
        }

        setContentView(textView)
    }
}