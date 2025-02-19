package org.example;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
public class Main {


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

        // Contar las ocurrencias de 'm', 'n' y 'ñ'
        int contadorM = 0, contadorN = 0, contadorÑ = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                for (char c : linea.toCharArray()) {
                    switch (Character.toLowerCase(c)) {
                        case 'm':
                            contadorM++;
                            break;
                        case 'n':
                            contadorN++;
                            break;
                        case 'ñ':
                            contadorÑ++;
                            break;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Ocurrió un error al leer el fichero: " + e.getMessage());
        }

        // Mostrar el resultado
        System.out.println("Total de 'm': " + contadorM);
        System.out.println("Total de 'n': " + contadorN);
        System.out.println("Total de 'ñ': " + contadorÑ);
    }

}