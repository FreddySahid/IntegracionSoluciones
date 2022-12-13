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
public class Empresa {
    private Integer idEmpresa;
    private String nombre;
    private String nombreComercial;
    private String nombreRepresentante;
    private String correoEmpresa;
    private String direccionCalle;
    private Integer direccionNumero;
    private Integer cp;
    private String ciudad;
    private String telefono;
    private String sitioWeb;
    private String rfc;
    private Integer idEstado;
    private String estado;
    private Integer numSucursales;
    

    public Empresa() {
    }

    public Empresa(Integer idEmpresa, String nombre, String nombreComercial, String nombreRepresentante, String correoEmpresa, String direccionCalle, Integer direccionNumero, Integer cp, String ciudad, String telefono, String sitioWeb, String rfc, Integer idEstado, Integer numSucursales, String estado) {
        this.idEmpresa = idEmpresa;
        this.nombre = nombre;
        this.nombreComercial = nombreComercial;
        this.nombreRepresentante = nombreRepresentante;
        this.correoEmpresa = correoEmpresa;
        this.direccionCalle = direccionCalle;
        this.direccionNumero = direccionNumero;
        this.cp = cp;
        this.ciudad = ciudad;
        this.telefono = telefono;
        this.sitioWeb = sitioWeb;
        this.rfc = rfc;
        this.idEstado = idEstado;
        this.numSucursales = numSucursales;
        this.estado = estado;
    }
    
    

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreComercial() {
        return nombreComercial;
    }

    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    public String getNombreRepresentante() {
        return nombreRepresentante;
    }

    public void setNombreRepresentante(String nombreRepresentante) {
        this.nombreRepresentante = nombreRepresentante;
    }

    public String getCorreoEmpresa() {
        return correoEmpresa;
    }

    public void setCorreoEmpresa(String correoEmpresa) {
        this.correoEmpresa = correoEmpresa;
    }

    public String getDireccionCalle() {
        return direccionCalle;
    }

    public void setDireccionCalle(String direccionCalle) {
        this.direccionCalle = direccionCalle;
    }

    public Integer getDireccionNumero() {
        return direccionNumero;
    }

    public void setDireccionNumero(Integer direccionNumero) {
        this.direccionNumero = direccionNumero;
    }

    public Integer getCp() {
        return cp;
    }

    public void setCp(Integer cp) {
        this.cp = cp;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getSitioWeb() {
        return sitioWeb;
    }

    public void setSitioWeb(String sitioWeb) {
        this.sitioWeb = sitioWeb;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public Integer getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }

    public Integer getNumSucursales() {
        return numSucursales;
    }

    public void setNumSucursales(Integer numSucursales) {
        this.numSucursales = numSucursales;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
    
}
