package com.example.cuponexmovil

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.cuponexmovil.poko.RespuestaLogin
import com.example.cuponexmovil.util.Constantes
import com.google.gson.Gson
import com.koushikdutta.ion.Ion

class LoginUser : AppCompatActivity() {

    private lateinit var etEmailUser : EditText
    private lateinit var etPassUser : EditText

    var keyLogin : String = "idUsuario"
    /*val prefs = PreferenceManager.getDefaultSharedPreferences(this)*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_user)

        //Evento de botón Registrar usuario
        var btnRegistrar = findViewById<Button>(R.id.btnRegistrar)
        btnRegistrar.setOnClickListener {
            val viewRegistrar = Intent(this@LoginUser, RegistrarUser::class.java)
            startActivity(viewRegistrar)
            //finish()
        }

        //Evento de botón Ingresar usuario
        var btnIngresar = findViewById<Button>(R.id.btnIngresar)
        btnIngresar.setOnClickListener {
            validarCampos()
        }

        //Inicializar variables de EditText
        etEmailUser = findViewById(R.id.etEmailUser)
        etPassUser = findViewById(R.id.etPassUser)
    }

    private fun validarCampos(){
        val emailTxt = etEmailUser.text.toString()
        val passTxt = etPassUser.text.toString()
        var band = true

        if (emailTxt.isEmpty()){
            band = false
            etEmailUser.setError("Debe de ingresar el correo electrónico")
        }

        if (passTxt.isEmpty()){
            band = false
            etPassUser.setError("Por favor, ingrese su contraseña")
        }

        if (band){
            enviarDatos(emailTxt, passTxt)
        }
    }

    private fun enviarDatos(email:String, password:String){
        Ion.getDefault(this@LoginUser).conscryptMiddleware.enable(false)
        Ion.with(this@LoginUser).load("POST", Constantes.URL_WS+"movil/inicioSesion")
            .setHeader("Content-Type", "application/x-www-form-urlencoded")
            .setBodyParameter("correo", email)
            .setBodyParameter("password", password)
            .asString().setCallback { e, result ->
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
                val nombreCompleto = "Bienvenido(a)" + respuestaWS.nombre + " " + respuestaWS.apellidoPaterno + " " + respuestaWS.apellidoMaterno
                mostrarAlerta(nombreCompleto)

                //Guardar el id del usuario
                val recordar = getSharedPreferences("estado", Context.MODE_PRIVATE)
                val editar = recordar.edit()
                editar.putInt(keyLogin, respuestaWS.idUsuario)
                editar.commit()

                /*val editorSP = prefs.edit()
                editorSP.putInt(keyLogin, respuestaWS.idUsuario)
                editorSP.apply()*/

                val irPrincipal = Intent(this@LoginUser, MainActivity::class.java)
                startActivity(irPrincipal)
                finish()
            } else {
                mostrarAlerta(respuestaWS.mensaje)
            }
        }
    }

    private fun mostrarAlerta(mensaje:String){
        Toast.makeText(this@LoginUser, mensaje, Toast.LENGTH_LONG).show()
    }
}