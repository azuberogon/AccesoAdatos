package es.mariana.acda.examen.ut1.utils;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;

public class LecturaJSON {

    public static JSONObject lecturaJSON(String rutaFichero) throws IOException {
        // TODO START parsear para devolver un JSONObject
        File archivoJson = new File(rutaFichero);
        try{
            JSONObject jsb = new JSONObject(new JSONTokener(new FileReader(archivoJson)));
            return jsb;
        }catch (IOException IOE){
            System.out.println("error: " + IOE);

            return null;
        }

       //permite usar el método (aunque el resultado es null). Cambiar al completar el metodo
        // TODO END
    }

    private static String lecturaFichero(String rutaFichero) throws IOException {
        // TODO START lee el fichero que recibe por parámetro
        File archivoJson = new File(rutaFichero);
        try{
            JSONObject jsb = new JSONObject(new JSONTokener(new FileReader(archivoJson)));
            return jsb.toString();
        }catch (IOException IOE){
            System.out.println("error: " + IOE);

            return null;
        }
 //Cambiar al completar el método
        // TODO END

    }
}
