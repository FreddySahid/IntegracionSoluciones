
package Util;

import javafx.scene.control.Alert;


public class Utilidades {
    public static void mostrarAlertaSimple(String titulo,
            String mensaje, Alert.AlertType tipo){
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setContentText(mensaje);
        alerta.setHeaderText(null);
        alerta.showAndWait();
    }
}
