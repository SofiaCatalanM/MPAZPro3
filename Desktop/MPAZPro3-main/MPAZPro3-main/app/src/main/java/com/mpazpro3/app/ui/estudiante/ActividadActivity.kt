package com.mpazpro3.app.ui.estudiante

import android.content.Intent
import android.os.Bundle
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mpazpro3.app.databinding.ActivityActividadBinding

class ActividadActivity : AppCompatActivity() {

    private lateinit var binding: ActivityActividadBinding
    private var estudianteId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityActividadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        estudianteId = intent.getIntExtra("ESTUDIANTE_ID", 0)
        val objetivoCodigo = intent.getStringExtra("OBJETIVO_CODIGO") ?: ""

        binding.tvTituloActividad.text = "Evaluación: $objetivoCodigo"

        binding.btnEnviarRespuesta.setOnClickListener {
            val opcionCorrecta = binding.rbOpcion2.isChecked // París es la correcta en este ejemplo mock

            if (!binding.rbOpcion1.isChecked && !binding.rbOpcion2.isChecked && !binding.rbOpcion3.isChecked) {
                Toast.makeText(this, "Por favor selecciona una respuesta", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Simulamos un puntaje: 100 si acierta, 50 si falla (para probar la UI)
            val puntaje = if (opcionCorrecta) 100 else 50
            val total = 100
            val intento = 1

            val intent = Intent(this, ResultadosActivity::class.java).apply {
                putExtra("PUNTAJE", puntaje)
                putExtra("TOTAL", total)
                putExtra("INTENTO", intento)
            }
            startActivity(intent)
            finish() // Cierra esta actividad para no volver a ella con el botón "Atrás"
        }
    }
}