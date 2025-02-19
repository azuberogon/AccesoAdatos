package org.example;
import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.File;
import java.util.Scanner;

public class Main {

    public class ProcesadorXML {
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

            // Procesar el fichero XML
            try {
                // Crear un objeto DocumentBuilderFactory
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document document = builder.parse(file);

                // Normalizar el documento
                document.getDocumentElement().normalize();

                // Obtener la lista de elementos "videojuego"
                NodeList nodeList = document.getElementsByTagName("videojuego");

                // Mostrar la información de cada videojuego
                for (int i = 0; i < nodeList.getLength(); i++) {
                    Node node = nodeList.item(i);
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        Element element = (Element) node;

                        String nombre = element.getElementsByTagName("nombre").item(0).getTextContent();
                        String genero = element.getElementsByTagName("genero").item(0).getTextContent();
                        String compania = element.getElementsByTagName("compania").item(0).getTextContent();
                        String anio = element.getElementsByTagName("anio").item(0).getTextContent();

                        System.out.println("Videojuego " + (i + 1) + ":");
                        System.out.println("  Nombre: " + nombre);
                        System.out.println("  Género: " + genero);
                        System.out.println("  Compañía: " + compania);
                        System.out.println("  Año: " + anio);
                        System.out.println();
                    }
                }
            } catch (Exception e) {
                System.out.println("Ocurrió un error al procesar el fichero XML: " + e.getMessage());
            }
        }
    }
}