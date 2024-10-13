package ejercicioRepaso.ejercicios;

import java.io.*;

public class Ejercicio_3 {

    public static void main(String[] args) {
        if (args.length == 0){
            System.out.println("error no se ha metido la ruta");
        }
        File archivo = new File(args[0]);

        if (!archivo.exists()){
            System.out.println("el archivo no existe");
            return;
        }

        if (archivo.isDirectory()){
            System.out.println("la ruta apunta a un directorio ");
            return;
        }
        /*
        * \\s coincide con un solo carácter de espacio en blanco, como un
        * espacio, una pestaña, una nueva línea o un retorno de carro.
        *
        * El cuantificador + después de \\s indica que el patrón anterior debe coincidir
        * una o más veces.
        *
        * */
        int palabra = 0;
        int lineas = 0;
        // se abre el fichero y empieza a procesar el texto para poder manipularlo se usar lo de bufferedReader = new bfr ne filereader
        try (BufferedReader br = new BufferedReader( new FileReader(archivo))){
            //linea guardara la linea del fichero
            String linea="";

            while ((linea=br.readLine())!=null){
                lineas++;
                String [] palabras = linea.trim().split("\\s+"); // array -> linea con toda la informacion, se eliminan los espacios, se separa con el patron
                palabra += palabras.length; // palabra guarda todas las palabras e
            }
            System.out.println("total de plabras: " + palabra +"\n linea: " + lineas  );
        } catch (IOException IO){
            System.out.println("error archivo: " + args[0] + " error: " + IO.getMessage() );
        }

        File archivoEscribir = new File(archivo.getParent(), "ejercicioRepaso/ejercicios/contador.txt");

        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(archivoEscribir));
            bw.write("Palabras: " + palabra);
            bw.newLine();
            bw.write("lineas: " + lineas);
            bw.close();
        }catch (IOException IOE){
            System.out.println("error al crear el fichero: " + IOE.getMessage());
        }

        System.out.println("los elementos se guardado en la ruta: " +archivoEscribir.getParent());
    }
}
