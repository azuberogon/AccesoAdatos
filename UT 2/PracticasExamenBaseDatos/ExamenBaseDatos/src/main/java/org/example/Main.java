package org.example;

import org.example.db.dao.FacturaDao;
import org.example.db.model.Factura;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_RESET = "\u001B[0m";

    private Integer codigo;
    private String destinatario;
    private Integer cuenta;
    private Double importe;
    private LocalDateTime fecha_hora;


    public static void main(String[] args) {
        System.out.println("Arrancando servidor!");
        String str = " ";
        //while(!str.equalsIgnoreCase("s")){
            try {
                FacturaDao facturaDao = new FacturaDao();

                Factura facturaNueva = new Factura(1,"putoedu",10,7.5, LocalDateTime.now());
                System.out.println("metiendo la factura ");
                facturaDao.insertar(facturaNueva);

                for (Factura factura : facturaDao.obtenerTodos()) {
                    System.out.println(factura);
                }
            }catch (SQLException SQLE){
                System.out.println("Error en el servidor:"+ SQLE.fillInStackTrace());
            }




        //}


    }
}