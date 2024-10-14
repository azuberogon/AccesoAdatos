package org.example.Examen.gpt.json;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;

public class ExamenJsonGPT {
    private String rutaArchivoJson;

    public ExamenJsonGPT() {
        rutaArchivoJson = "src/main/resources/empleados.json";
    }

    public static void main(String[] args) {
        ExamenJsonGPT examen = new ExamenJsonGPT();
        File archivoJson = new File(examen.getRutaJSON());

        // Verificar la existencia del archivo JSON
        if (!archivoJson.exists() || archivoJson.isDirectory()) {
            System.out.println("El archivo JSON no existe o la ruta es incorrecta.");
            return;
        }

        try {
            // Procesar archivo JSON (Empleados)
            JSONObject jsonObject = new JSONObject(new JSONTokener(new FileReader(archivoJson)));
            double salarioMedio = examen.calcularSalarioMedio(jsonObject);
            int totalProyectos = examen.contarProyectos(jsonObject);

            System.out.println("Contenido JSON:");
            System.out.println(jsonObject.toString(2));  // Imprimir con formato bonito

            // Guardar la información en un archivo
            examen.guardarInformacionEmpleados(jsonObject, salarioMedio, totalProyectos);

        } catch (IOException e) {
            System.out.println("Error al procesar el archivo JSON: " + e.getMessage());
        }
    }

    public String getRutaJSON() {
        return "src/main/resources/empleados.json";  // Suponemos que este es el archivo JSON
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
                // Comprobar si el salario no es nulo
                if (!empleado.isNull("salario")) {
                    double salario = empleado.getDouble("salario");
                    salarioTotal += salario;
                    empleadosActivos++;
                }
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
    public void guardarInformacionEmpleados(JSONObject jsonObject, double salarioMedio, int totalProyectos) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/resources/informacion_empleados.txt"))) {
            bw.write("Información de empleados:\n");
            bw.write(jsonObject.toString(2));  // Escribe el contenido completo del JSON
            bw.newLine();
            bw.write("Salario medio de empleados activos: " + salarioMedio);
            bw.newLine();
            bw.write("Número total de proyectos: " + totalProyectos);
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
        }
    }
}
/*
*
*
* package org.example.Examen.gpt.json;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.*;

public class ExamenJsonGPT {
    private String rutaArchivoJson; // Variable para almacenar la ruta del archivo JSON

    public ExamenJsonGPT() {
        rutaArchivoJson = "src/main/resources/empleados.json"; // Inicializa la ruta del archivo JSON
    }

    public static void main(String[] args) {
        ExamenJsonGPT examen = new ExamenJsonGPT();
        File archivoJson = new File(examen.getRutaJSON()); // Crear objeto File para el archivo JSON

        // Verificar la existencia del archivo JSON
        if (!archivoJson.exists() || archivoJson.isDirectory()) {
            System.out.println("El archivo JSON no existe o la ruta es incorrecta.");
            return;
        }

        try {
            // Procesar archivo JSON
            JSONObject jsonObject = new JSONObject(new JSONTokener(new FileReader(archivoJson))); // Crear el JSONObject a partir del archivo JSON
            double salarioMedio = examen.calcularSalarioMedio(jsonObject); // Calcular el salario medio
            int totalProyectos = examen.contarProyectos(jsonObject); // Contar el número de proyectos

            System.out.println("Contenido JSON:");
            System.out.println(jsonObject.toString(2)); // Imprimir el contenido del JSON con formato bonito

            // Guardar la información en un archivo de texto
            examen.guardarInformacionEmpleados(jsonObject, salarioMedio, totalProyectos);

        } catch (IOException e) {
            System.out.println("Error al procesar el archivo JSON: " + e.getMessage()); // Manejar errores de lectura del archivo
        }
    }

    public String getRutaJSON() {
        return "src/main/resources/empleados.json"; // Ruta al archivo JSON
    }

    // Método para calcular el salario medio de empleados activos en el archivo JSON
    public double calcularSalarioMedio(JSONObject jsonObject) {
        JSONArray empleados = jsonObject.getJSONArray("empleados"); // Obtener el array "empleados" del objeto JSON
        double salarioTotal = 0;
        int empleadosActivos = 0;

        for (int i = 0; i < empleados.length(); i++) {
            JSONObject empleado = empleados.getJSONObject(i); // Obtener cada empleado como un objeto JSON
            boolean estaActivo = empleado.getBoolean("activo"); // Comprobar si el empleado está activo
            if (estaActivo) {
                // Comprobar si el salario no es nulo
                if (!empleado.isNull("salario")) {
                    double salario = empleado.getDouble("salario"); // Obtener el salario del empleado
                    salarioTotal += salario; // Sumar el salario al total
                    empleadosActivos++; // Incrementar el número de empleados activos
                }
            }
        }

        return empleadosActivos > 0 ? salarioTotal / empleadosActivos : 0; // Retornar el salario medio
    }

    // Método para contar el total de proyectos en el archivo JSON
    public int contarProyectos(JSONObject jsonObject) {
        JSONArray proyectos = jsonObject.getJSONArray("proyectos"); // Obtener el array "proyectos" del objeto JSON
        return proyectos.length(); // Retornar el número de proyectos
    }

    // Método para guardar la información solicitada en un archivo de texto
    public void guardarInformacionEmpleados(JSONObject jsonObject, double salarioMedio, int totalProyectos) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/resources/informacion_empleados.txt"))) {
            bw.write("Información de empleados:\n");
            bw.write(jsonObject.toString(2)); // Escribir el contenido del JSON con formato bonito
            bw.newLine();
            bw.write("Salario medio de empleados activos: " + salarioMedio); // Escribir el salario medio
            bw.newLine();
            bw.write("Número total de proyectos: " + totalProyectos); // Escribir el número de proyectos
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo: " + e.getMessage()); // Manejar errores de escritura en el archivo
        }
    }
}

*
*
*
*
*
*
* */