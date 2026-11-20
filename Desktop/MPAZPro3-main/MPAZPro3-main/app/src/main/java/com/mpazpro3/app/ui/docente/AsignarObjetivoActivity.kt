package com.mpazpro3.app.ui.docente

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.mpazpro3.app.data.database.AppDatabase
import com.mpazpro3.app.data.repository.ContenidoRepository
import com.mpazpro3.app.databinding.ActivityAsignarObjetivoBinding
import com.mpazpro3.app.util.ResultadoValidacion
import com.mpazpro3.app.util.Validador
import com.mpazpro3.app.viewmodel.ContenidoViewModel
import com.mpazpro3.app.viewmodel.ViewModelFactory

class AsignarObjetivoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAsignarObjetivoBinding
    private lateinit var viewModel: ContenidoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAsignarObjetivoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val database = AppDatabase.obtener(this)
        val repository = ContenidoRepository(database.contenidoDao(), database.objetivoDao())
        val factory = ViewModelFactory(contenidoRepository = repository)
        viewModel = ViewModelProvider(this, factory)[ContenidoViewModel::class.java]

        binding.btnGuardar.setOnClickListener { guardarContenido() }
    }

    private fun guardarContenido() {
        val codigo = binding.etCodigoObjetivo.text.toString()
        val validacion = Validador.validarCodigoObjetivo(codigo)

        when (validacion) {
            is ResultadoValidacion.Invalido -> {
                binding.tilCodigo.error = validacion.mensaje
                binding.tilCodigo.isErrorEnabled = true
            }
            ResultadoValidacion.Valido -> {
                binding.tilCodigo.isErrorEnabled = false
                android.widget.Toast.makeText(this, "Contenido guardado", android.widget.Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }
}
