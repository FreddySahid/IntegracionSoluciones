/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;


import java.util.List;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojos.Categoria;
import pojos.RespuestaLogin;
import pojos.TipoPromocion;

/**
 *
 * @author fredd
 */
@Path("tipopromociones")
public class TipoPromocionWS {

    @Context
    private UriInfo context;
        
    public TipoPromocionWS() {
    }
    
    
    
    @Path("all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<TipoPromocion> buscarTodos(){
        List<TipoPromocion> listaEmpresa = null;
        SqlSession conexionBD= MyBatisUtil.getSession();
        if( conexionBD != null){
            try{
                listaEmpresa = conexionBD.selectList("tipopromociones.all");
            }catch(Exception e){
                e.printStackTrace();
            }finally{
                conexionBD.close();
            }
            
        }
        return listaEmpresa;
    }
    
    @Path("tipoPromocionNombre")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public RespuestaLogin iniciarSesionUsuario(
            @FormParam("tipoPromocion") String tipoPromocion)
            {

        TipoPromocion tipoPromocionO = new TipoPromocion();
        tipoPromocionO.setTipoPromocion(tipoPromocion);
        
        RespuestaLogin respuesta = new RespuestaLogin();
        SqlSession conexionBD = new MyBatisUtil().getSession();
        
        if(conexionBD != null){
            try{
                tipoPromocionO = conexionBD.selectOne("tipopromociones.tipoPromocionNombre", tipoPromocionO);
                conexionBD.commit();
                //if(resultado > 0){
                    respuesta.setError(false);
                    //respuesta.setMensaje("Bien venido " + paciente.getNombre());
                    respuesta.setIdUsuario(tipoPromocionO.getIdTipoPromocion());
                /*}else{
                    respuesta.setError(true);
                    respuesta.setMensaje("No se pudo encontrar el paciente, intentelo de nuevo más tarde");
                    
                }*/
            }catch(Exception e){
                respuesta.setError(true);
                respuesta.setMensaje(e.getMessage());
            }finally{
                conexionBD.close();
            }
        }else{
            respuesta.setError(true);
            respuesta.setMensaje("Servicio no disponible, intentelo más tarde.");
        }
        
        return respuesta;
    }
    
}
