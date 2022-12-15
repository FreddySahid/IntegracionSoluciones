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
import pojos.Empresa;
import pojos.Respuesta;

/**
 * FXML Controller class
 *
 * @author fredd
 */
public class FXMLBuscarEmpresaController implements Initializable {

    @FXML
    private Label lbEmpresa;
    @FXML
    private TextField tfBusqueda;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void fnBuscarEmpresa(ActionEvent event) {
        if(!tfBusqueda.getText().isEmpty()){
            String empresa = tfBusqueda.getText();
            buscarEmpresa(empresa);            
        }else{
            Utilidades.mostrarAlertaSimple("Error",  "Debe ingresar datos", Alert.AlertType.ERROR);
        }
        
    }
    
    public void buscarEmpresa(String empresa){
            try{
            String url = Constantes.URL_BASE+"empresas/buscarByRfcRepresentante/"+empresa;
            //String parametros = "nombreRepresentante="+empresa;
            String resultado = ConexionServiciosWeb.consumirServicioGET(url);
            Gson gson = new Gson();
            Empresa respuesta = gson.fromJson(resultado, Empresa.class);
            
            if(!respuesta.getNombre().isEmpty()){
                lbEmpresa.setText(respuesta.toString());
                
            }else{
                lbEmpresa.setText("No se encuentra la empresa");
            }
            
        }catch(Exception e){
            Utilidades.mostrarAlertaSimple("Error de conexión",  "No se encontro la empresa", Alert.AlertType.ERROR);
            
        }
        
    }
    
}
