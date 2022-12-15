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
import pojos.Estado;
import pojos.Respuesta;

/**
 * FXML Controller class
 *
 * @author fredd
 */
public class FXMLRegistrarEmpresaController implements Initializable {

    @FXML
    private TextField tfDireccion;
    @FXML
    private TextField tfCorreo;
    @FXML
    private TextField tfNombreRepresentante;
    @FXML
    private TextField tfNombreComercial;
    @FXML
    private TextField tfNombre;
    @FXML
    private TextField tfDireccionNumero;
    @FXML
    private TextField tfCp;
    @FXML
    private TextField tfCiudad;
    @FXML
    private TextField tfTelefono;
    @FXML
    private TextField tfSitioWeb;
    @FXML
    private TextField tfRFC;
    @FXML
    private ComboBox<Estado> cvEstado;

    ObservableList<Estado> listaEstado = FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        obtenerEstados();
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
    private void agregarEmpresa(ActionEvent event) {
        if(!tfNombre.getText().isEmpty()&& !tfNombreComercial.getText().isEmpty()&&
                !tfNombreRepresentante.getText().isEmpty() && !tfCorreo.getText().isEmpty() && 
                !tfDireccion.getText().isEmpty() && !tfDireccionNumero.getText().isEmpty() && 
                !tfCp.getText().isEmpty() && !tfCiudad.getText().isEmpty() && !tfTelefono.getText().isEmpty()
                && !tfSitioWeb.getText().isEmpty() && !tfRFC.getText().isEmpty() && 
                !cvEstado.getValue().toString().isEmpty() ){
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
            String estado = cvEstado.getValue().toString();
            Integer idEstado;
            if(estado == "Inactivo"){
                idEstado = 2;
            }else{
                idEstado = 1;
                System.out.println(idEstado);
            }
            if(!nombre.isEmpty() && !nombreComercial.isEmpty() && !nombreRepresentante.isEmpty() 
                    && !correoEmpresa.isEmpty() && !direccionCalle.isEmpty() && direccionNumero != null && cp != null 
                    && !ciudad.isEmpty() && !telefono.isEmpty() && !sitioWeb.isEmpty() && !rfc.isEmpty() && 
                    idEstado != null){
                GuardarEmpresa(nombre, nombreComercial, nombreRepresentante, correoEmpresa, 
                        direccionCalle, direccionNumero, cp, ciudad, telefono, sitioWeb, rfc, idEstado);
            }else{
                Utilidades.mostrarAlertaSimple("Datos incompletos", "Datos incompletos", Alert.AlertType.ERROR);
            }
        }
        else{
            Utilidades.mostrarAlertaSimple("Datos incompletos", "Datos incompletos", Alert.AlertType.ERROR);

        }
    }
    
    public void GuardarEmpresa(String nombre,String nombreComercial, String nombreRepresentante,
         String correoEmpresa,  String direccionCalle, Integer direccionNumero, Integer cp,String ciudad, 
         String telefono, String sitioWeb,String rfc, Integer idEstado  ){
        
        try{
            String url = Constantes.URL_BASE+"empresas/registrar";
            String parametros = "nombre="+nombre+
                    "&nombreComercial="+nombreComercial+
                    "&nombreRepresentante="+nombreRepresentante+
                    "&correoEmpresa="+correoEmpresa+
                    "&direccionCalle="+direccionCalle+
                    "&direccionNumero="+direccionNumero+
                    "&cp="+cp+
                    "&ciudad="+ciudad+
                    "&telefono="+telefono+
                    "&sitioWeb="+sitioWeb+
                    "&rfc="+rfc+
                    "&idEstado="+
                    idEstado;
            String resultado = ConexionServiciosWeb.consumirServicioPOST(url, parametros);
            Gson gson = new Gson();
            Respuesta respuesta = gson.fromJson(resultado, Respuesta.class);
            if(!respuesta.getError()){
                Utilidades.mostrarAlertaSimple("Usuario agregado", "Empresa agregada al sistema"+
                        " al sistema", Alert.AlertType.INFORMATION);
               
            }else{
                Utilidades.mostrarAlertaSimple("Datos erroneos", respuesta.getMensaje(), Alert.AlertType.ERROR);
            }
            
        }catch(Exception e){
            Utilidades.mostrarAlertaSimple("Error de conexión",  "Datos erroneos o no se puede conectar con la base de datos", Alert.AlertType.ERROR);
            
        }
        
    }
    
}
