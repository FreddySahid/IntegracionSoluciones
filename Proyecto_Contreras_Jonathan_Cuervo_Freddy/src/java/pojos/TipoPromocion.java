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
public class TipoPromocion {
    private Integer idTipoPromocion;
    private String tipoPromocion;

    public TipoPromocion() {
    }
    

    public TipoPromocion(Integer idTipoPromocion, String tipoPromocion) {
        this.idTipoPromocion = idTipoPromocion;
        this.tipoPromocion = tipoPromocion;
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
    
    
}
