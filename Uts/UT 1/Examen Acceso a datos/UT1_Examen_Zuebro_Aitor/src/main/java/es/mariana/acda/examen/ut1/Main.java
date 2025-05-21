package es.mariana.acda.examen.ut1;

import es.mariana.acda.examen.ut1.models.PuntoRecogida;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        String rutaJSON = null;

        // TODO START guarda en rutaJSON la ruta relativa al fichero recogida_residuos.json del directorio src/main/resources
        rutaJSON= "src/main/resources/recogida_residuos.json";
        // TODO END

        JSONObject jsonObject = null;

        // TODO START Parsea el documento mediante LecturaJSON a jsonObject. Avisa de error en la lectura mediante un Sistem.out
        File archivoJson = new File(rutaJSON);
        if (!archivoJson.exists() || archivoJson.isDirectory()) {
            System.out.println("El archivo JSON no existe o la ruta es incorrecta.");
            return;
        }
        try{
            jsonObject = new JSONObject(new JSONTokener(new FileReader(archivoJson)));
        }catch (Exception e){
            System.out.println("No se puede leer el archivo JSON.");
        }

        // TODO END

        String mancomunidad = "";

        // TODO START solicita por System.in el nombre de la mancomunidad cuyos datos queremos recabar
        //     Si se introduce una cadena en blanco se debe solicitar de nuevo. Almacenar el dato en la variable
        Scanner sc = new Scanner(System.in);

        do {
            mancomunidad=sc.next();
        }while (mancomunidad.isEmpty());

        /*fallo con la clase scanner */

        sc.close();


        // TODO END


        List<PuntoRecogida> puntosRecogida = new ArrayList<>();


        // TODO START extrae del jsonObject una lista de los PuntosRecogida que pertenezcan a la mancomunidad introducida (si existe)
        //       Gestiona todas las posibles excepciones y problemas que puedan darse


        JSONArray puntosRecogidaJsonArray = jsonObject.getJSONArray("records");
        for (int i = 0; i < puntosRecogidaJsonArray.length(); i++) {
            JSONArray jsonArrayObj = puntosRecogidaJsonArray.getJSONArray(1);
            for (int j = 0; j < jsonArrayObj.length(); j++) {

                JSONObject objeto = jsonArrayObj.getJSONObject(j);
                int id = objeto.getInt("_id");
                String tipoEquipamiento = objeto.getString("ID Tipo Equipamiento");
                String ID_tipo_Equipamiento = objeto.getString("ID Equipamiento");
                String TipoEquipamiento = objeto.getString("TipoEquipamiento");
                String x = objeto.getString("x");
                String y = objeto.getString("y");
                String Mancomunidad = objeto.getString("Mancomunidad");
                String Localidad = objeto.getString("Localidad");
                String Direccion = objeto.getString("Direccion");
                String Horario = objeto.getString("Horario");
                String Comentarios = objeto.getString("Comentarios");
                String Fecha_ultima_actualizacion = objeto.getString("Comentarios");
                String Despues_que = objeto.getString("?Despues que?");
                try{

                }catch (NumberFormatException NFE){
                    System.out.println("Erro al parsear el numero ");
                }
                //puntosRecogida.add(new PuntoRecogida(id,Integer.parseInt(ID_tipo_Equipamiento)))
            }



            /*
            JSONObject puntoDeREcogidaObj = puntosRecogidaJsonArray.getJSONObject(i);
            JSONArray puntos= puntoDeREcogidaObj.getJSONArray("");
            for (int j = 0; j < ; j++) {

            }

            int id = puntoDeREcogidaObj.getInt("_id");
            String tipoEquipamiento = puntoDeREcogidaObj.getString("ID Tipo Equipamiento");
            String IDtipoEquipamiento = puntoDeREcogidaObj.getString("ID Equipamiento");
            System.out.println();



            PuntoRecogida aux = new PuntoRecogida(id,)
            puntosRecogida.add();
*/

        }
/*
        for (int i = 0; i < empleados.length(); i++) {
            JSONObject empleado = empleados.getJSONObject(i);
            boolean estaActivo = empleado.getBoolean("activo");
            if (estaActivo) {
                // Comprobar si el salario no es nulo
                if (!empleado.isNull("salario")) {
                    double salario = empleado.getDouble("salario");
                    salarioTotal += salario;
                    empleadosActivos++;
                }
            }
        }*/
        // TODO END



        // TODO START Ordena la lista mediante el metodo puntosOrdenados

        // TODO END



        //TODO START Genera el archivo binario mediante la clase EscrituraBinario. Avisa de errores en la escritura por System.out.


        //TODO END

    }







    public static List<PuntoRecogida> puntosOrdenados( List<PuntoRecogida> puntos){

        // TODO START Devuelve la lista de PuntosRecogida ordenada de manera ascendente según el tipo de equipamiento
        return puntos; //permite usar el metodo aunque no ordene. Cambiar al completar el metodo
        // TODO END
    }


}
