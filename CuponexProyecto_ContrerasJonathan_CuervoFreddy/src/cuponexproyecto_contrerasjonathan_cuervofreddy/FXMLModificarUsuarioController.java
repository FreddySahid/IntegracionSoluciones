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
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import modelo.ConexionServiciosWeb;
import pojos.Respuesta;
import pojos.Usuario;

/**
 * FXML Controller class
 *
 * @author fredd
 */
public class FXMLModificarUsuarioController implements Initializable {

    @FXML
    private TextField tfApellidoMaterno;
    @FXML
    private TextField tfApellidoPaterno;
    @FXML
    private TextField tfNombre;
    @FXML
    private PasswordField pfPassword;
    @FXML
    private TextField tfBuscar;
    @FXML
    private TableColumn clNombre;
    @FXML
    private TableColumn clApellidoP;
    @FXML
    private TableColumn clApellidoM;
    @FXML
    private TableColumn clCorreo;
    @FXML
    private TableColumn clPassword;
    @FXML
    private TableView<Usuario> tvUsuario;
    private ObservableList<Usuario> listaUsuarios; 
    @FXML
    private TextField tfCorreo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        tfCorreo.setDisable(true);
        configurarTabla();
        cargarUsuario();
    }  
    
    private void configurarTabla(){
        listaUsuarios = FXCollections.observableArrayList();         
        clNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        clApellidoP.setCellValueFactory(new PropertyValueFactory("apellidoPaterno"));
        clApellidoM.setCellValueFactory(new PropertyValueFactory("apellidoMaterno"));
        clCorreo.setCellValueFactory(new PropertyValueFactory("correo"));
        clPassword.setCellValueFactory(new PropertyValueFactory("password"));
        
    }
    
    private void cargarUsuario(){
        String urlWS = Constantes.URL_BASE+ "usuarios/all";
        try{
            String jsonRespuesta = ConexionServiciosWeb.consumirServicioGET(urlWS);
            Gson gson = new Gson();
            Type tipoListaUsuario = new TypeToken<List<Usuario>>(){}.getType();
            List UsuarioWS = gson.fromJson(jsonRespuesta, tipoListaUsuario);
            
            listaUsuarios.addAll(UsuarioWS);
            tvUsuario.setItems(listaUsuarios);
            
            
        }catch (Exception e){
            Utilidades.mostrarAlertaSimple("Error", "Error al cargar", Alert.AlertType.ERROR);
        }    
    }
    
        
    @FXML
    private void ModificarUsuario(ActionEvent event) {
                
        String nombre = tfNombre.getText();
        String apellidoPaterno = tfApellidoPaterno.getText();
        String apellidoMaterno = tfApellidoMaterno.getText();
        String correo = tfCorreo.getText();
        String password= pfPassword.getText();
        
        if (!nombre.isEmpty() && !apellidoPaterno.isEmpty() && !apellidoMaterno.isEmpty()
                &&!correo.isEmpty()  && !password.isEmpty()){
            //verificarInicioSesion(noPersonal, password);
            //todo
            //recuperaDatosTabla();
            actualizaUsuario(nombre, apellidoPaterno,apellidoMaterno,correo, password);
        }else{ 
            Utilidades.mostrarAlertaSimple("Campos requeridos", 
                    "Es necesario ingresar todos los campos", 
                    Alert.AlertType.WARNING);
        }
    }
    
    public void actualizaUsuario(String nombre, String apellidoPaterno, String apellidoMaterno, String correo,
            String password){
                try{
            String url = Constantes.URL_BASE+"usuarios/modificar";
            String parametros =
                    "correo="+correo+
                    "&nombre="+nombre+
                    "&apellidoPaterno="+apellidoPaterno+
                    "&apellidoMaterno="+apellidoMaterno+
                    
                    "&password="+password;
            String resultado = ConexionServiciosWeb.consumirServicioPUT(url, parametros);
            Gson gson = new Gson();
            Respuesta respuesta = gson.fromJson(resultado, Respuesta.class);
            
            if(!respuesta.getError()){
                Utilidades.mostrarAlertaSimple("Usuario Modificado", "Usuario Modificado "+
                        " al sistema", Alert.AlertType.INFORMATION);                
            }else{
                Utilidades.mostrarAlertaSimple("Usuario Modifiacdo", "Error en el correo", Alert.AlertType.ERROR);
            }
            
        }catch(Exception e){
            Utilidades.mostrarAlertaSimple("Error de conexión",  e.getMessage(), Alert.AlertType.ERROR);
            
        }
        configurarTabla();
        cargarUsuario();
    }

    @FXML
    private void eliminarUsuario(ActionEvent event) {
        String correo = tfCorreo.getText();
        eliminar(correo);

        
    }
    
    public void eliminar(String correo){
                try{
            String url = Constantes.URL_BASE+"usuarios/eliminar";
            String parametros = "correo="+correo;
            String resultado = ConexionServiciosWeb.consumirServicioDELETE(url, parametros);
            Gson gson = new Gson();
            Respuesta respuesta = gson.fromJson(resultado, Respuesta.class);
            
            if(!respuesta.getError()){
                Utilidades.mostrarAlertaSimple("Usuario Modificado", "Usuario Eliminado "+
                        " al sistema", Alert.AlertType.INFORMATION);                
            }else{
                Utilidades.mostrarAlertaSimple("Usuario Modifiacdo", "Error en el correo", Alert.AlertType.ERROR);
            }
            
        }catch(Exception e){
            Utilidades.mostrarAlertaSimple("Error de conexión",  e.getMessage(), Alert.AlertType.ERROR);
            
        }
        configurarTabla();
        cargarUsuario();
    }
    
    public void inicializarInformacionVentana(int idUsuario, String nombre, String aPaterno, String aMaterno, String correo, String password){    
        tfCorreo.setDisable(true);
        tfNombre.setText(nombre);
        tfApellidoPaterno.setText(aPaterno);
        tfApellidoMaterno.setText(aMaterno);
        tfCorreo.setText(correo);
        pfPassword.setText(password);
    }

    @FXML
    private void buscarUsuario(ActionEvent event) {
    }

    @FXML
    private void guardarUsuario(ActionEvent event) {
  
        int filaSeleccionada = tvUsuario.getSelectionModel().getSelectedIndex();
        if(filaSeleccionada >= 0){
                String nombre = listaUsuarios.get(filaSeleccionada).getNombre();
                String apellidoPaterno = listaUsuarios.get(filaSeleccionada).getApellidoPaterno();
                String apellidoMaterno = listaUsuarios.get(filaSeleccionada).getApellidoMaterno();
                String correo = listaUsuarios.get(filaSeleccionada).getCorreo();
                String password = listaUsuarios.get(filaSeleccionada).getPassword();            

                tfNombre.setText(nombre);
                tfApellidoPaterno.setText(apellidoPaterno);
                tfApellidoMaterno.setText(apellidoMaterno);
                tfCorreo.setText(correo);
                pfPassword.setText(password);
        }else{
            Utilidades.mostrarAlertaSimple("Selecciona un registro", "Debes seleccionar un administrador para su modificación"
                    , Alert.AlertType.WARNING);
        }
        

    }

    @FXML
    private void seleccionar(MouseEvent event) {
        
    }
    
}
