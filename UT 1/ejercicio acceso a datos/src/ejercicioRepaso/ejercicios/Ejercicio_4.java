package ejercicioRepaso.ejercicios;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Ejercicio_4 {

    /**
     *
     Desarrolla un programa que cumpla con las siguientes características:
     • Pide al usuario un nombre de fichero (este puede incluir la ruta absoluta o relativa al mismo)
     • Comprobar si existe el fichero
     • En caso de no existir o ser un directorio, mostrar un mensaje por pantalla.
     • En el caso de que sea un fichero, tratarlo como si de un fichero CSV se tratase.
     • Contar el número de campos (columnas) que tiene.
     • En caso de que cuente con un campo "edad", calcular la media de las edades de todas las personas que aparezcan. Para ello, basaros en el recurso facilitado.
     • Escribir los resultados en un fichero en la misma ruta que el fichero leído. El fichero resultante tendrá el nombre "resultado_csv.txt"

     * */

    public static void main(String[] args) {

        if (args.length !=0){
            System.out.println("debes introducir una ruta absoluta o relativa");

        }
        File archivo = new File(args[0]);
        if (archivo.exists()){
            System.out.println("la ruta apunta a un directorio");
            return;

        }
        if (archivo.isDirectory()){
            System.out.println("el archivo es un directorio");
            return;
        }


        int numeroCampos = 0;
        List<Integer> edades = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(archivo));
            String linea ;
            String [] cabecera = br.readLine().trim().split(",");

            int inidiceBusqueda = -1; // menos uno para que de un pete de indice
            for (int i = 0; i < cabecera.length; i++) {
                if (cabecera[i].trim().equalsIgnoreCase("edad")){
                    inidiceBusqueda = i;
                    break;
                }
            }
            // linea = br.readLine();  -> se crea un objeto de string que contiene toda la linea del lector br,
            // luego se compara para que no sea null el objeto
            while ((linea = br.readLine())!=null){
                String [] campos = linea.trim().split(",");
                if (inidiceBusqueda!=-1 && campos.length>inidiceBusqueda){
                    try {
                        edades.add(Integer.parseInt(campos[inidiceBusqueda].trim()));
                    }catch (NumberFormatException NFE){
                        System.out.println("se intenta meter el ");
                    }
                }

            }




        }catch (IOException IOE){
            System.out.println("error al arbir el archivo" + archivo.getPath() + "error:" + IOE.getMessage());
        }

        double media = 0 ;
        if (!edades.isEmpty()){
            double sumaMedia = 0;
            for (Integer edad : edades) {
                sumaMedia += edad;
            }
            media = sumaMedia / edades.size();
        }


    }




}
