package ejercicioRepaso.chuletas.gpt;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UT1_A4_Zubero_Aitor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduce el nombre del fichero (ruta absoluta o relativa): ");
        String rutaFichero = scanner.nextLine();

        File fichero = new File(rutaFichero);


        if (!fichero.exists()) {
            System.out.println("El fichero no existe.");
            return;
        }


        if (fichero.isDirectory()) {
            System.out.println("La ruta especificada es un directorio.");
            return;
        }

        int numeroCampos = 0;
        List<Integer> edades = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fichero))) {
            String linea;
            String[] encabezados = reader.readLine().split(",");


            numeroCampos = encabezados.length;


            int indiceEdad = -1; //menos
            for (int i = 0; i < encabezados.length; i++) {
                if (encabezados[i].trim().equalsIgnoreCase("edad")) {
                    indiceEdad = i;
                    break;
                }
            }


            while ((linea = reader.readLine()) != null) {
                String[] campos = linea.split(",");
                if (indiceEdad != -1 && campos.length > indiceEdad) {
                    try {
                        int edad = Integer.parseInt(campos[indiceEdad].trim());
                        edades.add(edad);
                    } catch (NumberFormatException e) {
                        System.out.println("Valor de edad no válido en la línea: " + linea);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Se produjo un error al leer el fichero: " + e.getMessage());
            return;
        }


        double mediaEdad = 0;
        if (!edades.isEmpty()) {
            double sumaEdades = 0;
            for (int edad : edades) {
                sumaEdades += edad;
            }
            mediaEdad = sumaEdades / edades.size();
        }


        File resultado = new File(fichero.getParent(), "resultado_csv.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(resultado))) {
            writer.write("Número de campos: " + numeroCampos);
            writer.newLine();
            if (!edades.isEmpty()) {
                writer.write("Media de edades: " + mediaEdad);
            } else {
                writer.write("No se encontraron edades.");
            }
        } catch (IOException e) {
            System.out.println("Se produjo un error al escribir el fichero de resultado: " + e.getMessage());
        }

        System.out.println("Los resultados se han guardado en " + resultado.getAbsolutePath());
    }
}
