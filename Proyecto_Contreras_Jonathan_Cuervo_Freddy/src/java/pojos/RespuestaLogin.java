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
public class RespuestaLogin {
    private Boolean error;
    private String mensaje;
    private String nombre;
    private String apellidoParterno;
    private String apellidoMaterno;
    private Integer idUsuario;
    private String token;

    public RespuestaLogin() {
    }

    public RespuestaLogin(Boolean error, String mensaje, String nombre, String apellidoParterno, String apellidoMaterno,Integer idUsuario, String token) {
        this.error = error;
        this.mensaje = mensaje;
        this.nombre = nombre;
        this.apellidoParterno = apellidoParterno;
        this.apellidoMaterno = apellidoMaterno;
        this.token = token;
        this.idUsuario = idUsuario;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }
    
}
