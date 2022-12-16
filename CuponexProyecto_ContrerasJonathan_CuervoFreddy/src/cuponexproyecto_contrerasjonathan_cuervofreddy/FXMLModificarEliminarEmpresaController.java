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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import modelo.ConexionServiciosWeb;
import pojos.Empresa;
import pojos.Estado;
import pojos.Respuesta;
import pojos.Usuario;

/**
 * FXML Controller class
 *
 * @author fredd
 */
public class FXMLModificarEliminarEmpresaController implements Initializable {

    @FXML
    private ComboBox<Estado> cvEstado;
    @FXML
    private TextField tfRFC;
    @FXML
    private TextField tfSitioWeb;
    @FXML
    private TextField tfTelefono;
    @FXML
    private TextField tfCiudad;
    @FXML
    private TextField tfCp;
    @FXML
    private TextField tfDireccionNumero;
    @FXML
    private TextField tfNombre;
    @FXML
    private TextField tfNombreComercial;
    @FXML
    private TextField tfNombreRepresentante;
    @FXML
    private TextField tfCorreo;
    @FXML
    private TextField tfDireccion;
    @FXML
    private TableView<Empresa> tbEmpresa;
    @FXML
    private TableColumn clNombre;
    @FXML
    private TableColumn clNombreComercial;
    @FXML
    private TableColumn clNombreRepresentante;
    @FXML
    private TableColumn clCorreo;
    @FXML
    private TableColumn clDireccion;
    @FXML
    private TableColumn clNumeroDireccion;
    @FXML
    private TableColumn clCP;
    @FXML
    private TableColumn clCiudad;
    @FXML
    private TableColumn clTelefono;
    @FXML
    private TableColumn clSitioWeb;
    @FXML
    private TableColumn clRFC;
    @FXML
    private TableColumn idEmpresa;
    private ObservableList<Empresa> listaEmpresa; 
    Integer id;
    Integer idEstado;
    ObservableList<Estado> listaEstado = FXCollections.observableArrayList();
    @FXML
    private TableColumn clSucursales;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarTabla();
        cargarEmpresa();
        obtenerEstados();
        tfRFC.setDisable(true);
        
        // TODO
    } 
    
    private void configurarTabla(){
        listaEmpresa = FXCollections.observableArrayList();         
        clNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        clNombreComercial.setCellValueFactory(new PropertyValueFactory("nombreComercial"));
        clNombreRepresentante.setCellValueFactory(new PropertyValueFactory("nombreRepresentante"));
        clCorreo.setCellValueFactory(new PropertyValueFactory("correoEmpresa"));
        clDireccion.setCellValueFactory(new PropertyValueFactory("direccionCalle"));
        clNumeroDireccion.setCellValueFactory(new PropertyValueFactory("direccionNumero"));
        clCP.setCellValueFactory(new PropertyValueFactory("cp"));
        clCiudad.setCellValueFactory(new PropertyValueFactory("ciudad"));
        clTelefono.setCellValueFactory(new PropertyValueFactory("telefono"));
        clSitioWeb.setCellValueFactory(new PropertyValueFactory("sitioWeb"));
        clRFC.setCellValueFactory(new PropertyValueFactory("rfc"));
        idEmpresa.setCellValueFactory(new PropertyValueFactory("idEmpresa"));
        clSucursales.setCellValueFactory(new PropertyValueFactory("numSucursales"));
                
    }
    
    private void cargarEmpresa(){
        String urlWS = Constantes.URL_BASE+ "empresas/all";
        try{
            String jsonRespuesta = ConexionServiciosWeb.consumirServicioGET(urlWS);
            Gson gson = new Gson();
            Type tipoListaUsuario = new TypeToken<List<Empresa>>(){}.getType();
            List UsuarioWS = gson.fromJson(jsonRespuesta, tipoListaUsuario);
            
            listaEmpresa.addAll(UsuarioWS);
            tbEmpresa.setItems(listaEmpresa);
            
            
        }catch (Exception e){
            Utilidades.mostrarAlertaSimple("Error", "Error al cargar", Alert.AlertType.ERROR);
        }    
    }

    @FXML
    private void fnEliminar(ActionEvent event) {
        try{
            String url = Constantes.URL_BASE+"empresas/eliminar";
            String parametros = "idEmpresa="+id;
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
        configurarTabla();
        cargarEmpresa();
    }

    @FXML
    private void fnModificar(ActionEvent event) {
        if(!tfNombre.getText().isEmpty()&& !tfNombreComercial.getText().isEmpty()&&
                !tfNombreRepresentante.getText().isEmpty() && !tfCorreo.getText().isEmpty() && 
                !tfDireccion.getText().isEmpty() && !tfDireccionNumero.getText().isEmpty() && 
                !tfCp.getText().isEmpty() && !tfCiudad.getText().isEmpty() && !tfTelefono.getText().isEmpty()
                && !tfSitioWeb.getText().isEmpty() && !tfRFC.getText().isEmpty() && 
                !cvEstado.getSelectionModel().isEmpty() ){
            String nombre = tfNombre.getText();
            String nombreComercial = tfNombreComercial.getText();
            String nombreRepresentante = tfNombreRepresentante.getText();
            String correoEmpresa = tfCorreo.getText();
            String direccionCalle = tfDireccion.getText();
            Integer direccionNumero =  Integer.parseInt(tfDireccionNumero.getText());
            Integer cp = Integer.parseInt(tfCp.getText());
            String ciudad = tfCiudad.getText();
            String telefono = tfTelefono.getText();
            String sitioWeb = tfSitioWeb.getText();
            String rfc = tfRFC.getText();
            obtenerIdEstado();
            

            if(!nombre.isEmpty() && !nombreComercial.isEmpty() && !nombreRepresentante.isEmpty() 
                    && !correoEmpresa.isEmpty() && !direccionCalle.isEmpty() && direccionNumero != null && cp != null 
                    && !ciudad.isEmpty() && !telefono.isEmpty() && !sitioWeb.isEmpty() && !rfc.isEmpty() && 
                    idEstado != null){
               GuardarEmpresa(nombre, nombreComercial, nombreRepresentante, correoEmpresa, 
                    direccionCalle, direccionNumero, cp, ciudad, telefono, sitioWeb, rfc);
                 //Utilidades.mostrarAlertaSimple("Datos incompletos", "Bien", Alert.AlertType.ERROR);
            }else{
                Utilidades.mostrarAlertaSimple("Datos incompletos", "Datos incompletos", Alert.AlertType.ERROR);
            }
        }
        else{
            Utilidades.mostrarAlertaSimple("Datos incompletos", "Datos incompletos", Alert.AlertType.ERROR);

        }
    }
        public void obtenerIdEstado(){
        String estado = cvEstado.getValue().toString();
        
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
    
    public void GuardarEmpresa(String nombre,String nombreComercial, String nombreRepresentante,
         String correoEmpresa,  String direccionCalle, Integer direccionNumero, Integer cp,String ciudad, 
         String telefono, String sitioWeb,String rfc ){
        
        try{
            String url = Constantes.URL_BASE+"empresas/actualizar";
            String parametros = "idEmpresa="+id+"&nombre="+nombre+
                    "&nombreComercial="+nombreComercial+
                    "&nombreRepresentante="+nombreRepresentante+
                    "&correoEmpresa="+correoEmpresa+
                    "&direccionCalle="+direccionCalle+
                    "&direccionNumero="+direccionNumero+
                    "&cp="+cp+
                    "&ciudad="+ciudad+
                    "&telefono="+telefono+
                    "&sitioWeb="+sitioWeb+
                    "&idEstado="+
                    idEstado;
            String resultado = ConexionServiciosWeb.consumirServicioPUT(url, parametros);
            Gson gson = new Gson();
            Respuesta respuesta = gson.fromJson(resultado, Respuesta.class);
            if(!respuesta.getError()){
                Utilidades.mostrarAlertaSimple("Empresa Actualizada", "Empresa agregada al sistema"+
                        " al sistema", Alert.AlertType.INFORMATION);
                configurarTabla();
                cargarEmpresa();
               
            }else{
                Utilidades.mostrarAlertaSimple("Datos erroneos", respuesta.getMensaje(), Alert.AlertType.ERROR);
            }
            
        }catch(Exception e){
            Utilidades.mostrarAlertaSimple("Error de conexión",  "Datos erroneos o no se puede conectar con la base de datos", Alert.AlertType.ERROR);
            
        }
        
    }
    
    public void obtenerEstados(){
        String url = Constantes.URL_BASE+"empresas/allEstado";
        try{
            String jsonRespuesta = ConexionServiciosWeb.consumirServicioGET(url);
            Gson gson = new Gson();
            Type tipoListaCatalogo = new TypeToken<List<Estado>>(){}.getType();
            ArrayList<Estado> listaWS = gson.fromJson(jsonRespuesta, tipoListaCatalogo);
            listaEstado.addAll(listaWS);
            cvEstado.setItems(listaEstado);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    private void fnSeleccionar(ActionEvent event) {
        int filaSeleccionada = tbEmpresa.getSelectionModel().getSelectedIndex();
        if(filaSeleccionada >= 0){
                String nombre = listaEmpresa.get(filaSeleccionada).getNombre();
                String nombreRepresentante = listaEmpresa.get(filaSeleccionada).getNombreRepresentante();
                String nombreComercial = listaEmpresa.get(filaSeleccionada).getNombreComercial();
                String correoEmpresa = listaEmpresa.get(filaSeleccionada).getCorreoEmpresa();
                String direccionCalle = listaEmpresa.get(filaSeleccionada).getDireccionCalle();
                Integer direccionNumero = listaEmpresa.get(filaSeleccionada).getDireccionNumero();
                Integer cp = listaEmpresa.get(filaSeleccionada).getCp();
                String ciudad = listaEmpresa.get(filaSeleccionada).getCiudad();
                String telefono = listaEmpresa.get(filaSeleccionada).getTelefono();
                String sitioWeb =listaEmpresa.get(filaSeleccionada).getSitioWeb();
                String rfc = listaEmpresa.get(filaSeleccionada).getRfc();
                id = listaEmpresa.get(filaSeleccionada).getIdEmpresa();
                idEstado = listaEmpresa.get(filaSeleccionada).getIdEstado();
                
                System.out.println("El id empresa es "+ id +" y el idEstado es "+ idEstado);
                

                tfNombre.setText(nombre);
                tfNombreRepresentante.setText(nombreRepresentante);
                tfNombreComercial.setText(nombreComercial);
                tfCorreo.setText(correoEmpresa);
                tfDireccion.setText(direccionCalle);
                tfDireccionNumero.setText(direccionNumero+"");
                tfCp.setText(cp+"");
                tfCiudad.setText(ciudad);
                tfTelefono.setText(telefono);
                tfSitioWeb.setText(sitioWeb);
                tfRFC.setText(rfc);
                

        }else{
            Utilidades.mostrarAlertaSimple("Selecciona un registro", "Debes seleccionar un administrador para su modificación"
                    , Alert.AlertType.WARNING);
        }
    }

    @FXML
    private void fnSeleccion(MouseEvent event) {
    }
    
}
