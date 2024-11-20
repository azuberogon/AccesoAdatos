package ejercicioRepaso.chuletas.gpt;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class UT1_A3_Zubero_Aitor {
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

        int numeroLineas = 0;
        int numeroPalabras = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(fichero))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                numeroLineas++;
                // Contar palabras en la línea
                String[] palabras = linea.trim().split("\\s+");
                numeroPalabras += palabras.length;
            }
        } catch (IOException e) {
            System.out.println("Se produjo un error al leer el fichero: " + e.getMessage());
            return;
        }

        // Crear el fichero resultado.txt
        File resultado = new File(fichero.getParent(), "ejercicioRepaso/ejercicios/resultado.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(resultado))) {
            writer.write("Número de líneas: " + numeroLineas);
            writer.newLine();
            writer.write("Número de palabras: " + numeroPalabras);
        } catch (IOException e) {
            System.out.println("Se produjo un error al escribir el fichero de resultado: " + e.getMessage());
        }

        System.out.println("Los resultados se han guardado en " + resultado.getAbsolutePath());
    }
}