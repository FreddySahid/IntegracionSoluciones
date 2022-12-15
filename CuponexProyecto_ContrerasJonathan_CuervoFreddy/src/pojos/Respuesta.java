/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pojos;

/**
 *
 * @author fredd
 */
public class Respuesta {
    private boolean error;
    private String mensaje;
    private String nombre;
    private String apellidoParterno;
    private String apellidoMaterno;
    private Integer idUsuario;

    public Respuesta() {
    }

    public Respuesta(boolean error, String mensaje, String nombre,String apellidoParterno, String apellidoMaterno, Integer idUsuario ) {
        this.error = error;
        this.mensaje = mensaje;
        this.nombre = nombre;
        this.apellidoParterno = apellidoParterno;
        this.apellidoMaterno = apellidoMaterno;  
        this.idUsuario = idUsuario;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public boolean getError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoParterno() {
        return apellidoParterno;
    }

    public void setApellidoParterno(String apellidoParterno) {
        this.apellidoParterno = apellidoParterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }
    
    
    
}
