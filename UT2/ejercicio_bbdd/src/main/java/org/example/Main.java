package org.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.db.conection.MYSQLConnection;
import org.example.db.dao.FacturaDAO;
import org.example.db.model.Factura;


public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class.getName());

    public static void main(String[] args) {
        System.out.println("-----------------------------------------");
        System.out.println("------- UT2_A3 - Metadatos en BDR -------");
        System.out.println("-----------------------------------------");

        Scanner sc = new Scanner(System.in);
        System.out.println("¿Quieres crear la tabla de Facturas?");
        System.out.println("(1=Sí)");
        System.out.println("(Cualquier otra cosa = No)");
        String respuesta = sc.nextLine();
        if (respuesta.equals("1")) {
            try {
                ejecutarSQL("src/main/resources/SQL/facturas.sql");
            } catch (IOException e) {
                logger.error("Error al leer el fichero: ", e.fillInStackTrace());
                System.exit(-1);
            } catch (SQLException e) {
                logger.error("Error al ejecutar el fichero de creación de Facturas: ", e.fillInStackTrace());
                System.exit(-1);
            }
        }


        System.out.println("¿Quieres probar el CRUD de Facturas?");
        System.out.println("1=Sí");
        System.out.println("Cualquier otra cosa = No");
        respuesta = sc.nextLine();
        if (respuesta.equals("1")) {
            testCRUD();
        }

        

        // Obtenemos la conexión
        Connection connection = MYSQLConnection.getInstancia().getConnection();

        try {
            // Obtener metadatos de la BDR
            DatabaseMetaData dbMetaData = connection.getMetaData();
           // mostrarMetadatos(dbMetaData);
            mostrarFactura();
            FacturaDAO facturaDAO = new FacturaDAO();
            //insertarFactura(facturaDAO);
            testCRUD();
        } catch (SQLException e) {
            logger.error("Ha ocurrido un error al obtener los metadatos: ", e.fillInStackTrace());
        }
    }
    private static void testCRUD() {
        FacturaDAO facturaDAO = new FacturaDAO();
        insertarFactura(facturaDAO);
        consultarTodasLasFacturas(facturaDAO);
        actualizarFactura(facturaDAO);
        /*error por un null*/

        consultarFactura(facturaDAO);
        eliminarFactura(facturaDAO);
    }

    private static void eliminarFactura(FacturaDAO facturaDAO) {
        Factura factura = new Factura(2,"teset2",77777777,122222.0);
        try{
            facturaDAO.eliminar(factura.getCodigo());

        }catch (SQLException SQLE){
            System.out.println("Error en la eliminacion de la factura" + SQLE);
        }
    }

    private static void consultarFactura(FacturaDAO facturaDAO) {
        //Factura factura = new Factura("teset2",77777777,122222.0);

        try {
            System.out.println("factura buscada: " + facturaDAO.obtenerPorId(2));
        }catch (SQLException SQLE){
            System.out.println("Error en al consultar la factura: " +2 + " error: " + SQLE );
        }


    }

    private static void actualizarFactura(FacturaDAO facturaDAO) {
        Factura factura = new Factura(2,"teset2Actualizada",111111,0.0);
        try{
            facturaDAO.actualizar(factura);
        }catch (SQLException SQLE){
            System.out.println("error en la base de datos "+ SQLE);
        }

    }

    private static void consultarTodasLasFacturas(FacturaDAO facturaDAO) {
        try{
            List<Factura> facturas = facturaDAO.obtenerTodos();
            for (Factura factura : facturas) {
                System.out.println(factura);
            }
        }catch (SQLException SQLE){
            System.out.println("Error en al consultar todas las facturas error: " + SQLE);
        }

    }


    /**
     * codigo para insertar una linea de test, aqui genera la factura, despues
     * */
    private static void insertarFactura(FacturaDAO facturaDAO) {
       // LocalDateTime hora = LocalDateTime.of(2024,01,10,7,10,27);
        Factura factura = new Factura(2,"teset2",77777777,122222.0);
        try {
            facturaDAO.insertar(factura);
        }catch (SQLException SQLE){
            System.out.println("Error de sql " + SQLE);
        }
    }
    private static void insertar1Factura(){
        String sql = "INSERT INTO facturas (CODIGO, destinatario, cuenta, importe, fecha_hora) VALUES (?, ?, ?, ?, ?)";
        Connection conect = MYSQLConnection.getInstancia().getConnection();
        try(PreparedStatement pstmt =conect.prepareStatement(sql)){
            LocalDateTime hora = LocalDateTime.of(2024,01,10,7,10,27);
            pstmt.setInt(1,1);
            pstmt.setString(2,"test1");
            pstmt.setInt(3,77777777);
            pstmt.setDouble(4,12.05);
            pstmt.setTimestamp(5,Timestamp.valueOf(hora)); // linea de tiempo que puede dar error a la hora de guardar horas
            int filasAfectadas = pstmt.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Factura insertada correctamente.");
            } else {
                System.out.println("No se pudo insertar la factura.");
            }
        }catch (SQLException sqlException){
            System.out.println("error en la sql:" + sqlException);
        }
    }

    private static void ejecutarSQL(String fichero) throws IOException, SQLException {
        String contenidoFicheroSQL = leerFicheroSQL(fichero);
        Connection connection = MYSQLConnection.getInstancia().getConnection();

        // Verificar si la conexión es válida
        if (connection != null) {
            try (Statement stmt = connection.createStatement()) {
                // Dividir el contenido en sentencias separadas por puntos y coma (;)
                String[] sentencias = contenidoFicheroSQL.split(";");

                // Ejecutar cada sentencia por separado
                for (String sentencia : sentencias) {
                    // Ejecutar solo si la sentencia no está vacía
                    if (!sentencia.trim().isEmpty()) {
                        stmt.executeUpdate(sentencia);
                    }
                }
            }
        }
    }



    public static String leerFicheroSQL(String sqlFilePath) throws IOException {
        File archivoSQL = new File(sqlFilePath);

        // Verificar si el archivo existe
        if (!archivoSQL.exists()) {
            throw new IOException("El archivo SQL no existe en la ruta especificada: " + sqlFilePath);
        }

        // Leer el archivo SQL y construir el script en un StringBuilder
        StringBuilder script = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(archivoSQL))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Añadir cada línea al script con el separador
                script.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            // Excepción si ocurre un problema al leer el archivo
            throw new IOException("Error al leer el archivo SQL: " + e.getMessage(), e);
        }

        return script.toString();
    }


    // Función encargada de mostrar metadatos
    private static void mostrarMetadatos(DatabaseMetaData dbMetaData) throws SQLException {
        // Nombre y versión del SGBD
        String dbProductName = dbMetaData.getDatabaseProductName();
        String dbProductVersion = dbMetaData.getDatabaseProductVersion();
        System.out.println("\n\n-----------------------------------------");
        System.out.println("SGBD: " + dbProductName + "\t\tVersión: " + dbProductVersion);
        System.out.println("-----------------------------------------");

        // Usuario actual
        System.out.println("Usuario actual: " + dbMetaData.getUserName());
        System.out.println("-----------------------------------------\n");

        // Mostramos los metadatos de todas las tablas
        mostrarTablas(dbMetaData);

    }

    
    private static void mostrarFactura(){
        FacturaDAO facturaDAO = new FacturaDAO();
        try {
            List<Factura> listaFactura = facturaDAO.obtenerTodos();
            for (Factura factura : listaFactura) {
                /*System.out.println(
                        factura.getCodigo()+
                        factura.getDestinatario()+
                        factura.getCuenta()+
                        factura.getImporte()+
                        factura.getFecha_hora()
                );*/
                System.out.println(factura);


            }
        }catch (SQLException SQLE){
            System.out.println("Error en la conexion a la sql "+SQLE );

        }

    }



    // Muestra las tablas de la BDR
    private static void mostrarTablas(DatabaseMetaData dbMetaData) throws SQLException {
        ResultSet rsTablas = dbMetaData.getTables(
                MYSQLConnection.getDB(), null, null, new String[] {"TABLE"});

        while (rsTablas.next()) {
            String tabla = rsTablas.getString("TABLE_NAME");
            System.out.println("TABLA: " + tabla);
            mostrarColumnas(dbMetaData, tabla);
            mostrarClavesPrimarias(dbMetaData, tabla);
            mostrarClavesAjenas(dbMetaData, tabla);
            System.out.println("\n\n");
        }
    }

    // Muestra las columnas de una tabla
    private static void mostrarColumnas(DatabaseMetaData dbMetaData, String tabla) throws SQLException {
        ResultSet rsColumnas = dbMetaData.getColumns(
                MYSQLConnection.getDB(), null, tabla, null);

        while (rsColumnas.next()) {
            System.out.println("\t" + rsColumnas.getString("COLUMN_NAME") + ": " + rsColumnas.getString("TYPE_NAME"));
        }
    }

    // Muestra las claves primarias de una tabla
    private static void mostrarClavesPrimarias(DatabaseMetaData dbMetaData, String tabla) throws SQLException {
        ResultSet rsPK = dbMetaData.getPrimaryKeys(
                MYSQLConnection.getDB(), null, tabla);
        System.out.println("\t\tClaves Primarias:");

        while (rsPK.next()) {
            System.out.println("\t\t\t" + rsPK.getString("COLUMN_NAME"));
        }
    }

    // Muestra las claves ajenas de una tabla
    private static void mostrarClavesAjenas(DatabaseMetaData dbMetaData, String tabla) throws SQLException {
        ResultSet rsFK = dbMetaData.getImportedKeys(
                MYSQLConnection.getDB(), null, tabla);
        System.out.println("\t\tClaves Ajenas:");

        while (rsFK.next()) {
            System.out.println("\t\t\t" + rsFK.getString("FKCOLUMN_NAME") + " --> " + rsFK.getString("PKTABLE_NAME"));
        }
    }
}
