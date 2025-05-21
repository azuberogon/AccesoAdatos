package ejercicioRepaso.chuletas.gpt;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class UT1_A6_Zubero_Aitor {

    public static void main(String[] args) {


        if (args.length < 1) {
            System.out.println("Por favor, proporciona la ruta del archivo XML como argumento.");
            return;
        }


        String rutaFichero = args[0];
        File archivo = new File(rutaFichero);


        if (!archivo.exists()) {
            System.out.println("El fichero '" + rutaFichero + "' no existe.");
        } else if (archivo.isDirectory()) {
            System.out.println("'" + rutaFichero + "' es un directorio, no un fichero.");
        } else {

            try {
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(archivo);
                doc.getDocumentElement().normalize();


                NodeList nList = doc.getElementsByTagName("videojuego");


                for (int i = 0; i < nList.getLength(); i++) {
                    Node nNode = nList.item(i);

                    if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element eElement = (Element) nNode;
                        String popularidad = eElement.getAttribute("popularidad");
                        String nombre = eElement.getElementsByTagName("nombre").item(0).getTextContent();

                        NodeList plataformasList = eElement.getElementsByTagName("plataforma");
                        NodeList generosList = eElement.getElementsByTagName("genero");
                        String anio = eElement.getElementsByTagName("anio").item(0).getTextContent();
                        String beneficios = eElement.getElementsByTagName("beneficios").item(0).getTextContent();


                        System.out.println("--------------------------------------");
                        System.out.println("Videojuego: " + nombre);
                        System.out.println("Popularidad: " + popularidad);
                        System.out.println("Año: " + anio);
                        System.out.println("Beneficios: " + beneficios);

                        System.out.println("Plataformas:");
                        for (int j = 0; j < plataformasList.getLength(); j++) {
                            String plataforma = plataformasList.item(j).getTextContent();
                            System.out.println("  " + plataforma);
                        }

                        System.out.println("Géneros:");
                        for (int k = 0; k < generosList.getLength(); k++) {
                            String genero = generosList.item(k).getTextContent();
                            System.out.println("  " + genero);
                        }
                    }
                }
                System.out.println("--------------------------------------");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
