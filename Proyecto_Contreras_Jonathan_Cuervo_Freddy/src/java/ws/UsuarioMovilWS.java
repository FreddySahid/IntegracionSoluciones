
package ws;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import mybatis.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import pojos.Respuesta;
import pojos.RespuestaLogin;
import pojos.UsuarioMovil;

@Path("movil")
public class UsuarioMovilWS {

    @Context
    private UriInfo context;

    public UsuarioMovilWS() {
    }

    @Path("inicioSesion")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public RespuestaLogin iniciarSesionMovil(@FormParam("correo") String correo, @FormParam("password") String password){
        UsuarioMovil usuarioM = new UsuarioMovil();
        usuarioM.setCorreo(correo);
        usuarioM.setPassword(password);
        
        RespuestaLogin respuesta = new RespuestaLogin();
        SqlSession conexionBD = new MyBatisUtil().getSession();
        
        if (conexionBD != null){
            try {
                usuarioM = conexionBD.selectOne("usuariosmovil.buscarUsuario", usuarioM);
                conexionBD.commit();
                respuesta.setError(false);
                respuesta.setNombre(usuarioM.getNombre());
                respuesta.setApellidoParterno(usuarioM.getApellidoPaterno());
                respuesta.setApellidoMaterno(usuarioM.getApellidoMaterno());
                respuesta.setIdUsuario(usuarioM.getIdUsuario());
                respuesta.setMensaje("Usuario aceptado");
            } catch (Exception e) {
                respuesta.setError(true);
                respuesta.setMensaje("Usuario no encontrado");
            }finally{
                conexionBD.close();
            }
        } else {
            respuesta.setError(true);
            respuesta.setMensaje("Servicio no disponible, intentelo más tarde");
        }
        return respuesta;
    }
    
    @Path("registrarUserMovil")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta registrarUserMovil(@FormParam("nombre") String nombre, 
            @FormParam("apellidoPaterno") String apellidoPaterno, @FormParam("apellidoMaterno") String apellidoMaterno,
            @FormParam("telefono") String telefono, @FormParam("correo") String correo, 
            @FormParam("direccionCalle") String direccionCalle, @FormParam("direccionNumero") String direccionNumero,
            @FormParam("fechaNacimiento") String fechaNacimiento, @FormParam("password") String password){
        
        Integer dnumero = Integer.parseInt(direccionNumero);
        
        UsuarioMovil usuarioM = new UsuarioMovil();
        usuarioM.setNombre(nombre);
        usuarioM.setApellidoPaterno(apellidoPaterno);
        usuarioM.setApellidoMaterno(apellidoMaterno);
        usuarioM.setTelefono(telefono);
        usuarioM.setCorreo(correo);
        usuarioM.setDireccionCalle(direccionCalle);
        usuarioM.setDireccionNumero(dnumero);
        usuarioM.setFechaNacimiento(fechaNacimiento);
        usuarioM.setPassword(password);
        
        Respuesta respuestaWS = new Respuesta();
        SqlSession conexionBD = new MyBatisUtil().getSession();
        
        if(conexionBD != null){
            try{
                int resultado = conexionBD.insert("usuariosmovil.registrarUserMovil", usuarioM);
                conexionBD.commit();
                if(resultado > 0){
                    respuestaWS.setError(false);
                    respuestaWS.setMensaje("Proceso de registro finalizo con éxito");
                }else{
                    respuestaWS.setError(true);
                    respuestaWS.setMensaje("Proceso de registro erroneo, intentelo de nuevo más tarde");
                }
            }catch(Exception e){
                respuestaWS.setError(true);
                respuestaWS.setMensaje("Proceso de registro interrumpido, intentelo más tarde");
            }finally{
                conexionBD.close();
            }
        }else{
            respuestaWS.setError(true);
            respuestaWS.setMensaje("Servicio no disponible, intentelo más tarde.");
        }
        return respuestaWS;
    }
    
    @Path("actualizarDatos")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta actualizarDatos(@FormParam("idUsuario") String idUsuario, 
            @FormParam("nombre") String nombre, 
            @FormParam("apellidoPaterno") String apellidoPaterno, @FormParam("apellidoMaterno") String apellidoMaterno,
            @FormParam("telefono") String telefono,
            @FormParam("direccionCalle") String direccionCalle, @FormParam("direccionNumero") String direccionNumero,
            @FormParam("fechaNacimiento") String fechaNacimiento, @FormParam("password") String password){
        
        Integer dnumero = Integer.parseInt(direccionNumero);
        Integer dusuario = Integer.parseInt(idUsuario);
        
        UsuarioMovil usuarioM = new UsuarioMovil();
        usuarioM.setIdUsuario(dusuario);
        usuarioM.setNombre(nombre);
        usuarioM.setApellidoPaterno(apellidoPaterno);
        usuarioM.setApellidoMaterno(apellidoMaterno);
        usuarioM.setTelefono(telefono);
        usuarioM.setDireccionCalle(direccionCalle);
        usuarioM.setDireccionNumero(dnumero);
        usuarioM.setFechaNacimiento(fechaNacimiento);
        usuarioM.setPassword(password);
        
        Respuesta respuestaWS = new Respuesta();
        SqlSession conexionBD = new MyBatisUtil().getSession();
        if(conexionBD != null){
            try{
                int resultado = conexionBD.update("usuariosmovil.actualizarDatos", usuarioM);
                conexionBD.commit();
                if(resultado > 0){
                    respuestaWS.setError(false);
                    respuestaWS.setMensaje("Información del usuario modificada con éxito");
                }else{
                    respuestaWS.setError(true);
                    respuestaWS.setMensaje("No se pudo actualizar el usuario, intentelo de nuevo más tarde");
                }
            }catch(Exception e){
                respuestaWS.setError(true);
                respuestaWS.setMensaje("No se pudo actualizar el usuario, intentelo de nuevo más tarde");
            }finally{
                conexionBD.close();
            }
        }else{
            respuestaWS.setError(true);
            respuestaWS.setMensaje("Servicio no disponible, intentelo más tarde.");
        }
        return respuestaWS;
    }
    
    @Path("obtenerDatos/{idUsuario}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public UsuarioMovil obtenerDatos(@PathParam("idUsuario") Integer idUsuario){
        UsuarioMovil usuarioM = null;
        SqlSession conexionBD = MyBatisUtil.getSession();
        if(conexionBD != null){
            try {
                usuarioM = conexionBD.selectOne("usuariosmovil.obtenerDatos", idUsuario);
            } catch (Exception e) {
                e.printStackTrace();
            } finally{
                conexionBD.close();
            }
        }
        return usuarioM;
    }
}
