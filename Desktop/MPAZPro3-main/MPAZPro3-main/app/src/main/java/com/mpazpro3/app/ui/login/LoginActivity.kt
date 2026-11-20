package com.mpazpro3.app.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.mpazpro3.app.R
import com.mpazpro3.app.data.database.AppDatabase
import com.mpazpro3.app.data.repository.DataInitializer
import com.mpazpro3.app.databinding.ActivityLoginBinding
import com.mpazpro3.app.ui.docente.DocenteActivity
import com.mpazpro3.app.ui.estudiante.EstudianteActivity
import com.mpazpro3.app.util.ResultadoValidacion
import com.mpazpro3.app.util.UtilVibracion
import com.mpazpro3.app.util.Validador
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var database: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = AppDatabase.obtener(this)

        // Inicializar datos de ejemplo en segundo plano
        lifecycleScope.launch {
            val initializer = DataInitializer(database)
            initializer.initializeData()
        }

        binding.btnIngresar.setOnClickListener {
            val nombre = binding.etNombre.text.toString().trim()

            // 1. Validación centralizada (Devuelve ResultadoValidacion)
            val resultado = Validador.validarNombre(nombre)

            // 2. Manejamos el resultado con 'when' (Smart Cast de Kotlin)
            when (resultado) {
                is ResultadoValidacion.Invalido -> {
                    // Retroalimentación visual y animación de error
                    // Kotlin sabe que aquí 'resultado' es Invalido, por eso podemos usar .mensaje
                    binding.tilNombre.error = resultado.mensaje

                    val shake = AnimationUtils.loadAnimation(this, R.anim.shake)
                    binding.tilNombre.startAnimation(shake)
                }
                ResultadoValidacion.Valido -> {
                    // 3. Éxito: limpiamos error y usamos recurso nativo
                    binding.tilNombre.isErrorEnabled = false
                    UtilVibracion.vibrarExito(this)

                    val esEstudiante = binding.rbEstudiante.isChecked
                    val destino = if (esEstudiante) {
                        EstudianteActivity::class.java
                    } else {
                        DocenteActivity::class.java
                    }

                    val intent = Intent(this@LoginActivity, destino)
                    intent.putExtra("USUARIO_NOMBRE", nombre)
                    intent.putExtra("USUARIO_ID", 1)
                    startActivity(intent)
                }
            }
        }
    }
}