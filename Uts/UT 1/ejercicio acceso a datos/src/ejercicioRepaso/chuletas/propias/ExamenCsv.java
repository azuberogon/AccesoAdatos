package ejercicioRepaso.chuletas.propias;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class ExamenCsv {
    public String ruta;


    public static void main(String[] args) {


        ExamenCsv examenCsv = new ExamenCsv();
        ArrayList<ArrayList<String>> hojaDecalculo=examenCsv.leerArchivosCSV();
        if (examenCsv.escribirArchivos(hojaDecalculo)){
            System.out.println("se ha guardado correctamente");
        }

    }
    public ExamenCsv(){
        ruta = "hojadecalculos.csv";
    }


    /**
     * Ejemplo csv:
     * String[] columnas = {"Nombre", "Edad", "Ciudad"};
     * Arrays.asList(columnas) -> convierte el arreglo en array, ArrayList<String> propia de "Array."
     * new ArrayList<>() genera un array -> new ArrayList<>(Arrays.asList(columnas)) -> Array<Array<String>> lo mismo que necesitamos
     * se guarda en fila
     * ArrayList<String> fila = new ArrayList<>(Arrays.asList(columnas));
     *
     *
     * hojaDeCalculo.add(fila); => se añade el array a la hoja de calculo osea la fila se añade a la hoja de calculo
     *
     * */
    public ArrayList<ArrayList<String>> leerArchivosCSV(){
        File archivo = new File(ruta);
        ArrayList<ArrayList<String>> hojaDeCalculo = new ArrayList<>();
        double sumaEdades = 0 ;
        int contadorEdades = 0 ;
        int indiceEdad = -1 ;
        try {
            BufferedReader br = new BufferedReader(new FileReader(archivo));
            String linea;
            boolean primeraFila = true;
            while ((linea = br.readLine()) != null) {
                String[] columnas = linea.split(";");//columna porque se separa en columnas el de filas es porque ese el conjunto de filas
                // crea la fila del csv en modo String sustituible por object al estar desordenado, luego mutable a otro dato.
                ArrayList<String> fila = new ArrayList<>(Arrays.asList(columnas));//aqui se genmeran las filas que son fillas pero es el conjunto de columnas
                if (primeraFila){//aw comprueba si es la priemra fila para que no salte el error
                    indiceEdad = fila.indexOf("Edad");//busca la columna de edad
                    if (indiceEdad == -1){//si no existe se mete por este if
                        System.out.println("No se encontro la columna llamada 'edad'.");
                    }
                    primeraFila=false;// ya no entrara mas por esta funcion "if (primeraFila){"
                }  else if (indiceEdad != -1 && fila.size() > indiceEdad) { // si se encontro el indice lo guarda en indiceEdad para poder coger siempre la columna
                    // Calcular la media de la columna 'Edad'
                    String valorEdad = fila.get(indiceEdad); // se guarda el dato en este string
                    if (esNumero(valorEdad)) { // se comprueba que sea un numero y despues se suma
                        sumaEdades += Double.parseDouble(valorEdad);
                        contadorEdades++;
                    }
                }
                hojaDeCalculo.add(fila); //se añade la fila a la hoja de calculo para poder imprimirlo
            }




            /*for (ArrayList<String> fila : hojaDeCalculo) {
                for (String columna : fila) {
                    boolean esNumero;
                    try {
                        Integer.parseInt(columna);
                        esNumero = true;  // Si se puede convertir, es un número
                    } catch (NumberFormatException e) {
                        esNumero = false; // No es un número
                    }
                    if (esNumero) {
                        media += Double.parseDouble(columna);
                        cont++;
                    }
                    System.out.println(columna + " ");
                }
                System.out.println();
            }*/
            br.close();
        }catch (IOException IOE){
            System.out.println("Error al abrir el archivo" + IOE.getMessage() );
        }
        sumaEdades = sumaEdades/contadorEdades;
        System.out.println("media de edad: " + sumaEdades);

        return hojaDeCalculo;
    }

    public boolean esNumero(String cadena){
        try{
            Double.parseDouble(cadena);
            return true;
        }catch (NumberFormatException NFE){
            System.out.println("Error en el formato de numero" + NFE.getMessage());
            return false;
        }

    }



    public boolean escribirArchivos(ArrayList<ArrayList<String>> HojaDeCalculo) {
        File hojaCsv = new File("resources/Salida/salida.txt");
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(hojaCsv));

            for (ArrayList<String> fila : HojaDeCalculo) {
                for (String columna : fila) {
                    bw.write(columna + " "); // si cambiamos este metodo con ; podras hacer una hja
                }
                bw.newLine();
            }

            bw.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

}

/*
    public void getRuta() {
        String archivo = "ruta/a/tu/archivo.csv"; // Reemplaza con la ruta de tu archivo CSV

        ArrayList<ArrayList<String>> hojaDeCalculo = new ArrayList<>(); // Lista para almacenar las filas y columnas
        String separador = ";"; // Delimitador CSV (puedes cambiarlo si es necesario)
/**
 *
 * array[array[String]] != a[a[],a[]]
 *
 *
 *
 *
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;

            // Leer cada línea del archivo
            while ((linea = br.readLine()) != null) {
                // Dividir la línea en columnas
                String[] columnas = linea.split(separador);

                // Crear una nueva lista para almacenar las columnas de la fila actual
                ArrayList<String> fila = new ArrayList<>();

                // Agregar cada columna a la lista de la fila actual
                for (String columna : columnas) {
                    fila.add(columna);
                }

                // Agregar la fila a la lista principal
                hojaDeCalculo.add(fila);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Imprimir el contenido del ArrayList<ArrayList<String>>
        for (ArrayList<String> fila : hojaDeCalculo) {
            for (String columna : fila) {
                System.out.print(columna + " ");
            }
            System.out.println();  // Nueva línea al final de cada fila
        }
    }

*/

