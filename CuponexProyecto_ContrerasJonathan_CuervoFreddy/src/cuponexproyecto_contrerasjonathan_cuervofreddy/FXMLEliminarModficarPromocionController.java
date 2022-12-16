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
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
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
public class FXMLEliminarModficarPromocionController implements Initializable {

    @FXML
    private ComboBox<Estado> cbEstado;
    @FXML
    private ComboBox<Categoria> cbCategoria;
    @FXML
    private ComboBox<TipoPromocion> cbTipoPromocion;
    @FXML
    private DatePicker dpFechaFinPromocion;
    @FXML
    private TextField tfCosto;
    @FXML
    private TextField tfPorcentajeDescuento;
    @FXML
    private TextField tfDescripcion;
    @FXML
    private TextField tfNombrePromocion;
    @FXML
    private DatePicker dpFechaInicioPromocion;
    @FXML
    private TableView<Promocion> tbPromocion;
    @FXML
    private TableColumn clIdPromocion;
    @FXML
    private TableColumn  clNombrePromocion;
    @FXML
    private TableColumn  clDescripcion;
    @FXML
    private TableColumn  clInicio;
    @FXML
    private TableColumn  clFin;
    @FXML
    private TableColumn  clTipoPromocion;
    @FXML
    private TableColumn  clDescuento;
    @FXML
    private TableColumn  clCosto;
    @FXML
    private TableColumn  clCategoria;
    @FXML
    private TableColumn  clEstado;
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
    private ObservableList<Promocion> listaPromociones;
    @FXML
    private TableColumn clIdTipoPromocion;
    @FXML
    private TableColumn clidCategoria;
    @FXML
    private TableColumn clIdEstado;
    @FXML
    private ImageView imgFoto;
    @FXML
    private Button btnSeleccionarFoto;
      String imgB;
    private byte [] byteImage = null;
    private String imageType = "";
    Integer cont = 0;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        obtenerCategorias();
        obtenerEstados();
        obtenerPromociones();
        obtenerSucursal();
        configurarTabla();
        cargarPromocion();
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
        private void configurarTabla(){
        listaPromociones = FXCollections.observableArrayList();  
        clIdPromocion.setCellValueFactory(new PropertyValueFactory("idPromocion"));
        clNombrePromocion.setCellValueFactory(new PropertyValueFactory("nombrePromocion"));
        clDescripcion.setCellValueFactory(new PropertyValueFactory("descripcion"));
        clInicio.setCellValueFactory(new PropertyValueFactory("fechaInicioPromocion"));
        clFin.setCellValueFactory(new PropertyValueFactory("fechaFinPromocion"));
        clTipoPromocion.setCellValueFactory(new PropertyValueFactory("tipoPromocion"));
        clDescuento.setCellValueFactory(new PropertyValueFactory("porcentajeDescuento"));
        clCosto.setCellValueFactory(new PropertyValueFactory("costo"));
        clCategoria.setCellValueFactory(new PropertyValueFactory("categoria"));
        clEstado.setCellValueFactory(new PropertyValueFactory("estado"));
        clIdEstado.setCellValueFactory(new PropertyValueFactory("idEstado"));
        clIdTipoPromocion.setCellValueFactory(new PropertyValueFactory("idTipoPromocion"));
        clidCategoria.setCellValueFactory(new PropertyValueFactory("idCategoria"));
              
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////   
    private void cargarPromocion(){
        String urlWS = Constantes.URL_BASE+ "promociones/all";
        try{
            String jsonRespuesta = ConexionServiciosWeb.consumirServicioGET(urlWS);
            Gson gson = new Gson();
            Type tipoListaUsuario = new TypeToken<List<Promocion>>(){}.getType();
            List UsuarioWS = gson.fromJson(jsonRespuesta, tipoListaUsuario);
            
            listaPromociones.addAll(UsuarioWS);
            tbPromocion.setItems(listaPromociones);
            
            
        }catch (Exception e){
            Utilidades.mostrarAlertaSimple("Error", "Error al cargar", Alert.AlertType.ERROR);
        }    
    }

    @FXML
    private void fnModificarPromocion(ActionEvent event) {
        if(!tfNombrePromocion.getText().isEmpty() && !tfDescripcion.getText().isEmpty() &&
                !tfPorcentajeDescuento.getText().isEmpty() &&!tfCosto.getText().isEmpty()){
            if(!cbEstado.getSelectionModel().isEmpty()){
                    obtenerIdEstado();
                }
            if(!cbCategoria.getSelectionModel().isEmpty()){
                    obtenerIdCategoria();
                }
            if(!cbTipoPromocion.getSelectionModel().isEmpty()){
                    obtenerIdTipoPromocion();
                }
            if(!cbSucursal.getSelectionModel().isEmpty()){
                    obtenerIdSucursal();
                }
            
            String nombre = tfNombrePromocion.getText();
            String descripcion = tfDescripcion.getText();
            Double porcentajeDescuento = Double.parseDouble(tfPorcentajeDescuento.getText());
            Double costo = Double.parseDouble(tfCosto.getText());
            String fechaInicioPromocion = dpFechaInicioPromocion.getValue().toString();
            String fechaFinPromocion = dpFechaFinPromocion.getValue().toString();
            guardarPromocion(nombre, descripcion, porcentajeDescuento, costo, fechaInicioPromocion, fechaFinPromocion);
            if(cont != 0){
                subirFotoBD(this.byteImage);
                
            } 
                 
             
            
            
        }

    }
        public void guardarPromocion(String nombre, String descripcion, Double porcentajeDescuento, Double costo, 
            String fechaInicioPromocion, String fechaFinPromocion ){
        try{
            String url = Constantes.URL_BASE+"promociones/actualizar";
            String parametros = "nombrePromocion="+nombre+"&descripcion="+descripcion+
                    "&fechaInicioPromocion="+fechaInicioPromocion+"&fechaFinPromocion="+fechaFinPromocion+
                    "&idTipoPromocion="+idTipoPromocion+"&porcentajeDescuento="+porcentajeDescuento+
                    "&costo="+costo+"&idCategoria="+idCategoria+"&idEstado="+idEstado+"&idPromocion="+idPromocion;
            String resultado = ConexionServiciosWeb.consumirServicioPUT(url, parametros);
            Gson gson = new Gson();
            Respuesta respuesta = gson.fromJson(resultado, Respuesta.class);
            
            if(!respuesta.getError()){
                Utilidades.mostrarAlertaSimple("Promoción guardada", "Promoción guardada", Alert.AlertType.INFORMATION);
                guardarSucursal();
                configurarTabla();
                cargarPromocion();
                

                
            }else{
                Utilidades.mostrarAlertaSimple("Sucursal guardada", "Datos incorrectos", Alert.AlertType.ERROR);
            }
            
        }catch(Exception e){
            Utilidades.mostrarAlertaSimple("Error de conexión",  e.getMessage(), Alert.AlertType.ERROR);
            
        }
        
    }
    public void guardarSucursal(){
        if(idSucursal != null){
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

    @FXML
    private void fnEliminarPromocion(ActionEvent event) {
        eliminarpromosucursal();
        eliminarpromo();
        configurarTabla();
        cargarPromocion();
        
    }
    
    public void eliminarpromosucursal(){
        if(idSucursal != null){
        try{
            String url = Constantes.URL_BASE+"sucursalPromocion/eliminar";
            String parametros = "idPromocion="+idPromocion+"&idSucursal="+idSucursal;
            String resultado = ConexionServiciosWeb.consumirServicioDELETE(url, parametros);
            Gson gson = new Gson();
            Respuesta respuesta = gson.fromJson(resultado, Respuesta.class);
            
            if(!respuesta.getError()){
                Utilidades.mostrarAlertaSimple("Empresa Eliminada", "Empresa Eliminada "+
                        " al sistema", Alert.AlertType.INFORMATION);                
            }else{
                Utilidades.mostrarAlertaSimple("Error al eliminar", "Esta empresa aún tiene sucursales", Alert.AlertType.ERROR);
            }
            
        }catch(Exception e){
            Utilidades.mostrarAlertaSimple("Error de conexión",  e.getMessage(), Alert.AlertType.ERROR);
            
        }
        }

    }
    public void eliminarpromo(){
        try{
            String url = Constantes.URL_BASE+"promociones/eliminar";
            String parametros = "idPromocion="+idPromocion;
            String resultado = ConexionServiciosWeb.consumirServicioDELETE(url, parametros);
            Gson gson = new Gson();
            Respuesta respuesta = gson.fromJson(resultado, Respuesta.class);
            
            if(!respuesta.getError()){
                Utilidades.mostrarAlertaSimple("Empresa Eliminada", "Promocion Eliminada "+
                        " al sistema", Alert.AlertType.INFORMATION);                
            }else{
                Utilidades.mostrarAlertaSimple("Error al eliminar", "Esta empresa aún tiene sucursales", Alert.AlertType.ERROR);
            }
            
        }catch(Exception e){
            Utilidades.mostrarAlertaSimple("Error de conexión",  e.getMessage(), Alert.AlertType.ERROR);
            
        }
        
    }

    @FXML
    private void fnSeleccionarPromocion(ActionEvent event) {
        int filaSeleccionada = tbPromocion.getSelectionModel().getSelectedIndex();
        if(filaSeleccionada >= 0){
                
                idPromocion = listaPromociones.get(filaSeleccionada).getIdPromocion();
                String nombrePromocion = listaPromociones.get(filaSeleccionada). getNombrePromocion();
                String descripocion = listaPromociones.get(filaSeleccionada).getDescripcion();
                String fechaInicioPromocion = listaPromociones.get(filaSeleccionada).getFechaInicioPromocion();
                String fechaFinPromocion = listaPromociones.get(filaSeleccionada).getFechaFinPromocion();
                Double porcentajeDescuent = listaPromociones.get(filaSeleccionada).getPorcentajeDescuento();
                Double costo = listaPromociones.get(filaSeleccionada).getCosto();
                idCategoria = listaPromociones.get(filaSeleccionada).getIdCategoria();
                idTipoPromocion = listaPromociones.get(filaSeleccionada).getIdTipoPromocion();
                idEstado = listaPromociones.get(filaSeleccionada).getIdEstado(); 
                
                tfNombrePromocion.setText(nombrePromocion);
                tfDescripcion.setText(nombrePromocion);
                String[] parts = fechaInicioPromocion.split("-");
                String part1 = parts[0]; // 123
                String part2 = parts[1];
                String part3 = parts[2];// 654321
                System.err.println("parte 1"+ part1+" parte2 "+part2+" parte3 "+part3);
                dpFechaInicioPromocion.setValue(LocalDate.of(Integer.parseInt(part3), Integer.parseInt(part2), Integer.parseInt(part1)));
                String[] partes = fechaFinPromocion.split("-");
                String parte1 = partes[0]; // 123
                String parte2 = partes[1];
                String parte3 = partes[2];
                dpFechaFinPromocion.setValue(LocalDate.of(Integer.parseInt(parte3), Integer.parseInt(parte2), Integer.parseInt(parte1)));
                tfPorcentajeDescuento.setText(porcentajeDescuent+"");
                tfCosto.setText(costo+"");
                                
                
                
                


        }else{
            Utilidades.mostrarAlertaSimple("Selecciona un registro", "Debes seleccionar un administrador para su modificación"
                    , Alert.AlertType.WARNING);
        }
    }

    @FXML
    private void fnDesactivar(ActionEvent event) {
        desactivar();
        configurarTabla();
        cargarPromocion();
        
    }
    public void desactivar(){
        
        try{
            String url = Constantes.URL_BASE+"promociones/desactivar";
            String parametros = "idPromocion="+idPromocion;
            String resultado = ConexionServiciosWeb.consumirServicioDELETE(url, parametros);
            Gson gson = new Gson();
            Respuesta respuesta = gson.fromJson(resultado, Respuesta.class);
            
            if(!respuesta.getError()){
                Utilidades.mostrarAlertaSimple("Empresa Eliminada", "Promocion Eliminada "+
                        " al sistema", Alert.AlertType.INFORMATION);                
            }else{
                Utilidades.mostrarAlertaSimple("Error al eliminar", "Esta empresa aún tiene sucursales", Alert.AlertType.ERROR);
            }
            
        }catch(Exception e){
            Utilidades.mostrarAlertaSimple("Error de conexión",  e.getMessage(), Alert.AlertType.ERROR);
            
        }
    }

    @FXML
    private void fnSeleccionarFoto(ActionEvent event)throws FileNotFoundException, IOException {
        cont = cont +1;
        FileChooser fileChoose = new FileChooser();
        File file = fileChoose.showOpenDialog(new Stage());
        if (file!=null){
            try{
                
            
            String imageName = file.getName();
            String[] imageNameArr = imageName.split("\\.");
            
            this.imageType = imageNameArr[imageNameArr.length-1].toLowerCase();
            if (!this.imageType.equals("png") && !this.imageType.equals("jpg")){
                Utilidades.mostrarAlertaSimple("Error", "Solo archivos jpg y png", Alert.AlertType.NONE);
                return;
            }
            
            if(file.length()>1000000){
                Utilidades.mostrarAlertaSimple("Error", "Imagen muy pesada", Alert.AlertType.NONE);
                return;
            }
            
            BufferedImage bImage = ImageIO.read(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(bImage, imageType, bos);
            this.byteImage = bos.toByteArray();
         }catch(Exception e){
             
         }   
        }
    }
    
    private void subirFotoBD(byte[] fotoPromocion){
        
        try{
            
            String urlServicio = Constantes.URL_BASE+"promociones/subirFoto/"+idPromocion;

            String parametros = "idPromocion=" + idPromocion +
                                "&foto=" + fotoPromocion;       
            String resultadoWS = ConexionServiciosWeb.peticionServicioPOSTImagen(urlServicio, this.byteImage);
            Gson gson = new Gson() ;
            Respuesta respuesta = gson.fromJson(resultadoWS, Respuesta.class);
            
            if (!respuesta.getError()) {                
                Utilidades.mostrarAlertaSimple("Promoción actualizada", 
                        "Promoción actualizada correctamente "
                        , Alert.AlertType.INFORMATION);
                //Stage stage = (Stage) this.btnSeleccionarFoto.getScene().getWindow();
                //stage.close();
            }else{
                Utilidades.mostrarAlertaSimple("Error al editar la promoción", respuesta.getMensaje(),
                        Alert.AlertType.ERROR);
            }
                                    
        }catch(Exception e){
            Utilidades.mostrarAlertaSimple("Error de conexión", e.getMessage(), Alert.AlertType.ERROR);            
        }
                      
    }  
    
}
