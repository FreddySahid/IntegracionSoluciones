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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import modelo.ConexionServiciosWeb;
import pojos.Respuesta;
import pojos.Sucursal;

/**
 * FXML Controller class
 *
 * @author fredd
 */
public class FXMLBuscarSucursalController implements Initializable {

    @FXML
    private Label lbSucursal;
    @FXML
    private TextField tfNombreDireccion;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // TODO
    }    


    @FXML
    private void fnBuscar(ActionEvent event) {
                if(!tfNombreDireccion.getText().isEmpty()){
            String nombreDireccion = tfNombreDireccion.getText();
            buscarSucursal(nombreDireccion);
        }else{
            Utilidades.mostrarAlertaSimple("Datos incompletos", "Datos incompletos. Ingrese el nombre o la dirección", Alert.AlertType.ERROR);
        }
    }

    private void buscarSucursal(String nombreDireccion) {
            try{
            String url = Constantes.URL_BASE+"sucursales/buscarnombredireccion";
            String parametros = "nombre="+nombreDireccion;
                    
            String resultado = ConexionServiciosWeb.consumirServicioPOST(url, parametros);
            Gson gson = new Gson();
            Sucursal respuesta = gson.fromJson(resultado, Sucursal.class);
            
            if(!respuesta.getNombre().isEmpty()){
                lbSucursal.setText(respuesta.toString());
                
            }else{
                lbSucursal.setText("No se encontro ninguna sucursal.");
            }
            
        }catch(Exception e){
            Utilidades.mostrarAlertaSimple("Error de conexión",  "No se encontró ninguna sucursal", Alert.AlertType.ERROR);
            
        }
    }
    
}
