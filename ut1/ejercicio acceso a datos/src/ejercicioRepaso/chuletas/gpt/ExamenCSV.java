package ejercicioRepaso.chuletas.gpt;



import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class ExamenCSV {

    public String ruta;

    public static void main(String[] args) {
        ExamenCSV examenCsv = new ExamenCSV();
        ArrayList<ArrayList<String>> hojaDecalculo = examenCsv.leerArchivosCSV();
        if (hojaDecalculo != null && examenCsv.escribirArchivos(hojaDecalculo)) {
            System.out.println("Se ha guardado correctamente.");
        }
    }

    public ExamenCSV() {
        ruta = "resources/hojadecalculos.csv";
    }

    public ArrayList<ArrayList<String>> leerArchivosCSV() {
        File archivo = new File(ruta);
        ArrayList<ArrayList<String>> hojaDeCalculo = new ArrayList<>();
        double sumaEdades = 0;
        int contadorEdades = 0;
        int indiceEdad = -1;
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            boolean primeraFila = true;
            while ((linea = br.readLine()) != null) {
                String[] columnas = linea.split(";");  // Ajustar el delimitador según sea necesario
                ArrayList<String> fila = new ArrayList<>(Arrays.asList(columnas));

                if (primeraFila) {
                    indiceEdad = fila.indexOf("Edad");
                    if (indiceEdad == -1) {
                        System.out.println("No se encontró la columna llamada 'Edad'.");
                    }
                    primeraFila = false;
                } else if (indiceEdad != -1 && fila.size() > indiceEdad) {
                    String valorEdad = fila.get(indiceEdad);
                    if (esNumero(valorEdad)) {
                        sumaEdades += Double.parseDouble(valorEdad);
                        contadorEdades++;
                    }
                }

                hojaDeCalculo.add(fila);
            }
        } catch (IOException e) {
            System.out.println("Error al abrir el archivo: " + e.getMessage());
            return null;
        }

        // Solo calcular la media si hay valores numéricos en la columna 'Edad'
        if (contadorEdades > 0) {
            double mediaEdades = sumaEdades / contadorEdades;
            System.out.println("Media de edades: " + mediaEdades);
        } else {
            System.out.println("No se encontraron valores numéricos en la columna 'Edad'.");
        }

        return hojaDeCalculo;
    }

    public boolean esNumero(String cadena) {
        try {
            Double.parseDouble(cadena);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public boolean escribirArchivos(ArrayList<ArrayList<String>> hojaDeCalculo) {
        File salidaCsv = new File("resources/Salida/salida.txt");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(salidaCsv))) {
            for (ArrayList<String> fila : hojaDeCalculo) {
                bw.write(String.join(" ", fila));  // Escribe las columnas separadas por espacio
                bw.newLine();
            }
            return true;
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
            return false;
        }
    }
}
