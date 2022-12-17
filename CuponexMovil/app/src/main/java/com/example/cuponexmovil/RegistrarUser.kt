package com.example.cuponexmovil

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.cuponexmovil.poko.RespuestaLogin
import com.example.cuponexmovil.util.Constantes
import com.google.gson.Gson
import com.koushikdutta.ion.Ion

class RegistrarUser : AppCompatActivity() {

    private lateinit var etNombreUser : EditText
    private lateinit var etApellidoPaternoUser : EditText
    private lateinit var etApellidoMaternoUser : EditText
    private lateinit var etTelefonoUser : EditText
    private lateinit var etCalleUser : EditText
    private lateinit var etNumeroUser : EditText
    private lateinit var etFechaUser : EditText
    private lateinit var etEmailRUser : EditText
    private lateinit var etPassRUser : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar_user)

        //Evento de botón para la página principal
        var btnPrincipal = findViewById<Button>(R.id.btnPrincipal)
        btnPrincipal.setOnClickListener {
            validarDatos();
        }

        //Inicializar variables
        etNombreUser = findViewById(R.id.etNombreUser)
        etApellidoPaternoUser = findViewById(R.id.etApellidoPaternoUser)
        etApellidoMaternoUser = findViewById(R.id.etApellidoMaternoUser)
        etTelefonoUser = findViewById(R.id.etTelefonoUser)
        etCalleUser = findViewById(R.id.etCalleUser)
        etNumeroUser = findViewById(R.id.etNumeroUser)
        etFechaUser = findViewById(R.id.etFechaUser)
        etEmailRUser = findViewById(R.id.etEmailRUser)
        etPassRUser = findViewById(R.id.etPassRUser)
    }

    private fun validarDatos(){
        val etNombreUsertxt = etNombreUser.text.toString()
        val etApellidoPaternoUsertxt = etApellidoPaternoUser.text.toString()
        val etApellidoMaternoUsertxt = etApellidoMaternoUser.text.toString()
        val etTelefonoUsertxt = etTelefonoUser.text.toString()
        val etCalleUsertxt = etCalleUser.text.toString()
        val etNumeroUsertxt = etNumeroUser.text.toString()
        val etFechaUsertxt = etFechaUser.text.toString()
        val etEmailRUsertxt = etEmailRUser.text.toString()
        val etPassRUsertxt = etPassRUser.text.toString()
        var band = true

        if (etNombreUsertxt.isEmpty()){
            band = false
            etNombreUser.setError("Debe ingresar su nombre")
        }
        if (etApellidoPaternoUsertxt.isEmpty()){
            band = false
            etApellidoPaternoUser.setError("Debe ingresar su apellido paterno")
        }
        if (etApellidoMaternoUsertxt.isEmpty()){
            band = false
            etApellidoMaternoUser.setError("Debe ingresar su apellido materno")
        }
        if (etTelefonoUsertxt.isEmpty()){
            band = false
            etTelefonoUser.setError("Debe ingresar su teléfono")
        }
        if (etCalleUsertxt.isEmpty()){
            band = false
            etCalleUser.setError("Debe ingresar el nombre de la calle")
        }
        if (etNumeroUsertxt.isEmpty()){
            band = false
            etNumeroUser.setError("Debe ingresar el numero de la dirección")
        }
        if (etFechaUsertxt.isEmpty()){
            band = false
            etFechaUser.setError("Debe ingresar su fecha de nacimiento")
        }
        if (etEmailRUsertxt.isEmpty()){
            band = false
            etEmailRUser.setError("Debe ingresar su correo eléctronico")
        }
        if (etPassRUsertxt.isEmpty()){
            band = false
            etPassRUser.setError("Debe ingresar una contraseña")
        }

        if (band){
            val direccionNumero = etNumeroUsertxt.toInt()
            enviarDatos(etNombreUsertxt, etApellidoPaternoUsertxt, etApellidoMaternoUsertxt, etTelefonoUsertxt, etCalleUsertxt, direccionNumero, etFechaUsertxt, etEmailRUsertxt, etPassRUsertxt)
        }
    }

    private fun enviarDatos(nombre:String, apellidoPaterno:String,
                            apellidoMaterno:String, telefono:String,
                            direccionCalle:String, direccionNumero:Int,
                            fechaNacimiento:String, correo:String, password:String){
        Ion.getDefault(this@RegistrarUser).conscryptMiddleware.enable(false)
        Ion.with(this@RegistrarUser).load("POST", Constantes.URL_WS + "movil/registrarUserMovil")
            .setHeader("Content-Type", "application/x-www-form-urlencoded")
            .setBodyParameter("nombre", nombre)
            .setBodyParameter("apellidoPaterno", apellidoPaterno)
            .setBodyParameter("apellidoMaterno", apellidoMaterno)
            .setBodyParameter("telefono", telefono)
            .setBodyParameter("direccionCalle", direccionCalle)
            .setBodyParameter("direccionNumero", direccionNumero.toString())
            .setBodyParameter("fechaNacimiento", fechaNacimiento)
            .setBodyParameter("correo", correo)
            .setBodyParameter("password", password)
            .asString()
            .setCallback { e, result ->
                if (e != null){
                    //Hay error
                    e.printStackTrace()
                    mostrarAlerta("Error en los datos ingresados")
                } else {
                    //Ok 200
                    validarPeticion(result)
                }
            }
    }

    private fun validarPeticion(respuesta:String){
        if (respuesta == null || respuesta.isEmpty()) {
            mostrarAlerta("Por el momento no hay servicio disponible")
        } else {
            val gson = Gson()
            val respuestaWS = gson.fromJson(respuesta, RespuestaLogin::class.java)
            if (!respuestaWS.error!!) {
                //val nombreCompleto = "Bienvenido(a)" + respuestaWS.nombre + " " + respuestaWS.apellidoPaterno + " " + respuestaWS.apellidoMaterno
                mostrarAlerta("Bienvenido a Coponex")
                val irPrincipal = Intent(this@RegistrarUser, MainActivity::class.java)
                startActivity(irPrincipal)
                finish()
            } else {
                mostrarAlerta(respuestaWS.mensaje)
            }
        }
    }

    private fun mostrarAlerta(mensaje:String){
        Toast.makeText(this@RegistrarUser, mensaje, Toast.LENGTH_LONG).show()
    }
}