/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cuponexproyecto_contrerasjonathan_cuervofreddy;

import Util.Constantes;
import Util.Utilidades;
import com.google.gson.Gson;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import modelo.ConexionServiciosWeb;
import pojos.Respuesta;

/**
 * FXML Controller class
 *
 * @author fredd
 */
public class FXMLAgregarUsuariosController implements Initializable {

    @FXML
    private TextField tfNombre;
    @FXML
    private TextField tfApellidoPaterno;
    @FXML
    private TextField tfApellidoMaterno;
    @FXML
    private TextField tfCorreo;
    @FXML
    private TextField tfPassword;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void agregarUsuario(ActionEvent event) {
    
        String nombre = tfNombre.getText();
        String apellidoPaterno = tfApellidoPaterno.getText();
        String apellidoMaterno = tfApellidoMaterno.getText();
        String correo = tfCorreo.getText();
        String password = tfPassword.getText();
        mandarDatos(nombre, apellidoPaterno, apellidoMaterno, correo, password);
        
        
    }
    
    private void mandarDatos(String nombre, String apellidoPaterno, String apellidoMaterno, 
            String correo, String password){
        try{
            String url = Constantes.URL_BASE+"usuarios/registrar";
            String parametros = "nombre="+nombre+
                    "&apellidoPaterno="+apellidoPaterno+
                    "&apellidoMaterno="+apellidoMaterno+
                    "&correo="+correo+
                    "&password="+password;
            String resultado = ConexionServiciosWeb.consumirServicioPOST(url, parametros);
            Gson gson = new Gson();
            Respuesta respuesta = gson.fromJson(resultado, Respuesta.class);
            if(!respuesta.getError()){
                Utilidades.mostrarAlertaSimple("Usuario agregado", "Usuario Agregado "+respuesta.getNombre()+
                        " al sistema", Alert.AlertType.INFORMATION);
               
            }else{
                Utilidades.mostrarAlertaSimple("Usuario verificado", respuesta.getMensaje(), Alert.AlertType.ERROR);
            }
            
        }catch(Exception e){
            Utilidades.mostrarAlertaSimple("Error de conexión",  e.getMessage(), Alert.AlertType.ERROR);
            
        }
    }
    
}
