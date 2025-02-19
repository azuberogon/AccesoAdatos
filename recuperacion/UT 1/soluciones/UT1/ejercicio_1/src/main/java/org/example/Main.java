package org.example;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        // Comprobar que se ha pasado un argumento
        if (args.length != 1) {
            System.out.println("Por favor, proporciona una ruta de fichero o directorio.");
            return;
        }

        // Obtener la ruta del fichero o directorio
        String path = args[0];
        File file = new File(path);

        // Comprobar si existe
        if (!file.exists()) {
            System.out.println("El fichero o directorio no existe: " + path);
            return;
        }

        // Si es un fichero
        if (file.isFile()) {
            System.out.println("Es un fichero.");
            System.out.println("Tamaño: " + file.length() + " bytes");
            System.out.println("Permisos:");
            System.out.println("Lectura: " + file.canRead());
            System.out.println("Escritura: " + file.canWrite());
            System.out.println("Ejecución: " + file.canExecute());
        }
        // Si es un directorio
        else if (file.isDirectory()) {
            System.out.println("Es un directorio.");
            System.out.println("Contenido:");

            File[] files = file.listFiles();
            if (files != null) {
                long totalSize = 0;
                for (File f : files) {
                    System.out.println(f.getName() + (f.isDirectory() ? " (directorio)" : " (fichero)"));
                    totalSize += f.length();
                }
                System.out.println("Tamaño total del directorio: " + totalSize + " bytes");
            } else {
                System.out.println("El directorio está vacío.");
            }
        } else {
            System.out.println("El objeto no es ni un fichero ni un directorio.");
        }
    }
}