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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import modelo.ConexionServiciosWeb;
import pojos.Promocion;
import pojos.Sucursal;

/**
 * FXML Controller class
 *
 * @author fredd
 */
public class FXMLBuscarPromocionController implements Initializable {

    @FXML
    private TextField tfBuscar;
    @FXML
    private Label lbPromocion;
    @FXML
    private DatePicker dpFecha;
    @FXML
    private DatePicker dpFechafinal;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void fnBuscar(ActionEvent event) {
        if(!tfBuscar.getText().isEmpty()){
            String nombrePromocion = tfBuscar.getText();
            buscarPromocion(nombrePromocion);
        }else{
            Utilidades.mostrarAlertaSimple("Datos incompletos", "Datos incompletos. Ingrese el nombre o la dirección", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void fnAgregarfecha(ActionEvent event) {
        String fechaInicioPromocion = dpFecha.getValue().toString();
        
        if(fechaInicioPromocion != null){
            buscarPromocionInicio(fechaInicioPromocion);
        }else{
            Utilidades.mostrarAlertaSimple("Error de conexión",  "Debes escoger una fecha", Alert.AlertType.ERROR);
           
        }
    }
    
    private void buscarPromocion(String nombrePromocion) {
            try{
            String url = Constantes.URL_BASE+"promociones/buscarnombrepromo";
            String parametros = "nombrePromocion="+nombrePromocion;
                    
            String resultado = ConexionServiciosWeb.consumirServicioPOST(url, parametros);
            Gson gson = new Gson();
            Promocion respuesta = gson.fromJson(resultado, Promocion.class);
            
            if(!respuesta.getNombrePromocion().isEmpty()){
                lbPromocion.setText(respuesta.toString());
                
            }else{
                lbPromocion.setText("No se encontro ninguna Promocion.");
            }
            
        }catch(Exception e){
            Utilidades.mostrarAlertaSimple("Error de conexión",  "No se encontró ninguna promocion", Alert.AlertType.ERROR);
            
        }
    }
        private void buscarPromocionInicio(String fechaInicioPromocion) {
            try{
            String url = Constantes.URL_BASE+"promociones/buscarnombrepromoFecha";
            String parametros = "fechaInicioPromocion="+fechaInicioPromocion;
                    
            String resultado = ConexionServiciosWeb.consumirServicioPOST(url, parametros);
            Gson gson = new Gson();
            Promocion respuesta = gson.fromJson(resultado, Promocion.class);
            
            if(!respuesta.getNombrePromocion().isEmpty()){
                lbPromocion.setText(respuesta.toString());
                
            }else{
                lbPromocion.setText("No se encontro ninguna Promocion.");
            }
            
        }catch(Exception e){
            Utilidades.mostrarAlertaSimple("Error de conexión",  "No se encontró ninguna promocion", Alert.AlertType.ERROR);
            
        }
    }

    @FXML
    private void fnFechaFinal(ActionEvent event) {
        String fechaFinPromocion = dpFechafinal.getValue().toString();
        
        if(fechaFinPromocion != null){
            buscarPromocionFin(fechaFinPromocion);
        }else{
            Utilidades.mostrarAlertaSimple("Error de conexión",  "Debes escoger una fecha", Alert.AlertType.ERROR);
           
        }
        
    }
    
    private void buscarPromocionFin(String fechaFinPromocion) {
            try{
            String url = Constantes.URL_BASE+"promociones/buscarnombrepromoFechaFin";
            String parametros = "fechaFinPromocion="+fechaFinPromocion;
                    
            String resultado = ConexionServiciosWeb.consumirServicioPOST(url, parametros);
            Gson gson = new Gson();
            Promocion respuesta = gson.fromJson(resultado, Promocion.class);
            
            if(!respuesta.getNombrePromocion().isEmpty()){
                lbPromocion.setText(respuesta.toString());
                
            }else{
                lbPromocion.setText("No se encontro ninguna Promocion.");
            }
            
        }catch(Exception e){
            Utilidades.mostrarAlertaSimple("Error de conexión",  "No se encontró ninguna promocion", Alert.AlertType.ERROR);
            
        }
    }

    
}
