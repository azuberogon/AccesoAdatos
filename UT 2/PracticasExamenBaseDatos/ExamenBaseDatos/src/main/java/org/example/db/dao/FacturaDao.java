package org.example.db.dao;

import org.example.db.conection.MySQLConnection;
import org.example.db.model.Factura;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class FacturaDao implements GenericDao<Factura,Integer> {
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_RESET = "\u001B[0m";
    @Override
    public void insertar(Factura entity) throws SQLException {
        if (obtenerPorId(entity.getCodigo())==null){

            String sql = "INSERT INTO facturas (codigo, destinatario, cuenta, importe, fecha_hora) " +
                    "VALUES (?,?,?,?,?)";
            Connection con = MySQLConnection.getInstance().getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, entity.getCodigo());
            pstmt.setString(2, entity.getDestinatario());
            pstmt.setInt(3, entity.getCuenta());
            pstmt.setDouble(4, entity.getImporte());
            pstmt.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));
           // pstmt.setString(5, entity.getFechaHora().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));;
            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted>0){
                System.out.println(ANSI_BLUE+"se hizo la inserccion de la factura "+entity.toString()+ANSI_RESET);
            }else {
                System.out.println(ANSI_RED +"la factura no se pudo introducir en la base de datos "+ANSI_RESET);
            }
            pstmt.close();
        }else {
            System.out.println("la factura ya existe");
        }



    }

    @Override
    public List<Factura> obtenerTodos() throws SQLException {
        List<Factura> facturas = new ArrayList<>();
        String sql = "SELECT * FROM facturas";
        Connection con = MySQLConnection.getInstance().getConnection();
        PreparedStatement pstmt = con.prepareStatement(sql);
        ResultSet RSFactura = pstmt.executeQuery();
        while (RSFactura.next()){
            facturas.add(new Factura(RSFactura.getInt("codigo"),
                    RSFactura.getString("destinatario"),
                    RSFactura.getInt("cuenta"),
                    RSFactura.getDouble("importe"),
                    RSFactura.getTimestamp("fecha_hora").toLocalDateTime()));
        }
        return facturas;
    }

    @Override
    public Factura obtenerPorId(Integer id) throws SQLException {
        String sql = "SELECT * FROM facturas WHERE codigo = ?";
        Connection con = MySQLConnection.getInstance().getConnection();
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setInt(1,id);//codigo tambien puede ir aqui
        ResultSet RSFactura = pstmt.executeQuery();
        while (RSFactura.next()){

            Factura factura = new Factura(RSFactura.getInt("codigo"),
                    RSFactura.getString("destinatario"),
                    RSFactura.getInt("cuenta"),
                    RSFactura.getDouble("importe"),
                    RSFactura.getTimestamp("fecha_hora").toLocalDateTime());
            if (factura !=null){
                pstmt.close();
                System.out.println(ANSI_BLUE+"Factura encontrada"+ANSI_RESET);
                return factura;
            }
        }
        pstmt.close();
        System.out.println(ANSI_RED+"  Factura no encontrada codigo:" + ANSI_GREEN +id+ANSI_RESET);
        return null;
    }

    @Override
    public void actualizar(Factura entity) throws SQLException {
        String sql = "UPDATE facturas SET " +
                "destinatario = ?"
                +"cuenta = ?"
                +"importe = ?"
                +"fecha_hora = ?"
                +"WHERE = ? ";
        Connection con = MySQLConnection.getInstance().getConnection();
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1,entity.getDestinatario());
        pstmt.setInt(2,entity.getCuenta());
        pstmt.setDouble(3,entity.getImporte());
        pstmt.setTimestamp(4,Timestamp.valueOf(entity.getFechaHora()));
        pstmt.setInt(5,entity.getCodigo());
        int rowsInserted = pstmt.executeUpdate();
        if (rowsInserted>0){
            System.out.println(ANSI_BLUE+"La actualizacion se llevo con existo en la base de datos "+ANSI_RESET);
        }else {
            System.out.println(ANSI_RED +"Error en la iserccion en la base de datos revisar el codigo "+ANSI_GREEN + entity.getCodigo()+ANSI_RESET);
        }




    }

    @Override
    public void eliminar(Integer id) throws SQLException {
        String sql = "DELTE FROM facturas WHERE codigo = ?";
        Connection con = MySQLConnection.getInstance().getConnection();
        PreparedStatement pstmt = con.prepareStatement(sql);
        int rowsInserted = pstmt.executeUpdate();
        if (rowsInserted>0){
            System.out.println("factur eliminada de la base de datos");
        }else {
            System.out.println(ANSI_RED +"Error, no se ah eliminado la factura del servidor.");
        }

    }
}
