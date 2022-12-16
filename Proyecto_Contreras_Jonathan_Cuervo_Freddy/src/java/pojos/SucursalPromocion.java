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
public class SucursalPromocion {
    
    private Integer idSucursal;
    private Integer idPromocion;

    public SucursalPromocion(Integer idSucursal, Integer idPromocion) {
        this.idSucursal = idSucursal;
        this.idPromocion = idPromocion;
    }

    public SucursalPromocion() {
    }

    public Integer getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(Integer idSucursal) {
        this.idSucursal = idSucursal;
    }

    public Integer getIdPromocion() {
        return idPromocion;
    }

    public void setIdPromocion(Integer idPromocion) {
        this.idPromocion = idPromocion;
    }
    
    
    
}
