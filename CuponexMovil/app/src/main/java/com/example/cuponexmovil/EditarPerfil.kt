package com.example.cuponexmovil

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.cuponexmovil.poko.Respuesta
import com.example.cuponexmovil.poko.RespuestaLogin
import com.example.cuponexmovil.poko.UsuarioMovil
import com.example.cuponexmovil.util.Constantes
import com.google.gson.Gson
import com.koushikdutta.ion.Ion

class EditarPerfil : AppCompatActivity() {

    private lateinit var etEditName : EditText
    private lateinit var etEditApellidoP : EditText
    private lateinit var etEditApellidoM : EditText
    private lateinit var etEditTelefono : EditText
    private lateinit var etEditCalle : EditText
    private lateinit var etEditNumero : EditText
    private lateinit var etEditFecha : EditText
    private lateinit var etEditPassword : EditText

    var keyLogin : String = "idUsuario"

    var usuarioDatos : UsuarioMovil? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_perfil)

        etEditName = findViewById(R.id.etEditNombre)
        etEditApellidoP = findViewById(R.id.etEditApellidoP)
        etEditApellidoM = findViewById(R.id.etEditApellidoM)
        etEditTelefono = findViewById(R.id.etEditTelefono)
        etEditCalle = findViewById(R.id.etEditCalle)
        etEditNumero = findViewById(R.id.etEditNumero)
        etEditFecha = findViewById(R.id.etEditFecha)
        etEditPassword = findViewById(R.id.etEditPassword)

        obtenerDatos()

        var btnUpdateData = findViewById<Button>(R.id.btnUpdateData)
        btnUpdateData.setOnClickListener {
            validarCampos()
        }
    }

    private fun validarCampos(){
        var etEditNametxt = etEditName.text.toString()
        var etEditApellidoPtxt = etEditApellidoP.text.toString()
        var etEditApellidoMtxt = etEditApellidoM.text.toString()
        var etEditTelefonotxt = etEditTelefono.text.toString()
        var etEditCalletxt = etEditCalle.text.toString()
        var etEditNumerotxt = etEditNumero.text.toString()
        var etEditFechatxt = etEditFecha.text.toString()
        var etEditPasswordtxt = etEditPassword.text.toString()
        var band = true

        if (etEditNametxt.isEmpty()){
            band = false
            etEditName.setError("Debe ingresar su nombre")
        }
        if (etEditApellidoPtxt.isEmpty()){
            band = false
            etEditApellidoP.setError("Debe ingresar su apellido paterno")
        }
        if (etEditApellidoMtxt.isEmpty()){
            band = false
            etEditApellidoM.setError("Debe ingresar su apellido materno")
        }
        if (etEditTelefonotxt.isEmpty()){
            band = false
            etEditTelefono.setError("Debe ingresar su teléfono")
        }
        if (etEditCalletxt.isEmpty()){
            band = false
            etEditCalle.setError("Debe ingresar el nombre de la calle")
        }
        if (etEditNumerotxt.isEmpty()){
            band = false
            etEditNumero.setError("Debe ingresar el numero de la dirección")
        }
        if (etEditFechatxt.isEmpty()){
            band = false
            etEditFecha.setError("Debe ingresar su fecha de nacimiento")
        }
        if (etEditPasswordtxt.isEmpty()){
            band = false
            etEditPassword.setError("Debe ingresar una contraseña")
        }
        if (band){
            enviarDatos(etEditNametxt, etEditApellidoPtxt, etEditApellidoMtxt, etEditTelefonotxt, etEditCalletxt, etEditNumerotxt.toInt(), etEditFechatxt, etEditPasswordtxt)
        }
    }

    private fun obtenerDatos(){
        val recuperar = getSharedPreferences("estado", Context.MODE_PRIVATE)
        val idUsuario = recuperar.getInt(keyLogin, 0)
        //mostrarAlerta("Id usuario: " + idUsuario)
        Ion.with(this@EditarPerfil)
            .load("GET", Constantes.URL_WS + "movil/obtenerDatos/${idUsuario}")
            .asString()
            .setCallback { e, result ->
                if(e!=null){
                    mostrarAlerta(e.message!!)
                }else{
                    mostrarInformacionUsuario(result)
                }
            }
    }

    private fun mostrarInformacionUsuario(informacion : String){
        val gson = Gson()
        usuarioDatos = gson.fromJson(informacion, UsuarioMovil::class.java)
        //mostrarAlerta(usuarioDatos.toString())
        cargarDatosView()
    }

    private fun cargarDatosView(){
        if (usuarioDatos != null){
            etEditName.setText(usuarioDatos!!.nombre)
            etEditApellidoP.setText(usuarioDatos!!.apellidoPaterno)
            etEditApellidoM.setText(usuarioDatos!!.apellidoMaterno)
            etEditTelefono.setText(usuarioDatos!!.telefono)
            etEditCalle.setText(usuarioDatos!!.direccionCalle)
            etEditNumero.setText(usuarioDatos!!.direccionNumero.toString())
            etEditFecha.setText(usuarioDatos!!.fechaNacimiento)
            etEditPassword.setText(usuarioDatos!!.password)
        }
    }

    private fun enviarDatos(nombre:String, apellidoPaterno:String,
                            apellidoMaterno:String, telefono:String,
                            direccionCalle:String, direccionNumero:Int,
                            fechaNacimiento:String, password:String){
        val recuperar = getSharedPreferences("estado", Context.MODE_PRIVATE)
        val idUsuario = recuperar.getInt(keyLogin, 0)
        Ion.getDefault(this@EditarPerfil).conscryptMiddleware.enable(false)
        Ion.with(this@EditarPerfil).load("PUT", Constantes.URL_WS+"movil/actualizarDatos")
            .setHeader("Content-Type", "application/x-www-form-urlencoded")
            .setBodyParameter("idUsuario", idUsuario.toString())
            .setBodyParameter("nombre", nombre)
            .setBodyParameter("apellidoPaterno", apellidoPaterno)
            .setBodyParameter("apellidoMaterno", apellidoMaterno)
            .setBodyParameter("telefono", telefono)
            .setBodyParameter("direccionCalle", direccionCalle)
            .setBodyParameter("direccionNumero", direccionNumero.toString())
            .setBodyParameter("fechaNacimiento", fechaNacimiento)
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
            val respuestaWS = gson.fromJson(respuesta, Respuesta::class.java)
            if (!respuestaWS.error!!) {
                mostrarAlerta("Datos actualizados")
            } else {
                mostrarAlerta("Error al actualizar datos")
            }
        }
    }

    private fun mostrarAlerta(mensaje:String){
        Toast.makeText(this@EditarPerfil, mensaje, Toast.LENGTH_LONG).show()
    }
}