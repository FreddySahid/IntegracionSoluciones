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
import modelo.ConexionServiciosWeb;
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
    private TableColumn clIdUsuario;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
        clIdUsuario.setCellValueFactory(new PropertyValueFactory("idUsuario"));
        
        
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
    }

    @FXML
    private void eliminarUsuario(ActionEvent event) {
    }

    @FXML
    private void buscarUsuario(ActionEvent event) {
    }

    @FXML
    private void guardarUsuario(ActionEvent event) {
    }
    
}
