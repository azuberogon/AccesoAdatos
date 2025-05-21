package es.masanz.ut1;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {
    private PrintWriter fSalida;//escritura
    private BufferedReader fEntrada;//sirve para leer
    private String c_Ruta;


    private HashMap<String,Integer> lineas ;



    public void leerDatos() throws IOException {


        int cont =1;
        fEntrada = new BufferedReader(new FileReader("Recolector Lorem Ipsum/Lorem.txt"));
        String linea;
        while ((linea = fEntrada.readLine())!=null){
            if (!linea.trim().isEmpty()){
                crearCarpeta(cont);//crea las carpetas
                linea = tipoGrafia(linea);
                cuentaLineas(linea);//añade en el hash  los datos

                escritorLineas();//escribe las lineas del hash

                cont++;
                lineas.clear();//lo ultimo para limpiar el arrayList y que por cada parrafo peuda seguir sin que se acumule lo del siguiente

            }
        }


    }


    private void crearCarpeta(int numeroCarpeta)  {
        c_Ruta="Parrafo"+numeroCarpeta;
        File carpeta = new File(c_Ruta);
        //fSalida = new PrintWriter(new BufferedWriter( new FileWriter(nombre)));
        if (!carpeta.exists()){
            //boolean intento = carpeta.mkdirs(); // test
            if (carpeta.mkdirs()){
                System.out.println("La carpeta ha sido creada ");
            }else {
                System.out.println("Error al crear la carpeta ");
            }
        }else {
            System.out.println("La carpeta ya existe ");
        }
    }
    public String tipoGrafia(String linea){

        return linea.replaceAll("[.,]", " ").toLowerCase();
    }

    private void cuentaLineas(String linea){
        lineas = new HashMap<>();
        String [] separadas = linea.split(" ");
        for (String lin:separadas) {
            if (!lin.trim().isEmpty()){
                if (lineas.containsKey(lin)){
                    Integer cont =lineas.get(lin);
                    cont += 1;
                    lineas.put(lin,cont);
                }else {
                    lineas.put(lin, 1);
                }
            }

        }


    }

    private void escritorLineas()  {

        try{

            for (Map.Entry<String, Integer> entry : lineas.entrySet()) {
               /* String clave = entry.getKey();
                Integer valor = entry.getValue();
                String ruta = c_Ruta+"/"+clave; // ruta donde se escribe el nombre de la carpeta y el nombre archivo
                fSalida = new PrintWriter(new BufferedWriter(new FileWriter(ruta))); // para que no se escriban dos veces los datos (no deberia)
                fSalida.write("Frecuencia: " + valor + "\n");
                System.out.println("Clave: " + clave + ", Valor: " + valor); // probar que vaya bien
                System.out.println("Fallo despues de cerrar");
                */
                String clave = entry.getKey();
                Integer valor = entry.getValue();
                String ruta = c_Ruta + File.separator + clave + ".txt"; // Usar File.separator para la ruta
                fSalida = new PrintWriter(new BufferedWriter(new FileWriter(ruta, true))); // Abrir en modo de adición
                fSalida.write("Frecuencia: " + valor + "\n");
                fSalida.close();
                System.out.println("Clave: " + clave + ", Valor: " + valor); // Probar que vaya bien
                System.out.println("Fallo después de cerrar");
            }
        }catch (FileNotFoundException FNE) {
            System.out.println(FNE + "No se a encontrado el fichero");
        }catch ( IOException e){
            System.out.println(e +"Error al abrir el archivo ");
        }

    }

    public static void main(String[] args) throws IOException {
        Main main = new Main();
        main.lineas = new HashMap<>();
        main.leerDatos();


    }




}