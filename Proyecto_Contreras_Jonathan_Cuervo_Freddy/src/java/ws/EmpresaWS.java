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
import pojos.Categoria;
import pojos.Empresa;
import pojos.Estado;
import pojos.Respuesta;
import pojos.RespuestaLogin;
import pojos.Usuario;

/**
 *
 * @author fredd
 */
@Path("empresas")
public class EmpresaWS {
    @Context
    private UriInfo context;

    public EmpresaWS() {
    }
    
    @Path("registrar")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta registrar(
            @FormParam("nombre") String nombre,
            @FormParam("nombreComercial") String nombreComercial,
            @FormParam("nombreRepresentante") String nombreRepresentante,
            @FormParam("correoEmpresa") String correoEmpresa,
            @FormParam("direccionCalle") String direccionCalle,
            @FormParam("direccionNumero") Integer direccionNumero,
            @FormParam("cp") Integer cp,
            @FormParam("ciudad") String ciudad,
            @FormParam("telefono") String telefono,
            @FormParam("sitioWeb") String sitioWeb,
            @FormParam("rfc") String rfc,
            @FormParam("idEstado") Integer idEstado){
        
        Empresa empresa = new Empresa();
        empresa.setNombre(nombre);
        empresa.setNombreComercial(nombreComercial);
        empresa.setNombreRepresentante(nombreRepresentante);
        empresa.setCorreoEmpresa(correoEmpresa);
        empresa.setDireccionCalle(direccionCalle);
        empresa.setDireccionNumero(direccionNumero);
        empresa.setCp(cp);
        empresa.setCiudad(ciudad);
        empresa.setTelefono(telefono);
        empresa.setSitioWeb(sitioWeb);
        empresa.setRfc(rfc);
        empresa.setIdEstado(idEstado);
         
        
 
        Respuesta respuestaWS = new Respuesta();
        SqlSession conexionBD = new MyBatisUtil().getSession();
        if(conexionBD != null){
            try{
                int resultado = conexionBD.insert("empresas.registrar", empresa);
                conexionBD.commit();
                if(resultado > 0){
                    respuestaWS.setError(false);
                    respuestaWS.setMensaje("Informaci??n de la empresa registrada con ??xito");
                }else{
                    respuestaWS.setError(true);
                    respuestaWS.setMensaje("No se pudo registrar la empresa, intentelo de nuevo m??s tarde");
                    
                }
            }catch(Exception e){
                respuestaWS.setError(true);
                respuestaWS.setMensaje(e.getMessage());
            }finally{
                conexionBD.close();
            }
        }else{
            respuestaWS.setError(true);
            respuestaWS.setMensaje("Servicio no disponible, intentelo m??s tarde.");
        }
        return respuestaWS;
    }
    
    @Path("actualizar")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta actualizar(
            @FormParam("idEmpresa") Integer idEmpresa,
            @FormParam("nombre") String nombre,
            @FormParam("nombreComercial") String nombreComercial,
            @FormParam("nombreRepresentante") String nombreRepresentante,
            @FormParam("correoEmpresa") String correoEmpresa,
            @FormParam("direccionCalle") String direccionCalle,
            @FormParam("direccionNumero") Integer direccionNumero,
            @FormParam("cp") Integer cp,
            @FormParam("ciudad") String ciudad,
            @FormParam("telefono") String telefono,
            @FormParam("sitioWeb") String sitioWeb,
            @FormParam("idEstado") Integer idEstado){
        
        Empresa empresa = new Empresa();
        empresa.setIdEmpresa(idEmpresa);
        empresa.setNombre(nombre);
        empresa.setNombreComercial(nombreComercial);
        empresa.setNombreRepresentante(nombreRepresentante);
        empresa.setCorreoEmpresa(correoEmpresa);
        empresa.setDireccionCalle(direccionCalle);
        empresa.setDireccionNumero(direccionNumero);
        empresa.setCp(cp);
        empresa.setCiudad(ciudad);
        empresa.setTelefono(telefono);
        empresa.setSitioWeb(sitioWeb);
        empresa.setIdEstado(idEstado);
         
        
 
        Respuesta respuestaWS = new Respuesta();
        SqlSession conexionBD = new MyBatisUtil().getSession();
        if(conexionBD != null){
            try{
                int resultado = conexionBD.update("empresas.actualizar", empresa);
                conexionBD.commit();
                if(resultado > 0){
                    respuestaWS.setError(false);
                    respuestaWS.setMensaje("Informaci??n de la empresa actualizada con ??xito");
                }else{
                    respuestaWS.setError(true);
                    respuestaWS.setMensaje("No se pudo actualizar la empresa, intentelo de nuevo m??s tarde");
                    
                }
            }catch(Exception e){
                respuestaWS.setError(true);
                respuestaWS.setMensaje(e.getMessage());
            }finally{
                conexionBD.close();
            }
        }else{
            respuestaWS.setError(true);
            respuestaWS.setMensaje("Servicio no disponible, intentelo m??s tarde.");
        }
        return respuestaWS;
    }
    
    @Path("all")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Empresa> buscarTodos(){
        List<Empresa> listaEmpresa = null;
        SqlSession conexionBD= MyBatisUtil.getSession();
        if( conexionBD != null){
            try{
                listaEmpresa = conexionBD.selectList("empresas.getAll");
            }catch(Exception e){
                e.printStackTrace();
            }finally{
                conexionBD.close();
            }
            
        }
        return listaEmpresa;
    }
    
    @Path("allEstado")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Estado> buscarTodosEstado(){
        List<Estado> listaEstados = null;
        SqlSession conexionBD= MyBatisUtil.getSession();
        if( conexionBD != null){
            try{
                listaEstados = conexionBD.selectList("empresas.getAllEstado");
            }catch(Exception e){
                e.printStackTrace();
            }finally{
                conexionBD.close();
            }
            
        }
        return listaEstados;
    }
    @Path("estadoNombre")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public RespuestaLogin obteneridEstado(
            @FormParam("estado") String estado)
            {

        Estado estadoO = new Estado();
        estadoO.setEstado(estado);
        
        RespuestaLogin respuesta = new RespuestaLogin();
        SqlSession conexionBD = new MyBatisUtil().getSession();
        
        if(conexionBD != null){
            try{
                estadoO = conexionBD.selectOne("empresas.estadoNombre", estadoO);
                conexionBD.commit();
                //if(resultado > 0){
                    respuesta.setError(false);
                    //respuesta.setMensaje("Bien venido " + paciente.getNombre());
                    respuesta.setIdUsuario(estadoO.getIdEstado());
                /*}else{
                    respuesta.setError(true);
                    respuesta.setMensaje("No se pudo encontrar el paciente, intentelo de nuevo m??s tarde");
                    
                }*/
            }catch(Exception e){
                respuesta.setError(true);
                respuesta.setMensaje(e.getMessage());
            }finally{
                conexionBD.close();
            }
        }else{
            respuesta.setError(true);
            respuesta.setMensaje("Servicio no disponible, intentelo m??s tarde.");
        }
        
        return respuesta;
    }
    
    @Path("buscarByRfcRepresentante/{nombreRepresentante}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Empresa obtenerTodosDatos( 
            @PathParam("nombreRepresentante") String nombreRepresentante){
        Empresa empresa = new Empresa();
        empresa.setNombreRepresentante(nombreRepresentante);
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try {
                empresa = conexionBD.selectOne("empresas.getEmpresa", empresa);
            } catch (Exception e) {
                e.printStackTrace();
            }finally{
                conexionBD.close();
            }
        }
        return empresa;
    }
    
    @Path("eliminar")
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta eliminar(@FormParam("idEmpresa") Integer idEmpresa){
        Respuesta respuestaWS = new Respuesta();
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try{
                int respuesta = conexionBD.update("empresas.eliminar", idEmpresa);
                conexionBD.commit();
                if(respuesta>0){
                    respuestaWS.setError(false);
                    respuestaWS.setMensaje("Registro de la empresa eliminado");
                }else{
                    respuestaWS.setError(false);
                    respuestaWS.setMensaje("No se pud?? eliminar el registro solicitado, la empresa a??n tiene sucursales existentes.");
                }
            }catch(Exception e){
                respuestaWS.setError(true);
                respuestaWS.setMensaje(e.getMessage());
            }finally{
                conexionBD.close();
            }
        }else{
            respuestaWS.setError(true);
            respuestaWS.setMensaje("No hay conexi??n");
        }
        return respuestaWS;
    }
    
    @Path("empresaNombre")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    
    public RespuestaLogin iniciarSesionUsuario(
            @FormParam("nombre") String nombre)
            {

        Empresa empresa = new Empresa();
        empresa.setNombre(nombre);
        
        RespuestaLogin respuesta = new RespuestaLogin();
        SqlSession conexionBD = new MyBatisUtil().getSession();
        
        if(conexionBD != null){
            try{
                empresa = conexionBD.selectOne("empresas.empresaNombre", empresa);
                conexionBD.commit();
                //if(resultado > 0){
                    respuesta.setError(false);
                    //respuesta.setMensaje("Bien venido " + paciente.getNombre());
                    respuesta.setIdUsuario(empresa.getIdEmpresa());
                /*}else{
                    respuesta.setError(true);
                    respuesta.setMensaje("No se pudo encontrar el paciente, intentelo de nuevo m??s tarde");
                    
                }*/
            }catch(Exception e){
                respuesta.setError(true);
                respuesta.setMensaje(e.getMessage());
            }finally{
                conexionBD.close();
            }
        }else{
            respuesta.setError(true);
            respuesta.setMensaje("Servicio no disponible, intentelo m??s tarde.");
        }
        
        return respuesta;
    }
    
}
