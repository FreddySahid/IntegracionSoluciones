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
import modelo.ConexionServiciosWeb;
import pojos.Empresa;
import pojos.EmpresaSegunda;
import pojos.Respuesta;
import pojos.Sucursal;

/**
 * FXML Controller class
 *
 * @author fredd
 */
public class FXMLModificarEliminarSucursalController implements Initializable {

    @FXML
    private TableColumn clIdSucursal;
    @FXML
    private TableColumn clNombre;
    @FXML
    private TableColumn clDireccion;
    @FXML
    private TableColumn clNumero;
    @FXML
    private TableColumn clCP;
    @FXML
    private TableColumn clColonia;
    @FXML
    private TableColumn clCiudad;
    @FXML
    private TableColumn clTelefono;
    @FXML
    private TableColumn clLatitud;
    @FXML
    private TableColumn clLongitud;
    @FXML
    private TableColumn clEncargado;
    @FXML
    private TableColumn clEmpresa;
    @FXML
    private ComboBox<EmpresaSegunda> cvEmpresa;
    @FXML
    private TextField tfCiudad;
    @FXML
    private TextField tfTelefono;
    @FXML
    private TextField tfLatitud;
    @FXML
    private TextField tfLongitud;
    @FXML
    private TextField tfEncargado;
    @FXML
    private TextField tfColonia;
    @FXML
    private TextField tfCP;
    @FXML
    private TextField tfNumero;
    @FXML
    private TextField tfDireccionCalle;
    @FXML
    private TextField tfNombre;
    @FXML
    private TableView<Sucursal> cbSucursal;
    Integer idEmpresa;
    Integer idSucursal;

    //Tabla
    private ObservableList<Sucursal> listaSucursales; 
    
    //CB
    ObservableList<EmpresaSegunda> listaEmpresa = FXCollections.observableArrayList(); 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        obtenerEmpresa();
        
    }  
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void obtenerEmpresa(){
        String url = Constantes.URL_BASE+"empresas/all";
        try{
            String jsonRespuesta = ConexionServiciosWeb.consumirServicioGET(url);
            Gson gson = new Gson();
            Type tipoListaCatalogo = new TypeToken<List<EmpresaSegunda>>(){}.getType();
            ArrayList<EmpresaSegunda> listaWS = gson.fromJson(jsonRespuesta, tipoListaCatalogo);
            listaEmpresa.addAll(listaWS);
            cvEmpresa.setItems(listaEmpresa);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void configurarTabla(){
        listaSucursales = FXCollections.observableArrayList();   
        clIdSucursal.setCellValueFactory(new PropertyValueFactory("idSucursal"));
        clNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        clDireccion.setCellValueFactory(new PropertyValueFactory("direccionCalle"));
        clNumero.setCellValueFactory(new PropertyValueFactory("direccionNumero"));
        clCP.setCellValueFactory(new PropertyValueFactory("cp"));
        clColonia.setCellValueFactory(new PropertyValueFactory("colonia"));
        clCiudad.setCellValueFactory(new PropertyValueFactory("ciudad"));
        clTelefono.setCellValueFactory(new PropertyValueFactory("telefono"));
        clLatitud.setCellValueFactory(new PropertyValueFactory("latitud"));
        clLongitud.setCellValueFactory(new PropertyValueFactory("longitud"));
        clEncargado.setCellValueFactory(new PropertyValueFactory("nombreEncargado"));
        clEmpresa.setCellValueFactory(new PropertyValueFactory("empresa"));              
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////   
    private void cargarSucursal(){
        String urlWS = Constantes.URL_BASE+ "sucursales/SucursalEmpresa/"+idEmpresa;
        try{
            String jsonRespuesta = ConexionServiciosWeb.consumirServicioGET(urlWS);
            Gson gson = new Gson();
            Type tipoListaUsuario = new TypeToken<List<Sucursal>>(){}.getType();
            List UsuarioWS = gson.fromJson(jsonRespuesta, tipoListaUsuario);
            
            listaSucursales.addAll(UsuarioWS);
            cbSucursal.setItems(listaSucursales);
            
            
        }catch (Exception e){
            Utilidades.mostrarAlertaSimple("Error", "Error al cargar", Alert.AlertType.ERROR);
        }    
    }
    
    
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////
     public void obtenerIdEmpresa(){
        String nombre = cvEmpresa.getValue().toString();
        
                try{
            String url = Constantes.URL_BASE+"empresas/empresaNombre";
            String parametros = "nombre="+nombre;
                    
            String resultado = ConexionServiciosWeb.consumirServicioPOST(url, parametros);
            Gson gson = new Gson();
            Respuesta respuesta = gson.fromJson(resultado, Respuesta.class);
            
            if(!respuesta.getError()){
                idEmpresa = respuesta.getIdUsuario(); 
                //System.err.println(idEmpresa);

                
            }else{
                Utilidades.mostrarAlertaSimple("Usuario verificado", "Usuario o contraseña incorrectos", Alert.AlertType.ERROR);
            }
            
        }catch(Exception e){
            Utilidades.mostrarAlertaSimple("Error de conexión",  e.getMessage(), Alert.AlertType.ERROR);
            
        }
        
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void modificarSucursalBD(String nombre, String direccionCalle, Integer direccionNumero,
            Integer cp, String colonia, String ciudad, String telefono, Double latitud, Double longitud, 
            String nombreEncargado){
                try{
            String url = Constantes.URL_BASE+"sucursales/actualizar";
            String parametros = "idSucursal="+idSucursal+"&nombre="+nombre+"&direccionCalle="+direccionCalle+
                    "&direccionNumero="+direccionNumero+
                    "&cp="+cp+"&colonia="+colonia+"&ciudad="+ciudad+"&telefono="+telefono+
                    "&latitud="+latitud+"&longitud="+longitud+"&nombreEncargado="+nombreEncargado+
                    "&idEmpresa="+idEmpresa;
            String resultado = ConexionServiciosWeb.consumirServicioPUT(url, parametros);
            Gson gson = new Gson();
            Respuesta respuesta = gson.fromJson(resultado, Respuesta.class);
            
            if(!respuesta.getError()){
                Utilidades.mostrarAlertaSimple("Sucursal guardada", "Sucursal Guarda"+
                        " al sistema", Alert.AlertType.INFORMATION);
                configurarTabla();
                cargarSucursal();

                
            }else{
                Utilidades.mostrarAlertaSimple("Sucursal guardada", "Datos incorrectos", Alert.AlertType.ERROR);
            }
            
        }catch(Exception e){
            Utilidades.mostrarAlertaSimple("Error de conexión",  e.getMessage(), Alert.AlertType.ERROR);
            
        }
        
    }
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////

    

    
    @FXML
    private void fnModificar(ActionEvent event) {
        if(!cvEmpresa.getSelectionModel().isEmpty() && !tfNombre.getText().isEmpty() &&
                !tfDireccionCalle.getText().isEmpty() && !tfNumero.getText().isEmpty() &&
                !tfCP.getText().isEmpty() && !tfColonia.getText().isEmpty() &&
                !tfCiudad.getText().isEmpty() && !tfTelefono.getText().isEmpty() &&
                !tfLatitud.getText().isEmpty() && !tfLongitud.getText().isEmpty() && 
                !tfEncargado.getText().isEmpty()){
                    
            String nombre = tfNombre.getText();
            String direccionCalle = tfDireccionCalle.getText();
            Integer direccionNumero = Integer.parseInt(tfNumero.getText());
            Integer cp = Integer.parseInt(tfCP.getText());
            String colonia = tfColonia.getText();
            String ciudad = tfCiudad.getText();
            String telefono = tfTelefono.getText();
            Double latitud = Double.parseDouble(tfLatitud.getText());
            Double longitud = Double.parseDouble(tfLongitud.getText());
            String nombreEncargado = tfEncargado.getText();
            obtenerIdEmpresa();
            modificarSucursalBD(nombre, direccionCalle, direccionNumero, cp, colonia, ciudad, telefono, latitud
                    , longitud, nombreEncargado);

        }
        else{
            Utilidades.mostrarAlertaSimple("Datos incompletos", "Datos incompletos", Alert.AlertType.ERROR);

        }
    }

    @FXML
    private void fnEliminar(ActionEvent event) {
        try{
            String url = Constantes.URL_BASE+"sucursales/eliminar";
            String parametros = "idSucursal="+idSucursal;
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
        cargarSucursal();
    }

    @FXML
    private void fnSeleccionar(ActionEvent event) {
        
        int filaSeleccionada = cbSucursal.getSelectionModel().getSelectedIndex();
        if(filaSeleccionada >= 0){
                    
                idSucursal = listaSucursales.get(filaSeleccionada).getIdSucursal();
                String nombre = listaSucursales.get(filaSeleccionada).getNombre();
                String direccion = listaSucursales.get(filaSeleccionada).getDireccionCalle();
                Integer numero = listaSucursales.get(filaSeleccionada).getDireccionNumero();
                Integer cp = listaSucursales.get(filaSeleccionada).getCp();
                String colonia =listaSucursales.get(filaSeleccionada).getColonia();
                String ciudad = listaSucursales.get(filaSeleccionada).getCiudad();
                String telefono = listaSucursales.get(filaSeleccionada).getTelefono();
                Double latitud = listaSucursales.get(filaSeleccionada).getLatitud();
                Double longitud = listaSucursales.get(filaSeleccionada).getLongitud();
                String encargado = listaSucursales.get(filaSeleccionada).getNombreEncargado();

                tfNombre.setText(nombre);
                tfDireccionCalle.setText(direccion);
                tfNumero.setText(numero+"");
                tfCP.setText(cp+"");
                tfColonia.setText(colonia);
                tfCiudad.setText(ciudad);
                tfTelefono.setText(telefono);
                tfLatitud.setText(latitud+"");
                tfLongitud.setText(longitud+"");
                tfEncargado.setText(encargado);

        }else{
            Utilidades.mostrarAlertaSimple("Selecciona un registro", "Debes seleccionar un administrador para su modificación"
                    , Alert.AlertType.WARNING);
        }
    }

    @FXML
    private void fnBuscar(ActionEvent event) {
        
        obtenerIdEmpresa();
        configurarTabla();
        cargarSucursal();
        
        
        
    }
    
}
