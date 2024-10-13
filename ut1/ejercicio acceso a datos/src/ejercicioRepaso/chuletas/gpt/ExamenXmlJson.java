package ejercicioRepaso.chuletas.gpt;





import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;

    public class ExamenXmlJson {
        private String ruta;

        public static void main(String[] args) {
            ExamenXmlJson examen = new ExamenXmlJson();
            File archivoXML = new File(examen.getRutaXML());
            File archivoJSON = new File(examen.getRutaJSON());

            // Verificar la existencia del archivo XML y JSON
            if (!archivoXML.exists() || archivoXML.isDirectory()) {
                System.out.println("El archivo XML no existe o la ruta es incorrecta.");
                return;
            }
            if (!archivoJSON.exists() || archivoJSON.isDirectory()) {
                System.out.println("El archivo JSON no existe o la ruta es incorrecta.");
                return;
            }

            try {
                // Procesar archivo XML (Popularidad de videojuegos)
                int contadorAlta = 0;
                int contadorMedia = 0;
                NodeList listaVideojuego = examen.convertirArchivos("videojuego");

                for (int i = 0; i < listaVideojuego.getLength(); i++) {
                    Element videojuego = (Element) listaVideojuego.item(i);

                    if (videojuego.hasAttribute("popularidad")) {
                        String popularidad = videojuego.getAttribute("popularidad");
                        if (popularidad.equalsIgnoreCase("alta")) {
                            contadorAlta++;
                        } else if (popularidad.equalsIgnoreCase("media")) {
                            contadorMedia++;
                        }
                    }
                }

                String strAlta = "Número de videojuegos con popularidad 'alta': " + contadorAlta;
                String strMedia = "Número de videojuegos con popularidad 'media': " + contadorMedia;

                // Procesar archivo JSON (Empleados)
                JSONObject jsonObject = new JSONObject(new JSONTokener(new FileReader(archivoJSON)));
                double salarioMedio = examen.calcularSalarioMedio(jsonObject);
                int totalProyectos = examen.contarProyectos(jsonObject);

                System.out.println("Contenido JSON:");
                System.out.println(jsonObject.toString(2));  // Imprimir con formato bonito

                String informacion = "Salario medio de empleados activos: " + salarioMedio +
                        "\nNúmero total de proyectos: " + totalProyectos;

                // Guardar la información en un archivo
                examen.guardarInformacionEmpleados(jsonObject, strAlta, strMedia, salarioMedio, totalProyectos);

            } catch (ParserConfigurationException | SAXException | IOException e) {
                System.out.println("Error al procesar el archivo XML o JSON: " + e.getMessage());
            }
        }

        public String getRutaXML() {
            return "resources/resources/videojuego.xml";
        }

        public String getRutaJSON() {
            return "resources/resources/empleados.json";  // Suponemos que este es el archivo JSON
        }

        // Método para convertir el archivo XML y obtener los nodos
        public NodeList convertirArchivos(String etiqueta) throws ParserConfigurationException, IOException, SAXException {
            File archivoXML = new File(getRutaXML());
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document documento = builder.parse(archivoXML);
            documento.getDocumentElement().normalize();
            return documento.getElementsByTagName(etiqueta);
        }

        // Método para calcular el salario medio de empleados activos en el archivo JSON
        public double calcularSalarioMedio(JSONObject jsonObject) {
            JSONArray empleados = jsonObject.getJSONArray("empleados");
            double salarioTotal = 0;
            int empleadosActivos = 0;

            for (int i = 0; i < empleados.length(); i++) {
                JSONObject empleado = empleados.getJSONObject(i);
                boolean estaActivo = empleado.getBoolean("activo");
                if (estaActivo) {
                    double salario = empleado.getDouble("salario");
                    salarioTotal += salario;
                    empleadosActivos++;
                }
            }

            return empleadosActivos > 0 ? salarioTotal / empleadosActivos : 0;
        }

        // Método para contar el total de proyectos en el archivo JSON
        public int contarProyectos(JSONObject jsonObject) {
            JSONArray proyectos = jsonObject.getJSONArray("proyectos");
            return proyectos.length();
        }

        // Método para guardar la información solicitada en un archivo de texto
        public void guardarInformacionEmpleados(JSONObject jsonObject, String alta, String media, double salarioMedio, int totalProyectos) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("resources/resources/informacion_empleados.txt"))) {
                bw.write("Información de empleados:\n");
                bw.write(jsonObject.toString(2));  // Escribe el contenido completo del JSON
                bw.newLine();
                bw.write("\n" + alta);
                bw.newLine();
                bw.write(media);
                bw.newLine();
                bw.write("Salario medio de empleados activos: " + salarioMedio);
                bw.newLine();
                bw.write("Número total de proyectos: " + totalProyectos);
            } catch (IOException e) {
                System.out.println("Error al escribir en el archivo: " + e.getMessage());
            }
        }
    }


