package com.mpazpro3.app.ui.docente.informe

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.mpazpro3.app.data.database.AppDatabase
import com.mpazpro3.app.data.repository.EvaluacionRepository
import com.mpazpro3.app.databinding.ActivityInformeBinding
import com.mpazpro3.app.util.Notificador
import kotlinx.coroutines.launch

class InformeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityInformeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInformeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val database = AppDatabase.obtener(this)
        val repository = EvaluacionRepository(
            database.actividadDao(),
            database.respuestaDao(),
            database.evaluacionDao()
        )

        binding.btnGenerar.setOnClickListener {
            val estudianteId = binding.etEstudianteId.text.toString().toIntOrNull()
            if (estudianteId == null) {
                binding.tilEstudiante.error = "ID invalido"
                return@setOnClickListener
            }
            binding.tilEstudiante.isErrorEnabled = false

            lifecycleScope.launch {
                val informe = repository.obtenerInforme(estudianteId)
                binding.tvCorrectas.text = "Correctas: ${informe["correctas"]}"
                binding.tvTotal.text = "Total: ${informe["total"]}"
                binding.tvPorcentaje.text = "Porcentaje: ${informe["porcentaje"]}%"
                binding.cardInforme.visibility = View.VISIBLE

                Notificador.mostrarNotificacion(
                    this@InformeActivity,
                    "Informe generado",
                    "Estudiante $estudianteId: ${informe["porcentaje"]}%"
                )
            }
        }
    }
}
