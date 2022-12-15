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
import javafx.scene.control.TextField;
import modelo.ConexionServiciosWeb;
import pojos.Empresa;
import pojos.EmpresaSegunda;
import pojos.Estado;
import pojos.Respuesta;

/**
 * FXML Controller class
 *
 * @author fredd
 */
public class FXMLRegistrarSucursalController implements Initializable {

    @FXML
    private TextField tfNombre;
    @FXML
    private TextField tfDireccionCalle;
    @FXML
    private TextField tfNumero;
    @FXML
    private TextField tfCP;
    @FXML
    private TextField tfColonia;
    @FXML
    private TextField tfEncargado;
    @FXML
    private TextField tfLongitud;
    @FXML
    private TextField tfLatitud;
    @FXML
    private TextField tfTelefono;
    @FXML
    private TextField tfCiudad;
    @FXML
    private ComboBox<EmpresaSegunda> cvEmpresa;
    
    Integer idEmpresa;
    
    ObservableList<EmpresaSegunda> listaEmpresa = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        obtenerEmpresa();
    }  
    
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

    @FXML
    private void fnGuardar(ActionEvent event) {
        
        if(!cvEmpresa.getValue().toString().isEmpty() && !tfNombre.getText().isEmpty() &&
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
            String encargado = tfEncargado.getText();    
            obtenerIdEmpresa();
            guardarSucursalBD(nombre, direccionCalle, direccionNumero, cp, colonia, ciudad, telefono, 
                    latitud, longitud, encargado);
        }
        else{
            Utilidades.mostrarAlertaSimple("Datos incompletos", "Datos incompletos", Alert.AlertType.ERROR);
        }
    }
    
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
    
    private void guardarSucursalBD(String nombre, String direccionCalle, Integer direccionNumero,
            Integer cp, String colonia, String ciudad, String telefono, Double latitud, Double longitud, 
            String nombreEncargado){
                try{
            String url = Constantes.URL_BASE+"sucursales/registrar";
            String parametros = "nombre="+nombre+"&direccionCalle="+direccionCalle+
                    "&direccionNumero="+direccionNumero+
                    "&cp="+cp+"&colonia="+colonia+"&ciudad="+ciudad+"&telefono="+telefono+
                    "&latitud="+latitud+"&longitud="+longitud+"&nombreEncargado="+nombreEncargado+
                    "&idEmpresa="+idEmpresa;
            String resultado = ConexionServiciosWeb.consumirServicioPOST(url, parametros);
            Gson gson = new Gson();
            Respuesta respuesta = gson.fromJson(resultado, Respuesta.class);
            
            if(!respuesta.getError()){
                Utilidades.mostrarAlertaSimple("Sucursal guardada", "Sucursal Guarda"+
                        " al sistema", Alert.AlertType.INFORMATION);

                
            }else{
                Utilidades.mostrarAlertaSimple("Sucursal guardada", "Datos incorrectos", Alert.AlertType.ERROR);
            }
            
        }catch(Exception e){
            Utilidades.mostrarAlertaSimple("Error de conexión",  e.getMessage(), Alert.AlertType.ERROR);
            
        }
        
    }
    
}
