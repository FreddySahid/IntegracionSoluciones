/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author fredd
 */
public class ConexionServiciosWeb {
    public static String consumirServicioGET(String url) throws Exception{
        String resultado = "";
        URL urlServicio = new URL(url);
        HttpURLConnection conexionHTTP = (HttpURLConnection) urlServicio.openConnection();
        conexionHTTP.setRequestMethod("GET");
        //Ejecutamos la petición
        int codigoRespuesta = conexionHTTP.getResponseCode();
        System.out.println("Codigo de respuesta GET: "+codigoRespuesta);
        if(codigoRespuesta == HttpURLConnection.HTTP_OK){
            resultado = convertirStreamDatos(conexionHTTP.getInputStream());
        }
        return resultado;
    }
    
    public static String consumirServicioPOST(String url, String parametros) throws Exception{
        String resultado = "";
        URL urlServicio = new URL(url);
        HttpURLConnection conexionHTTP = (HttpURLConnection) urlServicio.openConnection();
        conexionHTTP.setRequestMethod("POST");
        conexionHTTP.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conexionHTTP.setDoOutput(true);
        OutputStream salidaStream = conexionHTTP.getOutputStream();
        salidaStream.write(parametros.getBytes());
        salidaStream.flush();
        salidaStream.close();
        int codigoRespuesta = conexionHTTP.getResponseCode();
        System.out.println("Codigo Respuesta Post: "+codigoRespuesta);
        if(codigoRespuesta == HttpURLConnection.HTTP_OK){
            resultado = convertirStreamDatos(conexionHTTP.getInputStream());
        }else{
            resultado = "Error en el resultado con código "+ codigoRespuesta;
        }
        return resultado;
    }
    
    private static String convertirStreamDatos(InputStream infoBytes) throws IOException{
        InputStreamReader inputDatos = new InputStreamReader(infoBytes);
        BufferedReader bufferR = new BufferedReader(inputDatos);
        StringBuffer respuesta = new StringBuffer();
        String textoEntrada;
        while((textoEntrada = bufferR.readLine()) != null){
            respuesta.append(textoEntrada);
        }
        bufferR.close();
        return respuesta.toString();
        
    }
    public static String peticionServicioPOSTImagen(String url,byte[] parametros) throws IOException{
        
        String resultado = "";
        URL urlAcceso = new URL(url);
        HttpURLConnection conexionHTTP = (HttpURLConnection) urlAcceso.openConnection();
        conexionHTTP.setRequestMethod("POST");
        conexionHTTP.setRequestProperty("Content-Type", "multipart/form-data");
        conexionHTTP.setDoOutput(true);
        
        OutputStream outputSalida = conexionHTTP.getOutputStream() ;
        outputSalida.write(parametros);
        outputSalida.flush();
        outputSalida.close();

        int codigoRespuesta = conexionHTTP.getResponseCode();
        System.out.println("El código de respuesta es: "+ codigoRespuesta);
        
        if (codigoRespuesta == HttpURLConnection.HTTP_OK) {
            resultado = convertirStreamDatos(conexionHTTP.getInputStream());
        }else{
            resultado = "Error en la petición POST con código: "+codigoRespuesta;
        }
        
        return resultado;
    }  
    
    public static String consumirServicioPUT(String url, String parametros) throws Exception{
        String resultado = "";
        URL urlServicio = new URL(url);
        HttpURLConnection conexionHTTP = (HttpURLConnection) urlServicio.openConnection();
        conexionHTTP.setRequestMethod("PUT");
        conexionHTTP.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conexionHTTP.setDoOutput(true);
        OutputStream salidaStream = conexionHTTP.getOutputStream();
        salidaStream.write(parametros.getBytes());
        salidaStream.flush();
        salidaStream.close();
        int codigoRespuesta = conexionHTTP.getResponseCode();
        System.out.println("Codigo Respuesta Post: "+codigoRespuesta);
        if(codigoRespuesta == HttpURLConnection.HTTP_OK){
            resultado = convertirStreamDatos(conexionHTTP.getInputStream());
        }else{
            resultado = "Error en el resultado con código "+ codigoRespuesta;
        }
        return resultado;
    }
    
    public static String consumirServicioDELETE(String url, String parametros) throws Exception{
        String resultado = "";
        URL urlServicio = new URL(url);
        HttpURLConnection conexionHTTP = (HttpURLConnection) urlServicio.openConnection();
        conexionHTTP.setRequestMethod("DELETE");
        conexionHTTP.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conexionHTTP.setDoOutput(true);
        OutputStream salidaStream = conexionHTTP.getOutputStream();
        salidaStream.write(parametros.getBytes());
        salidaStream.flush();
        salidaStream.close();
        int codigoRespuesta = conexionHTTP.getResponseCode();
        System.out.println("Codigo Respuesta Post: "+codigoRespuesta);
        if(codigoRespuesta == HttpURLConnection.HTTP_OK){
            resultado = convertirStreamDatos(conexionHTTP.getInputStream());
        }else{
            resultado = "Error en el resultado con código "+ codigoRespuesta;
        }
        return resultado;
    }
}
