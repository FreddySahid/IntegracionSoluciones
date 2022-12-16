/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojos.Respuesta;
import pojos.SucursalPromocion;

/**
 *
 * @author fredd
 */
@Path("sucursalPromocion")
public class SucursalPromocionWS {

    @Context
    private UriInfo context;
    

    public SucursalPromocionWS() {
    }
    
    @Path("registrarsucursalpromo")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta registrarsucursalpromo(
        @FormParam("idSucursal") Integer idSucursal,
        @FormParam("idPromocion") Integer idPromocion){
        
        SucursalPromocion sucursalPromocion = new SucursalPromocion();
        sucursalPromocion.setIdSucursal(idSucursal);
        sucursalPromocion.setIdPromocion(idPromocion);
        

        Respuesta respuestaWS = new Respuesta();
        SqlSession conexionBD = new MyBatisUtil().getSession();
        if(conexionBD != null){
            try{
                int resultado = conexionBD.insert("sucursalPromocion.registrarsucursalpromo", sucursalPromocion);
                conexionBD.commit();
                if(resultado > 0){
                    respuestaWS.setError(false);
                    respuestaWS.setMensaje("Información de la promoción registrada con éxito");
                }else{
                    respuestaWS.setError(true);
                    respuestaWS.setMensaje("No se pudo registrar la promoción, intentelo de nuevo más tarde");
                    
                }
            }catch(Exception e){
                respuestaWS.setError(true);
                respuestaWS.setMensaje(e.getMessage());
            }finally{
                conexionBD.close();
            }
        }else{
            respuestaWS.setError(true);
            respuestaWS.setMensaje("Servicio no disponible, intentelo más tarde.");
        }
        return respuestaWS;
    }
    
    
    
}
