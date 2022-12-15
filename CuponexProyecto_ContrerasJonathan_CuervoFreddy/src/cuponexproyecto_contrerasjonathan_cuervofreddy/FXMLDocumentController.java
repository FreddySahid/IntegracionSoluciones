/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cuponexproyecto_contrerasjonathan_cuervofreddy;

import Util.Constantes;
import Util.Utilidades;
import com.google.gson.Gson;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.ConexionServiciosWeb;
import pojos.Respuesta;

/**
 * FXML Controller class
 *
 * @author fredd
 */
public class FXMLDocumentController implements Initializable {
    
    public Integer idUsuario;

    
    @FXML
    private TextField tfCorreo;
    @FXML
    private Label label;
    @FXML
    private PasswordField pfPassword;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
    @FXML
    private void iniciarSesion(){
        String correo = tfCorreo.getText();
        String password = pfPassword.getText();
        
        if(!correo.isEmpty()&&!password.isEmpty()){
            verificarInicioSesion(correo, password);
        }else{
            Utilidades.mostrarAlertaSimple("Campos requeridos", "Es importante ingresar los datos",
                    Alert.AlertType.WARNING);
        }
        
               
    }
    
    private void verificarInicioSesion( String correo, String password){
        try{
            String url = Constantes.URL_BASE+"usuarios/usuarioLogin";
            String parametros = "correo="+correo+
                    "&password="+password;
            String resultado = ConexionServiciosWeb.consumirServicioPOST(url, parametros);
            Gson gson = new Gson();
            Respuesta respuesta = gson.fromJson(resultado, Respuesta.class);
            
            if(!respuesta.getError()){
                Utilidades.mostrarAlertaSimple("Usuario verificado", "bienvenido "+respuesta.getNombre()+
                        " al sistema", Alert.AlertType.INFORMATION);
                idUsuario = Integer.parseInt(""+respuesta.getIdUsuario());
                irPantallaPrincipal();
                
            }else{
                Utilidades.mostrarAlertaSimple("Usuario verificado", "Usuario o contraseña incorrectos", Alert.AlertType.ERROR);
            }
            
        }catch(Exception e){
            Utilidades.mostrarAlertaSimple("Error de conexión",  e.getMessage(), Alert.AlertType.ERROR);
            
        }
        
        
        
    }
    
    
    private void irPantallaPrincipal(){
        
        try{
            Parent vistaPrincipal = FXMLLoader.load(getClass().getResource("FXMLMenuPrincipal.fxml"));
            Scene escenaPRincipal = new Scene(vistaPrincipal);
            Stage escenarioBase = (Stage) tfCorreo.getScene().getWindow();
            escenarioBase.setScene(escenaPRincipal);
            escenarioBase.show();
            
        }catch(IOException ex){
            Utilidades.mostrarAlertaSimple("Error", "Error al iniciar", Alert.AlertType.ERROR);
            
        }
        
    }
    
 
    
    
}
