package org.example;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Main {


    public class ProcesadorCSV {
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

            int columnCount = 0;
            List<Integer> edades = new ArrayList<>();
            boolean hasEdadColumn = false;

            // Leer el fichero CSV
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String headerLine = br.readLine();
                if (headerLine != null) {
                    // Contar columnas
                    String[] headers = headerLine.split(",");
                    columnCount = headers.length;

                    // Comprobar si hay una columna "edad"
                    for (String header : headers) {
                        if (header.trim().equalsIgnoreCase("edad")) {
                            hasEdadColumn = true;
                            break;
                        }
                    }

                    // Leer las líneas restantes y recoger las edades
                    String line;
                    while ((line = br.readLine()) != null) {
                        String[] fields = line.split(",");
                        if (hasEdadColumn) {
                            for (int i = 0; i < fields.length; i++) {
                                if (headers[i].trim().equalsIgnoreCase("edad")) {
                                    try {
                                        int edad = Integer.parseInt(fields[i].trim());
                                        edades.add(edad);
                                    } catch (NumberFormatException e) {
                                        System.out.println("Valor no válido en la columna 'edad': " + fields[i]);
                                    }
                                    break;
                                }
                            }
                        }
                    }
                }
            } catch (IOException e) {
                System.out.println("Ocurrió un error al leer el fichero: " + e.getMessage());
                return;
            }

            // Calcular la media de las edades
            double mediaEdad = 0;
            if (hasEdadColumn && !edades.isEmpty()) {
                int sumaEdades = 0;
                for (int edad : edades) {
                    sumaEdades += edad;
                }
                mediaEdad = (double) sumaEdades / edades.size();
            }

            // Escribir resultados en resultado_csv.txt
            String resultFilePath = file.getParent() + File.separator + "resultado_csv.txt";
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(resultFilePath))) {
                bw.write("Número de campos (columnas): " + columnCount + "\n");
                if (hasEdadColumn) {
                    bw.write("Media de edades: " + (edades.isEmpty() ? "No hay edades disponibles" : mediaEdad) + "\n");
                } else {
                    bw.write("No se encontró la columna 'edad'.\n");
                }
            } catch (IOException e) {
                System.out.println("Ocurrió un error al escribir el fichero de resultados: " + e.getMessage());
            }

            // Mostrar resultados en consola
            System.out.println("Número de campos (columnas): " + columnCount);
            if (hasEdadColumn) {
                System.out.println("Media de edades: " + (edades.isEmpty() ? "No hay edades disponibles" : mediaEdad));
            } else {
                System.out.println("No se encontró la columna 'edad'.");
            }
            System.out.println("Resultados escritos en: " + resultFilePath);
        }
    }
}