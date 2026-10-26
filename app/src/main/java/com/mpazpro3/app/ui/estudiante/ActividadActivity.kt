package com.mpazpro3.app.ui.estudiante

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.RadioButton
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.mpazpro3.app.R
import com.mpazpro3.app.data.database.AppDatabase
import com.mpazpro3.app.data.repository.EvaluacionRepository
import com.mpazpro3.app.databinding.ActivityActividadBinding
import com.mpazpro3.app.util.Notificador
import com.mpazpro3.app.viewmodel.EvaluacionViewModel
import com.mpazpro3.app.viewmodel.ViewModelFactory

class ActividadActivity : AppCompatActivity() {

    private lateinit var binding: ActivityActividadBinding
    private lateinit var viewModel: EvaluacionViewModel
    private var estudianteId: Int = 0
    private var objetivoCodigo: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityActividadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        estudianteId = intent.getIntExtra("ESTUDIANTE_ID", 0)
        objetivoCodigo = intent.getStringExtra("OBJETIVO_CODIGO") ?: ""

        val database = AppDatabase.obtener(this)
        val repository = EvaluacionRepository(
            database.actividadDao(),
            database.respuestaDao(),
            database.evaluacionDao()
        )
        val factory = ViewModelFactory(evaluacionRepository = repository)
        viewModel = ViewModelProvider(this, factory)[EvaluacionViewModel::class.java]

        configurarObservadores()
        configurarListeners()
        viewModel.cargarActividades(objetivoCodigo)
    }

    private fun configurarObservadores() {
        viewModel.actividades.observe(this) { actividades ->
            if (actividades.isNotEmpty()) mostrarActividad(0)
        }

        viewModel.indiceActual.observe(this) { indice ->
            mostrarActividad(indice)
        }

        viewModel.progreso.observe(this) { progreso ->
            binding.progressBar.progress = (progreso * 100).toInt()
        }

        viewModel.errorRespuesta.observe(this) { error ->
            binding.tvError.text = error ?: ""
            binding.tvError.visibility = if (error != null) View.VISIBLE else View.GONE
        }

        viewModel.resultado.observe(this) { par ->
            par?.let { (correcto, mensaje) ->
                binding.tvResultado.text = mensaje
                binding.tvResultado.visibility = View.VISIBLE
                binding.tvResultado.setTextColor(
                    getColor(if (correcto) R.color.verde_exito else R.color.rojo_error)
                )
                if (correcto) {
                    Notificador.vibrar(this, 100)
                    val scale = AnimationUtils.loadAnimation(this, R.anim.scale_up)
                    binding.tvResultado.startAnimation(scale)
                }
            }
        }

        viewModel.evaluacionFinal.observe(this) { evaluacion ->
            evaluacion?.let {
                val intent = Intent(this, ResultadosActivity::class.java).apply {
                    putExtra("PUNTAJE", it.puntaje)
                    putExtra("TOTAL", it.total)
                    putExtra("INTENTO", it.intento)
                }
                startActivity(intent)
                finish()
            }
        }
    }

    private fun mostrarActividad(indice: Int) {
        val actividades = viewModel.actividades.value ?: return
        if (indice >= actividades.size) return
        val actividad = actividades[indice]
        binding.tvPregunta.text = actividad.pregunta
        binding.rbA.text = actividad.alternativaA
        binding.rbB.text = actividad.alternativaB
        binding.rbC.text = actividad.alternativaC
        binding.rbD.text = actividad.alternativaD
        binding.tvIndicador.text = "Pregunta ${indice + 1} de ${actividades.size}"

        val fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        binding.cardPregunta.startAnimation(fadeIn)
    }

    private fun configurarListeners() {
        val listener = android.widget.RadioGroup.OnCheckedChangeListener { _, checkedId ->
            val letra = when (checkedId) {
                R.id.rbA -> "A"
                R.id.rbB -> "B"
                R.id.rbC -> "C"
                R.id.rbD -> "D"
                else -> null
            }
            letra?.let { viewModel.seleccionarRespuesta(it) }
        }
        binding.rgAlternativas.setOnCheckedChangeListener(listener)

        binding.btnConfirmar.setOnClickListener {
            viewModel.confirmarRespuesta(estudianteId)
        }

        binding.btnSiguiente.setOnClickListener {
            viewModel.siguienteActividad()
        }

        binding.btnFinalizar.setOnClickListener {
            viewModel.finalizarEvaluacion(estudianteId, objetivoCodigo, maxIntentos = 3)
        }
    }
}
