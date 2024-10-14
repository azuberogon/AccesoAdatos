package org.example.Examen.propios.json;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;

public class ExamenJson {

    String rutaJson ;


    public static void main(String[] args) {

    }


    public ExamenJson(){
        rutaJson =  "src/main/resources/empleados.json";
    }

    public void cargarArchivosJson(){
        File archivoJson = new File(rutaJson);
        try{
            JSONObject jsb = new JSONObject(new JSONTokener(new FileReader(archivoJson)));

        }catch (IOException IOE){
            System.out.println("error: " + IOE);
        }


    }


    public double calcularSalariosMedios(JSONObject jsonObject){ //s se le pasa el objeto de JSONObject para poder cortarlo
        JSONArray empleados = jsonObject.getJSONArray("empleados"); // se gnera un array del obejeto con la key "empleados"
        double salarioTotal = 0; // se guardara
        int empleadosActivos = 0;
        for (int i = 0; i < empleados.length(); i++) {
            JSONObject empleado = empleados.getJSONObject(i);
            boolean estaActivo = empleado.getBoolean("activo"); // se buscara  el objeto boleano de Activo
            if (estaActivo){
                if (!empleado.isNull("salario")){
                    salarioTotal +=empleado.getDouble("salario");
                    empleadosActivos++;
                }

            }

        }

        /**
         *   return empleadosActivos > 0 ? salarioTotal / empleadosActivos : 0;
         *
         *   empleadosActivos > 0:
         * Esta es la condición que se está evaluando. Verifica si el número de empleados activos es mayor que 0.
         * Si empleadosActivos es mayor que 0, significa que hay empleados activos y que se puede realizar una división.
         *
         *
         *
         * Esta línea devuelve el salario promedio si hay empleados activos (empleadosActivos > 0). Si no hay empleados activos (es decir, si empleadosActivos es 0), devuelve 0 para evitar una división por cero.
         *
         * Ejemplo:
         * Si empleadosActivos = 5 y salarioTotal = 25000, entonces se calcularía 25000 / 5, lo que resultaría en 5000 (salario promedio por empleado activo).
         * Si empleadosActivos = 0, el resultado sería 0, ya que no hay empleados activos y la división no tendría sentido.
         * */
        return empleadosActivos > 0 ? salarioTotal / empleadosActivos : 0;//? = if (empleadosActivos > 0 / ( salrioTotal mepleadosActivos))
        //0: hace que el valo si no es positivo sera 0 (como un valor absoluto pero lite )
    }


    public void guardarInformacionEmpleados(JSONObject jsonObject,double salarios, int totalProyectos){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/resources/informacion_empleados2.txt"))){
            bw.write("informacion de empleados:");
            bw.write(jsonObject.toString(2)); // escribe el objetojson pero ademas le dados espaciuos a cada nivel
            bw.newLine();
            bw.write("Salario medio de empleados activos: " + salarios);
            bw.newLine();
            bw.write("Número total de proyectos: " + totalProyectos);
        }catch (IOException IOE){
            System.out.println("error en la abertura del archvio: "+IOE);

        }


    }




    public int contarProyectos(JSONObject jsonObject){
        int cont = 0;

        JSONArray proyectos = jsonObject.getJSONArray("proyectos");

        return proyectos.length();
    }














    /**
     *
     *
     *{
     *   "nombre": "Juan",
     *   "edad": 30,
     *   "proyectos": [
     *     {
     *       "nombre": "Proyecto A",
     *       "descripcion": "Descripción del proyecto A"
     *     },
     *     {
     *       "nombre": "Proyecto B",
     *       "descripcion": "Descripción del proyecto B"
     *     }
     *   ]
     * }
     *
     *
     *
     *      JSONObject proyecto = proyectos.getJSONObject(i);
     *     {
     *       "nombre": "Proyecto A",
     *       "descripcion": "Descripción del proyecto A"
     *     },
     *
     *     String nombre = proyecto.getString("nombre");
     *
     * */

    public void testJsonArray() throws FileNotFoundException {
        JSONObject jsonObject = new JSONObject(new JSONTokener(new FileReader(new File("asd"))));
        JSONArray proyectos = jsonObject.getJSONArray("proyectos");

        for (int i = 0; i < proyectos.length(); i++) {
            JSONObject proyecto = proyectos.getJSONObject(i);
            String nombre = proyecto.getString("nombre");
            String descripcion = proyecto.getString("descripcion");
            System.out.println("Proyecto: " + nombre + ", Descripción: " + descripcion);
        }


    }












}
