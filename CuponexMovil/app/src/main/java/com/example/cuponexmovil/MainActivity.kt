package com.example.cuponexmovil

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var btnPromociones = findViewById<Button>(R.id.btnPromociones)
        btnPromociones.setOnClickListener {
            val viewPromociones = Intent(this@MainActivity, Promociones::class.java)
            startActivity(viewPromociones)
        }

        var btnEditarP = findViewById<Button>(R.id.btnEditarP)
        btnEditarP.setOnClickListener {
            val viewEditarP = Intent(this@MainActivity, EditarPerfil::class.java)
            startActivity(viewEditarP)
        }
    }
}