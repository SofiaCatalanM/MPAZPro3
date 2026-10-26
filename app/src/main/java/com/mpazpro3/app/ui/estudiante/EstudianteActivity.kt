package com.mpazpro3.app.ui.estudiante

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.mpazpro3.app.R
import com.mpazpro3.app.databinding.ActivityEstudianteBinding
import com.mpazpro3.app.util.UtilVibracion

class EstudianteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEstudianteBinding
    private var estudianteId: Int = 0
    private var estudianteNombre: String = ""
    private val CAMERA_REQUEST_CODE = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEstudianteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        estudianteId = intent.getIntExtra("USUARIO_ID", 0)
        estudianteNombre = intent.getStringExtra("USUARIO_NOMBRE") ?: "Estudiante"
        binding.tvBienvenida.text = "¡Hola, $estudianteNombre! 👋"

        // Vibración al entrar (Recurso Nativo 1)
        UtilVibracion.vibrarExito(this)

        configurarNavegacion()
        configurarCamara()
        aplicarAnimacionesEntrada()
    }

    private fun configurarNavegacion() {
        binding.btnLenguaje.setOnClickListener { navegarAContenido("LENGUAJE") }
        binding.btnMatematica.setOnClickListener { navegarAContenido("MATEMATICA") }
        binding.btnCiencias.setOnClickListener { navegarAContenido("CIENCIAS") }
        binding.btnHistoria.setOnClickListener { navegarAContenido("HISTORIA") }
    }

    private fun configurarCamara() {
        binding.btnCamara.setOnClickListener {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), CAMERA_REQUEST_CODE)
            } else {
                abrirCamara()
            }
        }
    }

    private fun abrirCamara() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (intent.resolveActivity(packageManager) != null) {
            startActivityForResult(intent, CAMERA_REQUEST_CODE)
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK) {
            val imagen = data?.extras?.get("data") as? Bitmap
            Toast.makeText(this, "Foto capturada correctamente (Recurso Nativo)", Toast.LENGTH_LONG).show()
        }
    }

    private fun navegarAContenido(asignatura: String) {
        val intent = Intent(this, ContenidoActivity::class.java).apply {
            putExtra("ASIGNATURA", asignatura)
            putExtra("ESTUDIANTE_ID", estudianteId)
        }
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }

    private fun aplicarAnimacionesEntrada() {
        val fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        listOf(binding.btnLenguaje, binding.btnMatematica, binding.btnCiencias, binding.btnHistoria)
            .forEachIndexed { index, btn ->
                btn.startAnimation(fadeIn.apply { startOffset = (index * 150).toLong() })
            }
    }
}