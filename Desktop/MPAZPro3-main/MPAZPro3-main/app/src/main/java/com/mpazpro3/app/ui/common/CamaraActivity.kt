package com.mpazpro3.app.ui.common

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.mpazpro3.app.databinding.ActivityCamaraBinding

class CamaraActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCamaraBinding
    private val REQUEST_CAMERA = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCamaraBinding.inflate(layoutInflater)
        setContentView(binding.root)

        verificarPermisoCamara()

        binding.btnCapturar.setOnClickListener {
            Toast.makeText(this, "Foto capturada (simulacion)", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun verificarPermisoCamara() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CAMERA),
                REQUEST_CAMERA
            )
        }
    }
}
