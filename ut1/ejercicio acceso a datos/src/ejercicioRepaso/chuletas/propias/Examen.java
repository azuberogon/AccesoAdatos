package ejercicioRepaso.chuletas.propias;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;



public class Examen {

    public static String ruta;
    public static String rutaSubArchivo;
    public static void main(String[] args) {
        if (args.length!=1){
            System.out.println("Error en los argumentos");

            return;
        }
        else {
            ruta = args[0];
        }
        contarLetras();

        File archivo = new File(args[0]);
        if (!archivo.exists()){
            System.out.println("el archivo no existe o directorio no existe.");
            return;
        }
        if (archivo.isDirectory()){
            esUndirectorio(archivo);
        }
        if (args.length<2){
            ExamenCsv examenCsv = new ExamenCsv();

        }


    }


    public static void esUndirectorio(File archivo){
        System.out.println("la ruta indica da es un directorio");
        System.out.println("El archivo pesa " + archivo.length());
        System.out.println("Permiso de lectura:"+ archivo.canRead());
        System.out.println("Permiso de escritura:"+ archivo.canWrite());
        System.out.println("Permiso de ejecución:"+ archivo.canExecute());

        String [] listaArchivos  = archivo.list();

        if (listaArchivos != null && listaArchivos.length>1){
            for(String subArchivos : listaArchivos ){
                File subArchivoConertido = new File(ruta + File.separator + subArchivos);
                System.out.println("Nombre del archivo");
                System.out.println("Permiso de lectura:"+ subArchivoConertido.canRead());
                System.out.println("Permiso de escritura:"+ subArchivoConertido.canWrite());
                System.out.println("Permiso de ejecución:"+ subArchivoConertido.canExecute());
            }
        }
    }

    public static File creadorArchivos(String nombre){
        rutaSubArchivo = ruta + File.separator + nombre;
        return new File(rutaSubArchivo);

    }
    /**
     *
     * esta funcion abre un archivo y lee todas las lineas de un archivo txt o csv
     * no se si funciona
     * y una lista de strings para recibir todas las lineas del metodo statico
     * Files.readAllLines() abre el archivo y lee todas las palabras necesita un archivo como argumento
     * Path.get() convierte el archivo en una ruta
     *
     *
     * */
    public static void leerTodasLasLIneasDeUnArchivo(){
        try {

            // Lee todas las líneas del archivo en una lista de Strings
            List<String> allLines = Files.readAllLines(Paths.get(rutaSubArchivo)); // Path.get() genera un ruta

            // Imprime cada línea del archivo
            for (String line : allLines) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Ocurrió un error al leer el archivo.");
            e.printStackTrace();
        }
    }



    public static void contarLetras() {
        //String[] listaArchivo = carpeta.list();
        int contM=0;int contN=0;int contÑ=0;
        //if (listaArchivo != null) {
        //    for (String archivoString : listaArchivo) {
            File archivo =new File(ruta);

        if (archivo.canRead() && archivo.isFile() ) {
            try(BufferedReader br = new BufferedReader(new FileReader(archivo))){
                String linea ;
                while ( (linea = br.readLine()) != null){
                    linea = linea.trim();
                    String [] palabras = linea.split("\\s+"); //las palabras se separan mas robnustamente para cualquier tipo de espacio
                    for (String palabra : palabras) {
                        palabra = palabra.toLowerCase();
                        char [] letras =palabra.toCharArray();
                        for (char letra : letras) {
                            if (letra=='m'){
                                contM++;
                            } else if (letra=='n') {
                                contN++;
                            } else if (letra=='ñ') {
                                contÑ++;
                            }
                        }
                    }
                }
                String texto = "Letras que contiene el texto" +
                                "\nLetra M:" +contM +
                                "\nLetra n:" + contN +
                                "\nLetra Ñ:"+ contÑ;

                System.out.println(texto);

                if (guardarEnTXT(texto)){
                    System.out.println("Se ha guardado correctamente");
                }
            }catch (IOException IOE){
                System.out.println("Error al leer el archivo: "+ IOE.getMessage());
            }
        }
    }
        //}
    //}
    public static boolean verificarCarpeta(String carpetaRuta){
        File carpeta = new File(carpetaRuta);
        if (!carpeta.exists()){
            return true;
        }else {
            return false;
        }
    }

    public static boolean guardarEnTXT(String texto){
        String rutass = "resources/Salida";
        rutass += "/ContarLetras.txt";
        boolean guardado =false;
        if (verificarCarpeta(rutass)){
            System.out.println("Error la carpeta $rutass" + rutass);
            return false;
        }


        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutass))){
            guardado = true;
            bw.write(texto); // escritor de linease pero se puede hacer que se escribar archivos se puede usar "\n"
        }catch (IOException IOE){
            System.out.println("Error no se puede leer el archivo, Error... " + IOE.getMessage());
        }
        return guardado;
    }



}
