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
public class Sucursal {
    private Integer idSucursal;
    private String nombre;
    private String direccionCalle;
    private Integer direccionNumero;
    private Integer cp;
    private String colonia;
    private String ciudad;
    private String telefono;
    private Double latitud;
    private Double longitud;
    private String nombreEncargado;
    private String empresa;
    private Integer idEmpresa;

    public Sucursal() {
    }

    public Sucursal(Integer idSucursal, String nombre, String direccionCalle, Integer direccionNumero, Integer cp, String colonia, String ciudad, String telefono, Double latitud, Double longitud, String nombreEncargado,String empresa, Integer idEmpresa) {
        this.idSucursal = idSucursal;
        this.nombre = nombre;
        this.direccionCalle = direccionCalle;
        this.direccionNumero = direccionNumero;
        this.cp = cp;
        this.colonia = colonia;
        this.ciudad = ciudad;
        this.telefono = telefono;
        this.latitud = latitud;
        this.longitud = longitud;
        this.nombreEncargado = nombreEncargado;
        this.empresa = empresa;
        this.idEmpresa = idEmpresa;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }
    

    public Integer getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(Integer idSucursal) {
        this.idSucursal = idSucursal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
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

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public String getNombreEncargado() {
        return nombreEncargado;
    }

    public void setNombreEncargado(String nombreEncargado) {
        this.nombreEncargado = nombreEncargado;
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }
    
    
}
