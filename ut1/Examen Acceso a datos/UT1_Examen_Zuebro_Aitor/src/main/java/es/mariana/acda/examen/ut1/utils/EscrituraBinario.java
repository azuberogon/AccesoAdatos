package es.mariana.acda.examen.ut1.utils;

import es.mariana.acda.examen.ut1.models.PuntoRecogida;

import java.io.*;
import java.util.List;

public class EscrituraBinario {

    public static void escribirBinarios(List<PuntoRecogida> puntos) throws IOException {
        // TODO START crea un ficheros binario formateado segun las instrucciones llamado "mancomunidad.bin",
        //        donde "mancomunidad" es aquella en concreto que estamos guardando
        //        Recuerda guardarlo en el lugar pedido y manejar las excepciones adecuadamente

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/main/resources"))) {
            for (PuntoRecogida punto : puntos) {
                oos.writeObject(punto.toString());
            }
            System.out.println("Objetos escritos en el fichero " + new File("src/main/resources/mancomunidad.bin").getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo: " + e.getMessage());
        }



       /* try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/resources/informacion_empleados.txt"))) {
            bw.write("Información de empleados:\n");
            bw.write(jsonObject.toString(2));  // Escribe el contenido completo del JSON
            bw.newLine();
            bw.write("Salario medio de empleados activos: " + salarioMedio);
            bw.newLine();
            bw.write("Número total de proyectos: " + totalProyectos);
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
        }*/

        // TODO END
    }


    public static String listaEquipamientos(List<PuntoRecogida> puntosRecogida){
        // TODO START Devuelve una cadena con los tipos de equipamiento de la mancomunidad


        return puntosRecogida.toString(); //permite usar el metodo aunque no da el resultado pedido. Cambiar al completar el metodo
        // TODO END
    }
}
