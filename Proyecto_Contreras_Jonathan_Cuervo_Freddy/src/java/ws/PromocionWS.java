/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import java.util.HashMap;
import java.util.List;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojos.Promocion;
import pojos.Respuesta;
import pojos.RespuestaLogin;
import pojos.Sucursal;

/**
 *
 * @author fredd
 */
@Path("promociones")
public class PromocionWS {
    
    @Context
    private UriInfo context;

    public PromocionWS() {
    }
    
    @Path("all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Promocion> buscarTodos(){
        List<Promocion> listaPromociones = null;
        SqlSession conexionBD= MyBatisUtil.getSession();
        if( conexionBD != null){
            try{
                listaPromociones = conexionBD.selectList("promociones.all");
            }catch(Exception e){
                e.printStackTrace();
            }finally{
                conexionBD.close();
            }
            
        }
        return listaPromociones;
    }
    
    @Path("obteneridpromocion")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Promocion buscaridPromocion(){
        Promocion listaPromociones = null;
        SqlSession conexionBD= MyBatisUtil.getSession();
        if( conexionBD != null){
            try{
                listaPromociones = conexionBD.selectOne("promociones.obteneridpromocion");
            }catch(Exception e){
                e.printStackTrace();
            }finally{
                conexionBD.close();
            }
            
        }
        return listaPromociones;
    }
    
    @Path("registrar")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta registrar(
        @FormParam("nombrePromocion") String nombrePromocion,
        @FormParam("descripcion") String descripcion,
        @FormParam("fechaInicioPromocion") String fechaInicioPromocion,
        @FormParam("fechaFinPromocion") String fechaFinPromocion,
        @FormParam("idTipoPromocion") Integer idTipoPromocion,
        @FormParam("porcentajeDescuento") Double porcentajeDescuento,
        @FormParam("costo") Double costo,
        @FormParam("idCategoria") Integer idCategoria,
        @FormParam("idEstado") Integer idEstado){
        
        Promocion promocion = new Promocion(); 
        promocion.setNombrePromocion(nombrePromocion);
        promocion.setDescripcion(descripcion);
        promocion.setFechaInicioPromocion(fechaInicioPromocion);
        promocion.setFechaFinPromocion(fechaFinPromocion);
        promocion.setIdTipoPromocion(idTipoPromocion);
        promocion.setPorcentajeDescuento(porcentajeDescuento);
        promocion.setCosto(costo);
        promocion.setIdCategoria(idCategoria);
        promocion.setIdEstado(idEstado);
        
        Respuesta respuestaWS = new Respuesta();
        SqlSession conexionBD = new MyBatisUtil().getSession();
        if(conexionBD != null){
            try{
                int resultado = conexionBD.insert("promociones.registrar", promocion);
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
    
    @Path("actualizar")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta actualizar(
        @FormParam("nombrePromocion") String nombrePromocion,
        @FormParam("descripcion") String descripcion,
        @FormParam("fechaInicioPromocion") String fechaInicioPromocion,
        @FormParam("fechaFinPromocion") String fechaFinPromocion,
        @FormParam("idTipoPromocion") Integer idTipoPromocion,
        @FormParam("porcentajeDescuento") Double porcentajeDescuento,
        @FormParam("costo") Double costo,
        @FormParam("idCategoria") Integer idCategoria,
        @FormParam("idEstado") Integer idEstado,
        @FormParam("idPromocion") Integer idPromocion){
        
        Promocion promocion = new Promocion(); 
        promocion.setNombrePromocion(nombrePromocion);
        promocion.setDescripcion(descripcion);
        promocion.setFechaInicioPromocion(fechaInicioPromocion);
        promocion.setFechaFinPromocion(fechaFinPromocion);
        promocion.setIdTipoPromocion(idTipoPromocion);
        promocion.setPorcentajeDescuento(porcentajeDescuento);
        promocion.setCosto(costo);
        promocion.setIdCategoria(idCategoria);
        promocion.setIdEstado(idEstado);
        promocion.setIdPromocion(idPromocion);
        
        Respuesta respuestaWS = new Respuesta();
        SqlSession conexionBD = new MyBatisUtil().getSession();
        if(conexionBD != null){
            try{
                int resultado = conexionBD.update("promociones.actualizar", promocion);
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
    
    @Path("eliminar")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta eliminar(@FormParam("idPromocion") Integer idPromocion){
        Respuesta respuestaWS = new Respuesta();
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try{
                int respuesta = conexionBD.delete("promociones.eliminar", idPromocion);
                conexionBD.commit();
                if(respuesta>0){
                    respuestaWS.setError(false);
                    respuestaWS.setMensaje("Registro de la promocion eliminado");
                }else{
                    respuestaWS.setError(false);
                    respuestaWS.setMensaje("No se pudó eliminar el registro solicitado");
                }
            }catch(Exception e){
                respuestaWS.setError(true);
                respuestaWS.setMensaje(e.getMessage());
            }finally{
                conexionBD.close();
            }
        }else{
            respuestaWS.setError(true);
            respuestaWS.setMensaje("No hay conexión");
        }
        return respuestaWS;
    }
    
    @Path("desactivar")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta desactivar(@FormParam("idPromocion") Integer idPromocion){
        Respuesta respuestaWS = new Respuesta();
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try{
                int respuesta = conexionBD.update("promociones.desactivar", idPromocion);
                conexionBD.commit();
                if(respuesta>0){
                    respuestaWS.setError(false);
                    respuestaWS.setMensaje("Promocion desactivada");
                }else{
                    respuestaWS.setError(false);
                    respuestaWS.setMensaje("No se pudó desactivar el registro solicitado");
                }
            }catch(Exception e){
                respuestaWS.setError(true);
                respuestaWS.setMensaje(e.getMessage());
            }finally{
                conexionBD.close();
            }
        }else{
            respuestaWS.setError(true);
            respuestaWS.setMensaje("No hay conexión");
        }
        return respuestaWS;
    }
    

    
    @Path("buscarnombrepromo")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Promocion obtenerDatos( 
            @FormParam("nombrePromocion") String nombrePromocion){
        
        Promocion promocion = new Promocion();
        promocion.setNombrePromocion(nombrePromocion);
        
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try {
                promocion = conexionBD.selectOne("promociones.buscarPromo", promocion);
            } catch (Exception e) {
                e.printStackTrace();
            }finally{
                conexionBD.close();
            }
        }
        return promocion;
    }
    
    @Path("buscarnombrepromoFecha")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Promocion obtenerDatosFecha( 
            @FormParam("fechaInicioPromocion") String fechaInicioPromocion){
        
        Promocion promocion = new Promocion();
        promocion.setFechaInicioPromocion(fechaInicioPromocion);
        
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try {
                promocion = conexionBD.selectOne("promociones.buscarPromocion", promocion);
            } catch (Exception e) {
                e.printStackTrace();
            }finally{
                conexionBD.close();
            }
        }
        return promocion;
    }
    
    @Path("buscarnombrepromoFechaFin")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Promocion obtenerDatosFechaFin( 
            @FormParam("fechaFinPromocion") String fechaFinPromocion){
        
        Promocion promocion = new Promocion();
        promocion.setFechaFinPromocion(fechaFinPromocion);
        
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try {
                promocion = conexionBD.selectOne("promociones.buscarPromocionFin", promocion);
            } catch (Exception e) {
                e.printStackTrace();
            }finally{
                conexionBD.close();
            }
        }
        return promocion;
    }
    
    @Path("subirFoto/{idPromocion}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta subirFoto(byte[] foto, 
            @PathParam("idPromocion") Integer idPromocion){
        
        Respuesta respuesta = new Respuesta();
        respuesta.setError(true);
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try{
                HashMap<String, Object> parametros = new HashMap<>();
                parametros.put("foto", foto);
                parametros.put("idPromocion", idPromocion);
                int filasAfectadas = conexionBD.update("promociones.subirFoto", parametros);
                conexionBD.commit();
                if(filasAfectadas >0){
                    respuesta.setError(false);
                    respuesta.setMensaje("Foto de la promocion guardada correctamente");
                }else{
                    respuesta.setMensaje("No se puedo guardar la foto de promocion");
                }
            }catch(Exception e){
                respuesta.setMensaje(e.getMessage());
            }finally{
                conexionBD.close();
            }
        }else{
            respuesta.setMensaje("Por el momento no hay conexión...");
            
        }
        return respuesta;
        
    }
    
    @Path("obtenerFoto/{idPromocion}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Promocion regresarFotoMedico(
            @PathParam("idPromocion") Integer idPromocion){
        
        Promocion promocion = new Promocion();
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try {
                promocion = conexionBD.selectOne("promociones.obtenerFoto", idPromocion);
            } catch (Exception e) {
                e.printStackTrace();
            }finally{
                conexionBD.close();
            }
        }
        return promocion;
    }
    
 

    
    @Path("obtenerdatosPromocionporcategoria/{idCategoria}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Promocion> buscarTodos(@PathParam("idCategoria") 
            Integer idCategoria){
        List<Promocion> listaPromociones = null;
        SqlSession conexionBD= MyBatisUtil.getSession();
        if( conexionBD != null){
            try{
                listaPromociones = conexionBD.selectList("promociones.buscarPromocionCategoria", idCategoria);
            }catch(Exception e){
                e.printStackTrace();
            }finally{
                conexionBD.close();
            }
            
        }
        return listaPromociones;
    }
    
    
    
    
    
    
}
