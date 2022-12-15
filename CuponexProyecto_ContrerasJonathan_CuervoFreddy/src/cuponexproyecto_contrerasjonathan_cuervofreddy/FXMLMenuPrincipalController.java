/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cuponexproyecto_contrerasjonathan_cuervofreddy;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author fredd
 */
public class FXMLMenuPrincipalController implements Initializable {

    @FXML
    private BorderPane bp;
    @FXML
    private VBox vb;
    @FXML
    private Button btnUsuario;
    @FXML
    private Button btnEmpresa;
    @FXML
    private Button btnSucursales;
    @FXML
    private Button btnPromociones;
    @FXML
    private AnchorPane ap;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void menuUsuario(MouseEvent event) {
        
        loadPage("FXMLMenuUsuario");
        
        
        
    }

    @FXML
    private void menuEmpresa(MouseEvent event) {

    }

    @FXML
    private void menuSucursales(MouseEvent event) {
    }

    @FXML
    private void menuPromociones(ContextMenuEvent event) {
    }
    
    private void loadPage(String Page){
        Parent root = null;
        

        
        try {
            root = FXMLLoader.load(getClass().getResource(Page+".fxml"));
        } catch (IOException ex) {
            Logger.getLogger(FXMLMenuPrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }

        

        bp.setCenter(root);
    }
    
    @FXML
    public void abrirRegistro(ActionEvent event){
        try{
            Parent vistaMedicos = FXMLLoader.load(getClass().getResource("FXMLAgregarUsuarios.fxml"));
            Scene sceaAdmin = new Scene(vistaMedicos);
            Stage scenarioAdmin = new Stage();
            scenarioAdmin.setScene(sceaAdmin);
            scenarioAdmin.initModality(Modality.APPLICATION_MODAL);
            scenarioAdmin.showAndWait();
        }catch (IOException ex){
            
        }
    }
    
    @FXML
    public void abrirModificacion(ActionEvent event){
        try{
            Parent vistaMedicos = FXMLLoader.load(getClass().getResource("FXMLModificarUsuario.fxml"));
            Scene sceaAdmin = new Scene(vistaMedicos);
            Stage scenarioAdmin = new Stage();
            scenarioAdmin.setScene(sceaAdmin);
            scenarioAdmin.initModality(Modality.APPLICATION_MODAL);
            scenarioAdmin.showAndWait();
        }catch (IOException ex){
            
        }
        
    }


}
