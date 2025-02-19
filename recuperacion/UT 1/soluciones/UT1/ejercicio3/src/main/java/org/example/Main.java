package org.example;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public class ContadorLineasPalabras {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Introduce el nombre del fichero (incluyendo la ruta si es necesario): ");
            String filePath = scanner.nextLine();

            File file = new File(filePath);

            // Comprobar si el fichero existe y es un archivo
            if (!file.exists() || file.isDirectory()) {
                System.out.println("El fichero no existe o es un directorio.");
                return;
            }

            int lineCount = 0;
            int wordCount = 0;

            // Contar líneas y palabras
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    lineCount++;
                    // Contar palabras en la línea
                    String[] words = line.trim().split("\\s+");
                    wordCount += words.length;
                }
            } catch (IOException e) {
                System.out.println("Ocurrió un error al leer el fichero: " + e.getMessage());
                return;
            }

            // Escribir resultados en resultado.txt
            String resultFilePath = file.getParent() + File.separator + "resultado.txt";
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(resultFilePath))) {
                bw.write("Número de líneas: " + lineCount + "\n");
                bw.write("Número de palabras: " + wordCount + "\n");
            } catch (IOException e) {
                System.out.println("Ocurrió un error al escribir el fichero de resultados: " + e.getMessage());
            }

            // Mostrar resultados en consola
            System.out.println("Número de líneas: " + lineCount);
            System.out.println("Número de palabras: " + wordCount);
            System.out.println("Resultados escritos en: " + resultFilePath);
        }
    }
}