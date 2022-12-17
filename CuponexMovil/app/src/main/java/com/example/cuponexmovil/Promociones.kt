package com.example.cuponexmovil

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView

class Promociones : AppCompatActivity() {

    private lateinit var spCategorias : Spinner
    private lateinit var spListaPromocion : Spinner
    private lateinit var ivFoto : ImageView
    private lateinit var tvNombreP : TextView
    private lateinit var tvDescripcion : TextView
    private lateinit var tvVigencia : TextView
    private lateinit var tvRestricciones : TextView
    private lateinit var tvTipoPromocion : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_promociones)

        spCategorias = findViewById(R.id.spCategorias)
        spListaPromocion = findViewById(R.id.spListaPromocion)
        ivFoto = findViewById(R.id.ivFoto)
        tvNombreP = findViewById(R.id.tvNombreP)
        tvDescripcion = findViewById(R.id.tvDescripcion)
        tvVigencia = findViewById(R.id.tvVigencia)
        tvRestricciones = findViewById(R.id.tvRestricciones)
        tvTipoPromocion = findViewById(R.id.tvTipoPromocion)
    }
}