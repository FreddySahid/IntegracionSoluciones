/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

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
import pojos.Empresa;
import pojos.Respuesta;
import pojos.RespuestaLogin;
import pojos.Sucursal;
import pojos.TipoPromocion;

/**
 *
 * @author fredd
 */
@Path("sucursales")
public class SucursalWS {
    
    @Context
    private UriInfo context;

    public SucursalWS() {
    }
    
    @Path("registrar")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta registrar(
            @FormParam("nombre") String nombre,
            @FormParam("direccionCalle") String direccionCalle,
            @FormParam("direccionNumero") Integer direccionNumero,
            @FormParam("cp") Integer cp,
            @FormParam("colonia") String colonia,
            @FormParam("ciudad") String ciudad,
            @FormParam("telefono") String telefono,
            @FormParam("latitud") Double latitud,
            @FormParam("longitud") Double longitud,
            @FormParam("nombreEncargado") String nombreEncargado,
            @FormParam("idEmpresa") Integer idEmpresa){
        
        Sucursal sucursal = new Sucursal();
        sucursal.setNombre(nombre);
        sucursal.setDireccionCalle(direccionCalle);
        sucursal.setDireccionNumero(direccionNumero);
        sucursal.setCp(cp);
        sucursal.setColonia(colonia);
        sucursal.setCiudad(ciudad);
        sucursal.setTelefono(telefono);
        sucursal.setLatitud(latitud);
        sucursal.setLongitud(longitud);
        sucursal.setNombreEncargado(nombreEncargado);
        sucursal.setIdEmpresa(idEmpresa);
 
        Respuesta respuestaWS = new Respuesta();
        SqlSession conexionBD = new MyBatisUtil().getSession();
        if(conexionBD != null){
            try{
                int resultado = conexionBD.insert("sucursales.registrar", sucursal);
                conexionBD.commit();
                if(resultado > 0){
                    respuestaWS.setError(false);
                    respuestaWS.setMensaje("Información de la sucursal registrada con éxito");
                }else{
                    respuestaWS.setError(true);
                    respuestaWS.setMensaje("No se pudo registrar la sucursal, intentelo de nuevo más tarde");
                    
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
            @FormParam("idSucursal") Integer idSucursal,
            @FormParam("nombre") String nombre,
            @FormParam("direccionCalle") String direccionCalle,
            @FormParam("direccionNumero") Integer direccionNumero,
            @FormParam("cp") Integer cp,
            @FormParam("colonia") String colonia,
            @FormParam("ciudad") String ciudad,
            @FormParam("telefono") String telefono,
            @FormParam("latitud") Double latitud,
            @FormParam("longitud") Double longitud,
            @FormParam("nombreEncargado") String nombreEncargado,
            @FormParam("idEmpresa") Integer idEmpresa){
        
        Sucursal sucursal = new Sucursal();
        sucursal.setIdSucursal(idSucursal);
        sucursal.setNombre(nombre);
        sucursal.setDireccionCalle(direccionCalle);
        sucursal.setDireccionNumero(direccionNumero);
        sucursal.setCp(cp);
        sucursal.setColonia(colonia);
        sucursal.setCiudad(ciudad);
        sucursal.setTelefono(telefono);
        sucursal.setLatitud(latitud);
        sucursal.setLongitud(longitud);
        sucursal.setNombreEncargado(nombreEncargado);
        sucursal.setIdEmpresa(idEmpresa);
 
        Respuesta respuestaWS = new Respuesta();
        SqlSession conexionBD = new MyBatisUtil().getSession();
        if(conexionBD != null){
            try{
                int resultado = conexionBD.update("sucursales.actualizar", sucursal);
                conexionBD.commit();
                if(resultado > 0){
                    respuestaWS.setError(false);
                    respuestaWS.setMensaje("Información de la sucursal actualizada con éxito");
                }else{
                    respuestaWS.setError(true);
                    respuestaWS.setMensaje("No se pudo actualizar la sucursal, intentelo de nuevo más tarde");
                    
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
    public Respuesta eliminar(@FormParam("idSucursal") Integer idSucursal){
        Respuesta respuestaWS = new Respuesta();
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try{
                int respuesta = conexionBD.update("sucursales.eliminar", idSucursal);
                conexionBD.commit();
                if(respuesta>0){
                    respuestaWS.setError(false);
                    respuestaWS.setMensaje("Registro de la sucursal eliminado");
                }else{
                    respuestaWS.setError(false);
                    respuestaWS.setMensaje("No se pudó eliminar el registro solicitado, la sucursal aún tiene sucursales existentes.");
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
    
    @Path("buscarnombredireccion")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Sucursal obtenerTodosDatos( 
            @FormParam("nombre") String nombre){
        Sucursal sucursal = new Sucursal();
        sucursal.setNombre(nombre);
        
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try {
                sucursal = conexionBD.selectOne("sucursales.getSucursal", sucursal);
            } catch (Exception e) {
                e.printStackTrace();
            }finally{
                conexionBD.close();
            }
        }
        return sucursal;
    }
    
    @Path("SucursalEmpresa/{idEmpresa}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Sucursal> buscarTodos(
            @PathParam("idEmpresa") Integer idEmpresa){
        List<Sucursal> listaEmpresa = null;
        SqlSession conexionBD= MyBatisUtil.getSession();
        if( conexionBD != null){
            try{
                listaEmpresa = conexionBD.selectList("sucursales.getSucursalEmpresa", idEmpresa);
            }catch(Exception e){
                e.printStackTrace();
            }finally{
                conexionBD.close();
            }
            
        }
        return listaEmpresa;
    }
    
    @Path("all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Sucursal> buscarTodos(){
        List<Sucursal> listaEmpresa = null;
        SqlSession conexionBD= MyBatisUtil.getSession();
        if( conexionBD != null){
            try{
                listaEmpresa = conexionBD.selectList("sucursales.getAll");
            }catch(Exception e){
                e.printStackTrace();
            }finally{
                conexionBD.close();
            }
            
        }
        return listaEmpresa;
    }
    
    @Path("sucursalNombre")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public RespuestaLogin iniciarSesionUsuario(
            @FormParam("nombre") String nombre)
            {

        Sucursal sucursal = new Sucursal();
        sucursal.setNombre(nombre);
        
        RespuestaLogin respuesta = new RespuestaLogin();
        SqlSession conexionBD = new MyBatisUtil().getSession();
        
        if(conexionBD != null){
            try{
                sucursal = conexionBD.selectOne("sucursales.sucursalNombre", sucursal);
                conexionBD.commit();
                //if(resultado > 0){
                    respuesta.setError(false);
                    //respuesta.setMensaje("Bien venido " + paciente.getNombre());
                    respuesta.setIdUsuario(sucursal.getIdSucursal());
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
