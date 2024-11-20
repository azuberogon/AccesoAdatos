package ejercicioRepaso.chuletas.gpt;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ExamenXml {
    private String ruta;

    public static void main(String[] args) {
        ExamenXml examenXml = new ExamenXml();
        File archivo = new File(examenXml.getRuta());

        if (!archivo.exists()) {
            System.out.println("El archivo no existe o la ruta es incorrecta.");
            return;
        }
        if (archivo.isDirectory()) {
            System.out.println("Error: La ruta apunta a un directorio.");
            return;
        }

        try {
            int contadorAlta = 0;
            int contadorMedia = 0;

            // Obtener lista de nodos con etiqueta "videojuego"
            NodeList listaVideojuego = examenXml.convertirArchivos("videojuego");

            for (int i = 0; i < listaVideojuego.getLength(); i++) {
                Element videojuego = (Element) listaVideojuego.item(i);

                // Verificar si el nodo tiene atributo "popularidad"
                if (videojuego.hasAttribute("popularidad")) {
                    String popularidad = videojuego.getAttribute("popularidad");
                    if (popularidad.equalsIgnoreCase("alta")) {
                        contadorAlta++;
                    } else if (popularidad.equalsIgnoreCase("media")) {
                        contadorMedia++;
                    }
                }
            }
            String strAlta = "Número de videojuegos con popularidad 'alta': "  + contadorAlta ;
            String strMedia = "Número de videojuegos con popularidad 'media': " + contadorMedia;
            guardar(strAlta,strMedia);
            System.out.println(strAlta);
            System.out.println(strMedia);

        } catch (ParserConfigurationException | SAXException | IOException e) {
            System.out.println("Error al procesar el archivo XML: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }

    public String getRuta() {
        return ruta;
    }

    public ExamenXml() {
        System.out.println("Inicializando...");
        ruta = "resources/resources/videojuego.xml"; // Ruta actualizada
    }

    // Método para convertir el archivo XML y obtener los nodos
    public NodeList convertirArchivos(String etiqueta) throws ParserConfigurationException, IOException, SAXException {
        File archivoXML = new File(getRuta()); // Usar la ruta definida en el constructor

        // Crear un DocumentBuilderFactory
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        // Crear un DocumentBuilder para parsear el archivo
        DocumentBuilder builder = factory.newDocumentBuilder();

        // Parsear el archivo XML y obtener un objeto Document
        Document documento = builder.parse(archivoXML);

        // Normalizar el documento XML (opcional pero recomendado)
        documento.getDocumentElement().normalize();

        // Obtener una lista de nodos con la etiqueta dada (por ejemplo, "videojuego")
        return documento.getElementsByTagName(etiqueta);
    }

    public static void guardar(String alta,String media){
        System.out.println("guardar");
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("resources/resources/medias.txt"))){
            bw.write(alta);
            bw.newLine();
            bw.write(media);

        }catch (IOException IOE){

        }
    }
}
