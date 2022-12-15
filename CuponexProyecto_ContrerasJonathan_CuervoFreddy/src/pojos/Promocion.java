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
public class Promocion {
    private Integer idPromocion;
    private String nombrePromocion;
    private String foto;
    private String descripcion;
    private String fechaInicioPromocion;
    private String fechaFinPromocion;
    private Integer idTipoPromocion;
    private String tipoPromocion;
    private Double porcentajeDescuento;
    private Double costo;
    private Integer idCategoria;
    private String categoria;
    private Integer idEstado;
    private String estado;

    public Promocion(Integer idPromocion, String nombrePromocion, String foto, String descripcion, String fechaInicioPromocion, String fechaFinPromocion, Integer idTipoPromocion, String tipoPromocion, Double porcentajeDescuento, Double costo, Integer idCategoria, String categoria, Integer idEstado, String estado) {
        this.idPromocion = idPromocion;
        this.nombrePromocion = nombrePromocion;
        this.foto = foto;
        this.descripcion = descripcion;
        this.fechaInicioPromocion = fechaInicioPromocion;
        this.fechaFinPromocion = fechaFinPromocion;
        this.idTipoPromocion = idTipoPromocion;
        this.tipoPromocion = tipoPromocion;
        this.porcentajeDescuento = porcentajeDescuento;
        this.costo = costo;
        this.idCategoria = idCategoria;
        this.categoria = categoria;
        this.idEstado = idEstado;
        this.estado = estado;
    }

    public Promocion() {
    }

    public Integer getIdPromocion() {
        return idPromocion;
    }

    public void setIdPromocion(Integer idPromocion) {
        this.idPromocion = idPromocion;
    }

    public String getNombrePromocion() {
        return nombrePromocion;
    }

    public void setNombrePromocion(String nombrePromocion) {
        this.nombrePromocion = nombrePromocion;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFechaInicioPromocion() {
        return fechaInicioPromocion;
    }

    public void setFechaInicioPromocion(String fechaInicioPromocion) {
        this.fechaInicioPromocion = fechaInicioPromocion;
    }

    public String getFechaFinPromocion() {
        return fechaFinPromocion;
    }

    public void setFechaFinPromocion(String fechaFinPromocion) {
        this.fechaFinPromocion = fechaFinPromocion;
    }

    public Integer getIdTipoPromocion() {
        return idTipoPromocion;
    }

    public void setIdTipoPromocion(Integer idTipoPromocion) {
        this.idTipoPromocion = idTipoPromocion;
    }

    public String getTipoPromocion() {
        return tipoPromocion;
    }

    public void setTipoPromocion(String tipoPromocion) {
        this.tipoPromocion = tipoPromocion;
    }

    public Double getPorcentajeDescuento() {
        return porcentajeDescuento;
    }

    public void setPorcentajeDescuento(Double porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Integer getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
}
