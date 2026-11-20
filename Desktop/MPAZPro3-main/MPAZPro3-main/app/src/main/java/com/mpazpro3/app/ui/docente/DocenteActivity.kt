package com.mpazpro3.app.ui.docente

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mpazpro3.app.databinding.ActivityDocenteBinding
import com.mpazpro3.app.ui.docente.informe.InformeActivity
import com.mpazpro3.app.ui.login.LoginActivity

class DocenteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDocenteBinding
    private var docenteNombre: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDocenteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        docenteNombre = intent.getStringExtra("USUARIO_NOMBRE") ?: "Docente"
        binding.tvBienvenidaDocente.text = "Hola, Prof. " + docenteNombre

        binding.btnVerEstudiantes.setOnClickListener {
            mostrarListaEstudiantes()
        }

        binding.btnAsignarObjetivo.setOnClickListener {
            val intent = Intent(this, AsignarObjetivoActivity::class.java)
            startActivity(intent)
        }

        binding.btnVerInformes.setOnClickListener {
            val intent = Intent(this, InformeActivity::class.java)
            startActivity(intent)
        }
    }

    @Deprecated("Se usa onBackPressed por compatibilidad con el flujo actual")
    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish()
    }

    private fun mostrarListaEstudiantes() {
        val estudiantes = listOf(
            "Juan Perez - Promedio: 85%",
            "Maria Gomez - Promedio: 92%",
            "Carlos Diaz - Promedio: 78%",
            "Ana Lopez - Promedio: 88%"
        )

        val builder = android.app.AlertDialog.Builder(this)
        builder.setTitle("Lista de Estudiantes y Rendimiento")
        builder.setItems(estudiantes.toTypedArray()) { dialog, which ->
            android.widget.Toast.makeText(this, "Viendo detalle de: ${estudiantes[which]}", android.widget.Toast.LENGTH_SHORT).show()
        }
        builder.setNeutralButton("Cerrar", null)
        builder.show()
    }
}