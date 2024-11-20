package org.example.Examen.gpt.binario;

import java.io.*;
import java.util.ArrayList;
/**
 * Clase Pelicula: Define los atributos título, fechaDeEstreno y director. Implementa el método toString() para mostrar la información de la película.
 * Clase Trilogia: Contiene una lista de películas y una lista de productores. También tiene un método toString() que genera una cadena con toda la información de la trilogía.
 * Clase ExamenTrilogias:
 * Creación de las trilogías: Se crean 3 trilogías (trilogiaOriginal, trilogiaPrecuelas, trilogiaSecuelas) y se agregan las películas y productores correspondientes.
 * Guardado en un archivo binario: Se utiliza ObjectOutputStream para guardar el objeto ArrayList<Trilogia> en un archivo binario.
 * Lectura y muestra del archivo: Se utiliza ObjectInputStream para leer las trilogías del archivo y mostrarlas en consola.
 *
 *
 * */
public class ExamenTrilogias {
    public static void main(String[] args) {
        // Crear las trilogías de Star Wars
        Trilogia trilogiaOriginal = new Trilogia("Trilogía Original");
        trilogiaOriginal.agregarPelicula(new Pelicula("Star Wars: Episodio IV - Una nueva esperanza", "25/5/1977", "George Lucas"));
        trilogiaOriginal.agregarPelicula(new Pelicula("Star Wars: Episodio V - El Imperio contraataca", "21/5/1980", "Irvin Kershner"));
        trilogiaOriginal.agregarPelicula(new Pelicula("Star Wars: Episodio VI - El retorno del Jedi", "25/5/1983", "Richard Marquand"));
        trilogiaOriginal.agregarProductor("Gary Kurtz");
        trilogiaOriginal.agregarProductor("Howard Kazanjian");

        Trilogia trilogiaPrecuelas = new Trilogia("Trilogía de Precuelas");
        trilogiaPrecuelas.agregarPelicula(new Pelicula("Star Wars: Episodio I - La amenaza fantasma", "19/5/1999", "George Lucas"));
        trilogiaPrecuelas.agregarPelicula(new Pelicula("Star Wars: Episodio II - El ataque de los clones", "16/5/2002", "George Lucas"));
        trilogiaPrecuelas.agregarPelicula(new Pelicula("Star Wars: Episodio III - La venganza de los Sith", "19/5/2005", "George Lucas"));
        trilogiaPrecuelas.agregarProductor("Rick McCallum");

        Trilogia trilogiaSecuelas = new Trilogia("Trilogía de Secuelas");
        trilogiaSecuelas.agregarPelicula(new Pelicula("Star Wars: Episodio VII - El despertar de la Fuerza", "18/12/2015", "J. J. Abrams"));
        trilogiaSecuelas.agregarPelicula(new Pelicula("Star Wars: Episodio VIII - Los últimos Jedi", "15/12/2017", "Rian Johnson"));
        trilogiaSecuelas.agregarPelicula(new Pelicula("Star Wars: Episodio IX - El ascenso de Skywalker", "20/12/2019", "J. J. Abrams"));
        trilogiaSecuelas.agregarProductor("Kathleen Kennedy");

        ArrayList<Trilogia> trilogias = new ArrayList<>();
        trilogias.add(trilogiaOriginal);
        trilogias.add(trilogiaPrecuelas);
        trilogias.add(trilogiaSecuelas);

        // Guardar en un archivo binario
        String rutaArchivo = "trilogias_star_wars.dat";
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(rutaArchivo))) {
            oos.writeObject(trilogias);
            System.out.println("Objetos escritos en el fichero " + new File(rutaArchivo).getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo: " + e.getMessage());
        }

        // Leer y mostrar el contenido del archivo binario
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(rutaArchivo))) {
            ArrayList<Trilogia> trilogiasLeidas = (ArrayList<Trilogia>) ois.readObject();
            System.out.println("\nVamos a recuperar los datos guardados en el archivo...");
            for (Trilogia trilogia : trilogiasLeidas) {
                System.out.println(trilogia);
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }
}
