package com.mpazpro3.app.ui.estudiante

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.mpazpro3.app.R
import com.mpazpro3.app.databinding.ActivityResultadosBinding
import com.mpazpro3.app.ui.estudiante.EstudianteActivity

class ResultadosActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultadosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultadosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val puntaje = intent.getIntExtra("PUNTAJE", 0)
        val total = intent.getIntExtra("TOTAL", 0)
        val intento = intent.getIntExtra("INTENTO", 1)

        val porcentaje = if (total > 0) (puntaje * 100) / total else 0

        binding.tvPuntaje.text = "$puntaje / $total"
        binding.tvPorcentaje.text = "$porcentaje%"
        binding.tvIntento.text = "Intento $intento"

        val mensaje = when {
            porcentaje >= 80 -> "Excelente trabajo!"
            porcentaje >= 60 -> "Buen desempeno, sigue practicando."
            porcentaje >= 40 -> "Puedes mejorar, repasa los contenidos."
            else -> "Te recomendamos revisar el material nuevamente."
        }
        binding.tvMensaje.text = mensaje

        val anim = AnimationUtils.loadAnimation(this, R.anim.scale_up)
        binding.tvPorcentaje.startAnimation(anim)

        binding.btnVolver.setOnClickListener {
            startActivity(Intent(this, EstudianteActivity::class.java))
            finish()
        }
    }
}
