/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cuponexproyecto_contrerasjonathan_cuervofreddy;

import Util.Constantes;
import Util.Utilidades;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import modelo.ConexionServiciosWeb;
import pojos.Categoria;
import pojos.Estado;
import pojos.Promocion;
import pojos.Respuesta;
import pojos.Sucursal;
import pojos.SucursalSecundaria;
import pojos.TipoPromocion;

/**
 * FXML Controller class
 *
 * @author fredd
 */
public class FXMLRegistrarPromocionController implements Initializable {

    @FXML
    private DatePicker dpFechaInicioPromocion;
    @FXML
    private TextField tfNombrePromocion;
    @FXML
    private TextField tfDescripcion;
    @FXML
    private TextField tfPorcentajeDescuento;
    @FXML
    private TextField tfCosto;
    @FXML
    private DatePicker dpFechaFinPromocion;
    @FXML
    private ComboBox<TipoPromocion> cbTipoPromocion;
    @FXML
    private ComboBox<Categoria> cbCategoria;
    @FXML
    private ComboBox<Estado> cbEstado;
    ObservableList<Estado> listaEstado = FXCollections.observableArrayList();
    ObservableList<Categoria> listaCategoria = FXCollections.observableArrayList();
    ObservableList<TipoPromocion> listaTipoPromocion = FXCollections.observableArrayList();
    ObservableList<SucursalSecundaria> listaSucursal = FXCollections.observableArrayList();
    Integer idEstado;
    Integer idCategoria;
    Integer idTipoPromocion;
    Integer idSucursal;
    Integer idPromocion;
    @FXML
    private ComboBox<SucursalSecundaria> cbSucursal;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        obtenerCategorias();
        obtenerEstados();
        obtenerPromociones();
        obtenerSucursal();
    } 
    
    public void obtenerEstados(){
        String url = Constantes.URL_BASE+"empresas/allEstado";
        try{
            String jsonRespuesta = ConexionServiciosWeb.consumirServicioGET(url);
            Gson gson = new Gson();
            Type tipoListaCatalogo = new TypeToken<List<Estado>>(){}.getType();
            ArrayList<Estado> listaWS = gson.fromJson(jsonRespuesta, tipoListaCatalogo);
            listaEstado.addAll(listaWS);
            cbEstado.setItems(listaEstado);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void obtenerCategorias(){
        String url = Constantes.URL_BASE+"categorias/all";
        try{
            String jsonRespuesta = ConexionServiciosWeb.consumirServicioGET(url);
            Gson gson = new Gson();
            Type tipoListaCatalogo = new TypeToken<List<Categoria>>(){}.getType();
            ArrayList<Categoria> listaWS = gson.fromJson(jsonRespuesta, tipoListaCatalogo);
            listaCategoria.addAll(listaWS);
            cbCategoria.setItems(listaCategoria);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void obtenerSucursal(){
        String url = Constantes.URL_BASE+"sucursales/all";
        try{
            String jsonRespuesta = ConexionServiciosWeb.consumirServicioGET(url);
            Gson gson = new Gson();
            Type tipoListaCatalogo = new TypeToken<List<SucursalSecundaria>>(){}.getType();
            ArrayList<SucursalSecundaria> listaWS = gson.fromJson(jsonRespuesta, tipoListaCatalogo);
            listaSucursal.addAll(listaWS);
            cbSucursal.setItems(listaSucursal);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void obtenerIdCategoria(){
        String categoria = cbCategoria.getValue().toString();
        
                try{
            String url = Constantes.URL_BASE+"categorias/categoriaNombre";
            String parametros = "categoria="+categoria;
                    
            String resultado = ConexionServiciosWeb.consumirServicioPOST(url, parametros);
            Gson gson = new Gson();
            Respuesta respuesta = gson.fromJson(resultado, Respuesta.class);
            
            if(!respuesta.getError()){
                idCategoria = respuesta.getIdUsuario(); 
                System.err.println("Categoria: "+idCategoria+" estado: "+idEstado+", tipo promocion: "+idTipoPromocion);

                
            }else{
                Utilidades.mostrarAlertaSimple("Usuario verificado", "Usuario o contraseña incorrectos", Alert.AlertType.ERROR);
            }
            
        }catch(Exception e){
            Utilidades.mostrarAlertaSimple("Error de conexión",  e.getMessage(), Alert.AlertType.ERROR);
            
        }
        
    }
    public void obtenerIdSucursal(){
        String nombre = cbSucursal.getValue().toString();
        
                try{
            String url = Constantes.URL_BASE+"sucursales/sucursalNombre";
            String parametros = "nombre="+nombre;
                    
            String resultado = ConexionServiciosWeb.consumirServicioPOST(url, parametros);
            Gson gson = new Gson();
            Respuesta respuesta = gson.fromJson(resultado, Respuesta.class);
            
            if(!respuesta.getError()){
                idSucursal = respuesta.getIdUsuario(); 
                System.err.println("sucursal"+idSucursal);

                
            }else{
                Utilidades.mostrarAlertaSimple("Usuario verificado", "Usuario o contraseña incorrectos", Alert.AlertType.ERROR);
            }
            
        }catch(Exception e){
            Utilidades.mostrarAlertaSimple("Error de conexión",  e.getMessage(), Alert.AlertType.ERROR);
            
        }
        
    }
        public void obtenerIdTipoPromocion(){
        String tipoPromocion = cbTipoPromocion.getValue().toString();
        
                try{
            String url = Constantes.URL_BASE+"tipopromociones/tipoPromocionNombre";
            String parametros = "tipoPromocion="+tipoPromocion;
                    
            String resultado = ConexionServiciosWeb.consumirServicioPOST(url, parametros);
            Gson gson = new Gson();
            Respuesta respuesta = gson.fromJson(resultado, Respuesta.class);
            
            if(!respuesta.getError()){
                idTipoPromocion = respuesta.getIdUsuario(); 
                

                
            }else{
                Utilidades.mostrarAlertaSimple("Usuario verificado", "Usuario o contraseña incorrectos", Alert.AlertType.ERROR);
            }
            
        }catch(Exception e){
            Utilidades.mostrarAlertaSimple("Error de conexión",  e.getMessage(), Alert.AlertType.ERROR);
            
        }
        
    }
    public void obtenerIdEstado(){
        String estado = cbEstado.getValue().toString();
        
                try{
            String url = Constantes.URL_BASE+"empresas/estadoNombre";
            String parametros = "estado="+estado;
                    
            String resultado = ConexionServiciosWeb.consumirServicioPOST(url, parametros);
            Gson gson = new Gson();
            Respuesta respuesta = gson.fromJson(resultado, Respuesta.class);
            
            if(!respuesta.getError()){
                idEstado = respuesta.getIdUsuario(); 
                

                
            }else{
                Utilidades.mostrarAlertaSimple("Usuario verificado", "Usuario o contraseña incorrectos", Alert.AlertType.ERROR);
            }
            
        }catch(Exception e){
            Utilidades.mostrarAlertaSimple("Error de conexión",  e.getMessage(), Alert.AlertType.ERROR);
            
        }
        
    }
    public void obtenerPromociones(){
        String url = Constantes.URL_BASE+"tipopromociones/all";
        try{
            String jsonRespuesta = ConexionServiciosWeb.consumirServicioGET(url);
            Gson gson = new Gson();
            Type tipoListaCatalogo = new TypeToken<List<TipoPromocion>>(){}.getType();
            ArrayList<TipoPromocion> listaWS = gson.fromJson(jsonRespuesta, tipoListaCatalogo);
            listaTipoPromocion.addAll(listaWS);
            cbTipoPromocion.setItems(listaTipoPromocion);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    private void fnGuardarPromocion(ActionEvent event) {
        if(!cbCategoria.getSelectionModel().isEmpty() && !cbEstado.getSelectionModel().isEmpty() &&
                !cbTipoPromocion.getSelectionModel().isEmpty() && !tfNombrePromocion.getText().isEmpty() &&
                !tfDescripcion.getText().isEmpty() && !tfPorcentajeDescuento.getText().isEmpty() &&
                !tfCosto.getText().isEmpty() && !tfCosto.getText().isEmpty() && 
                !dpFechaInicioPromocion.getValue().toString().isEmpty() &&
                !dpFechaFinPromocion.getValue().toString().isEmpty()){
            String nombre = tfNombrePromocion.getText();
            String descripcion = tfDescripcion.getText();
            Double porcentajeDescuento = Double.parseDouble(tfPorcentajeDescuento.getText());
            Double costo = Double.parseDouble(tfCosto.getText());
            String fechaInicioPromocion = dpFechaInicioPromocion.getValue().toString();
            String fechaFinPromocion = dpFechaFinPromocion.getValue().toString();
            obtenerIdCategoria();
            obtenerIdTipoPromocion();
            obtenerIdEstado();
            obtenerIdSucursal();
            guardarPromocion(nombre, descripcion, porcentajeDescuento, costo, fechaInicioPromocion, fechaFinPromocion);
            
            System.out.println(fechaFinPromocion);    
        }else{
            Utilidades.mostrarAlertaSimple("Datos incompletos", "Datos incompletos", Alert.AlertType.ERROR);
        }
        
    }
    public void guardarPromocion(String nombre, String descripcion, Double porcentajeDescuento, Double costo, 
            String fechaInicioPromocion, String fechaFinPromocion ){
        try{
            String url = Constantes.URL_BASE+"promociones/registrar";
            String parametros = "nombrePromocion="+nombre+"&descripcion="+descripcion+
                    "&fechaInicioPromocion="+fechaInicioPromocion+"&fechaFinPromocion="+fechaFinPromocion+
                    "&idTipoPromocion="+idTipoPromocion+"&porcentajeDescuento="+porcentajeDescuento+
                    "&costo="+costo+"&idCategoria="+idCategoria+"&idEstado="+idEstado;
            String resultado = ConexionServiciosWeb.consumirServicioPOST(url, parametros);
            Gson gson = new Gson();
            Respuesta respuesta = gson.fromJson(resultado, Respuesta.class);
            
            if(!respuesta.getError()){
                Utilidades.mostrarAlertaSimple("Promoción guardada", "Promoción guardada", Alert.AlertType.INFORMATION);
                obteneridpromocion();

                
            }else{
                Utilidades.mostrarAlertaSimple("Sucursal guardada", "Datos incorrectos", Alert.AlertType.ERROR);
            }
            
        }catch(Exception e){
            Utilidades.mostrarAlertaSimple("Error de conexión",  e.getMessage(), Alert.AlertType.ERROR);
            
        }
        
    }
    
    public void obteneridpromocion(){
        try{
            String url = Constantes.URL_BASE+"promociones/obteneridpromocion";

                    
            String resultado = ConexionServiciosWeb.consumirServicioGET(url);
            Gson gson = new Gson();
            Promocion respuesta = gson.fromJson(resultado, Promocion.class);
            
            if(respuesta.getIdPromocion() != null ){
                idPromocion = respuesta.getIdPromocion();
                guardarSucursal();
                //Utilidades.mostrarAlertaSimple("Error de conexión", "id promocion es "+ idPromocion+"", Alert.AlertType.ERROR);
                
                
            }else{

            }
            
        }catch(Exception e){
            Utilidades.mostrarAlertaSimple("Error de conexión",  "No se encontró ninguna sucursal", Alert.AlertType.ERROR);
            
        }
    }

    public void guardarSucursal(){
        try{
            String url = Constantes.URL_BASE+"sucursalPromocion/registrarsucursalpromo";
            String parametros = "idSucursal="+idSucursal+"&idPromocion="+idPromocion;
            String resultado = ConexionServiciosWeb.consumirServicioPOST(url, parametros);
            Gson gson = new Gson();
            Respuesta respuesta = gson.fromJson(resultado, Respuesta.class);
            
            if(!respuesta.getError()){
                Utilidades.mostrarAlertaSimple("Promoción guardada", "Promoción guardada junto con su sucursal", Alert.AlertType.INFORMATION);


                
            }else{
                Utilidades.mostrarAlertaSimple("Sucursal guardada", "Datos incorrectos", Alert.AlertType.ERROR);
            }
            
        }catch(Exception e){
            Utilidades.mostrarAlertaSimple("Error de conexión",  e.getMessage(), Alert.AlertType.ERROR);
            
        }
        
    }
}
