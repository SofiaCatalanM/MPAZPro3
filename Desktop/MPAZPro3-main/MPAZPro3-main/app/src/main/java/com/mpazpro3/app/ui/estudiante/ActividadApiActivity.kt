package com.mpazpro3.app.ui.estudiante

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mpazpro3.app.databinding.ActivityActividadApiBinding
import com.mpazpro3.app.network.PuntajeRequest
import com.mpazpro3.app.network.RetrofitClient
import com.mpazpro3.app.network.TriviaResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ActividadApiActivity : AppCompatActivity() {

    private lateinit var binding: ActivityActividadApiBinding
    private var estudianteId: Int = 0
    private var estudianteNombre: String = "Estudiante"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityActividadApiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        estudianteId = intent.getIntExtra("ESTUDIANTE_ID", 0)
        estudianteNombre = intent.getStringExtra("USUARIO_NOMBRE") ?: "Estudiante"

        binding.btnTraerPregunta.setOnClickListener {
            traerPreguntaYGuardarPuntaje()
        }
    }

    private fun traerPreguntaYGuardarPuntaje() {
        binding.progressBar.visibility = View.VISIBLE
        binding.tvPregunta.text = "Conectando con API y Microservicio..."

        // 1. Obtener pregunta de la API Externa
        RetrofitClient.triviaApi.getTriviaQuestions(amount = 1).enqueue(object : Callback<TriviaResponse> {
            override fun onResponse(call: Call<TriviaResponse>, response: Response<TriviaResponse>) {
                if (response.isSuccessful && response.body() != null && response.body()!!.results.isNotEmpty()) {


                    val preguntaCruda = response.body()!!.results[0].question
                    val pregunta = android.text.Html.fromHtml(preguntaCruda, android.text.Html.FROM_HTML_MODE_LEGACY).toString()

                    binding.tvPregunta.text = "Pregunta: $pregunta\n\n(Enviando puntaje al microservicio...)"

                    // 2. Enviar puntaje al Microservicio
                    enviarPuntajeAlMicroservicio()
                } else {
                    binding.progressBar.visibility = View.GONE
                    binding.tvPregunta.text = "Error al obtener la pregunta"
                }
            }

            override fun onFailure(call: Call<TriviaResponse>, t: Throwable) {
                binding.progressBar.visibility = View.GONE
                binding.tvPregunta.text = "Error de conexion: ${t.message}"
            }
        })
    }

    private fun enviarPuntajeAlMicroservicio() {
        val puntajeRequest = PuntajeRequest(
            estudianteId = estudianteId,
            nombre = estudianteNombre,
            puntaje = 100, // Puntaje simulado
            asignatura = "CIENCIAS"
        )

        RetrofitClient.microservicioApi.guardarPuntaje(puntajeRequest).enqueue(object : Callback<com.mpazpro3.app.network.PuntajeResponse> {
            override fun onResponse(call: Call<com.mpazpro3.app.network.PuntajeResponse>, response: Response<com.mpazpro3.app.network.PuntajeResponse>) {
                binding.progressBar.visibility = View.GONE
                if (response.isSuccessful) {
                    binding.tvPregunta.text = binding.tvPregunta.text.toString() + "\n\n[OK] Puntaje guardado en el microservicio correctamente."
                    Toast.makeText(this@ActividadApiActivity, "Datos sincronizados con el backend", Toast.LENGTH_LONG).show()
                } else {
                    binding.tvPregunta.text = binding.tvPregunta.text.toString() + "\n\n[Error] Fallo al guardar en el microservicio."
                }
            }

            override fun onFailure(call: Call<com.mpazpro3.app.network.PuntajeResponse>, t: Throwable) {
                binding.progressBar.visibility = View.GONE
                binding.tvPregunta.text = binding.tvPregunta.text.toString() + "\n\n[Error de Red] No se pudo conectar al microservicio: ${t.message}"
            }
        })
    }
}