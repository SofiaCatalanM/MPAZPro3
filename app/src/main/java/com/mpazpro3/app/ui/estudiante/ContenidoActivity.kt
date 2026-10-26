package com.mpazpro3.app.ui.estudiante

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mpazpro3.app.R
import com.mpazpro3.app.data.database.AppDatabase
import com.mpazpro3.app.data.entity.Contenido
import com.mpazpro3.app.data.entity.ObjetivoAprendizaje
import com.mpazpro3.app.data.repository.ContenidoRepository
import com.mpazpro3.app.databinding.ActivityContenidoBinding
import com.mpazpro3.app.viewmodel.ContenidoViewModel
import com.mpazpro3.app.viewmodel.ViewModelFactory

class ContenidoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityContenidoBinding
    private lateinit var viewModel: ContenidoViewModel
    private var asignatura: String = ""
    private var estudianteId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContenidoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        asignatura = intent.getStringExtra("ASIGNATURA") ?: ""
        estudianteId = intent.getIntExtra("ESTUDIANTE_ID", 0)

        val database = AppDatabase.obtener(this)
        val repository = ContenidoRepository(database.contenidoDao(), database.objetivoDao())
        val factory = ViewModelFactory(contenidoRepository = repository)
        viewModel = ViewModelProvider(this, factory)[ContenidoViewModel::class.java]

        binding.tvTitulo.text = asignatura
        binding.rvObjetivos.layoutManager = LinearLayoutManager(this)

        configurarObservadores()
        viewModel.cargarObjetivos(asignatura)
    }

    private fun configurarObservadores() {
        viewModel.objetivos.observe(this) { objetivos ->
            binding.rvObjetivos.adapter = ObjetivoAdapter(objetivos) { objetivo ->
                viewModel.cargarContenidos(objetivo.codigo)
            }
            binding.tvEmpty.visibility = if (objetivos.isEmpty()) View.VISIBLE else View.GONE
        }

        viewModel.contenidos.observe(this) { contenidos ->
            mostrarContenidos(contenidos)
        }

        viewModel.cargando.observe(this) { cargando ->
            binding.progressBar.visibility = if (cargando) View.VISIBLE else View.GONE
        }
    }

    private fun mostrarContenidos(contenidos: List<Contenido>) {
        val dialog = android.app.AlertDialog.Builder(this)
            .setTitle("Contenidos")
            .setItems(contenidos.map { it.titulo }.toTypedArray()) { _, index ->
                val contenido = contenidos[index]
                val intent = Intent(this, ActividadActivity::class.java).apply {
                    putExtra("OBJETIVO_CODIGO", contenido.objetivoCodigo)
                    putExtra("ESTUDIANTE_ID", estudianteId)
                }
                startActivity(intent)
            }
            .setNegativeButton("Cerrar", null)
            .create()
        dialog.show()
    }

    inner class ObjetivoAdapter(
        private val objetivos: List<ObjetivoAprendizaje>,
        private val onClick: (ObjetivoAprendizaje) -> Unit
    ) : RecyclerView.Adapter<ObjetivoAdapter.ViewHolder>() {

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val tvCodigo: TextView = view.findViewById(R.id.tvCodigo)
            val tvDescripcion: TextView = view.findViewById(R.id.tvDescripcion)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_objetivo, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val objetivo = objetivos[position]
            holder.tvCodigo.text = objetivo.codigo
            holder.tvDescripcion.text = objetivo.descripcion
            holder.itemView.setOnClickListener { onClick(objetivo) }
        }

        override fun getItemCount() = objetivos.size
    }
}
