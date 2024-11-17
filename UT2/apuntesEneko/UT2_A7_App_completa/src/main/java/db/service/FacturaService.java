package db.service;

import db.connection.MySQLConnection;
import db.dao.FacturaDAO;
import db.model.Factura;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import utils.GenericUtils;
import constants.GenericConstants;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static constants.FacturaConstants.*;

public class FacturaService extends GenericServiceImpl<Factura, Integer> {

    // Crea una instancia del Logger
    private static final Logger logger = LogManager.getLogger(FacturaService.class);

    public FacturaService() {
        super(new FacturaDAO());
    }

    @Override
    public int insertar(Factura factura) {
        int comprobarFactura = comprobarFactura(factura);
        Connection connection = MySQLConnection.getInstance().getConnection();
        if (comprobarFactura != 0)
            return comprobarFactura;
        int resultado = -1;
        try{
            connection.setAutoCommit(false);
            logger.info("Auto commit desactivado.");

            resultado = super.insertar(factura);
            connection.commit();
            logger.info("Factura creada correctamente");
        } catch (SQLException e){
            logger.error("Error en la creación de la factura: ", e.fillInStackTrace());
            try{
                connection.rollback();
                logger.info("Rollback en la creación de la factura realizado con éxito.");
            } catch (SQLException ex){
                logger.error("Error al hacer rollback en la creación de la factura: ", ex.fillInStackTrace());
            }
        } finally {
            try{
                connection.setAutoCommit(true);
                logger.info("Auto commit activado.");
            } catch (SQLException e) {
                logger.error("Error en la activación del auto commit: ", e.fillInStackTrace());
            }
        }
        return resultado;
    }

    @Override
    public Factura obtenerPorId(Integer id) {
        if (id == null) {
            logger.error("No se puede buscar con valores nulos");
            return null;
        }
        Factura factura = null;
        Connection connection = MySQLConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            logger.info("Auto commit desactivado.");

            factura = super.obtenerPorId(id);
            connection.commit();
            logger.info("Factura obtenida correctamente");
        } catch (SQLException e){
            logger.error("Error en la obtención de la factura: ", e.fillInStackTrace());
            try{
                connection.rollback();
                logger.info("Rollback en la obtención de la factura realizado con éxito.");
            } catch (SQLException ex){
                logger.error("Error al hacer rollback en la obtención de la factura: ", ex.fillInStackTrace());
            }
        } finally {
            try{
                connection.setAutoCommit(true);
                logger.info("Auto commit activado.");
            } catch (SQLException e) {
                logger.error("Error en la activación del auto commit: ", e.fillInStackTrace());
            }
        }
        return factura;
    }


    public List<Factura> obtenerPorDestinatario(String destinatario) {
        if (destinatario == null) {
            logger.error("No se puede buscar con valores nulos");
            return null;
        }
        List<Factura> facturas = new ArrayList<>();
        FacturaDAO  facturaDAO = (FacturaDAO) super.getGenericDAO();
        Connection connection = MySQLConnection.getInstance().getConnection();
        try{
            connection.setAutoCommit(false);
            logger.info("Auto commit desactivado.");

            facturas = facturaDAO.obtenerPorDestinatario(destinatario);
            connection.commit();
            logger.info("Facturas obtenidas correctamente");
        } catch (SQLException e){
            logger.error("Error al obtener la lista de facturas: ", e.fillInStackTrace());
            try{
                connection.rollback();
                logger.info("Rollback realizado con éxito.");
            } catch (SQLException ex){
                logger.error("Error al hacer rollback: ", ex.fillInStackTrace());
            }
        } finally {
            try{
                connection.setAutoCommit(true);
                logger.info("Auto commit activado.");
            } catch (SQLException e) {
                logger.error("Error en la activación del auto commit: ", e.fillInStackTrace());
            }
        }
        return  facturas;
    }

    @Override
    public int actualizar(Factura factura) {
        int comprobarFactura = comprobarFactura(factura);
        if (comprobarFactura != 0)
            return comprobarFactura;

        int resultado = -1;
        Connection connection = MySQLConnection.getInstance().getConnection();
        try{
            connection.setAutoCommit(false);
            logger.info("Auto commit desactivado.");

            resultado = super.actualizar(factura);
            connection.commit();
            logger.info("Factura actualizada correctamente");
        } catch (SQLException e){
            logger.error("Error en la actualización de la factura: ", e.fillInStackTrace());
            try{
                connection.rollback();
                logger.info("Rollback en la actualización de la factura realizado con éxito.");
            } catch (SQLException ex){
                logger.error("Error al hacer rollback en la actualización de la factura: ", ex.fillInStackTrace());
            }
        } finally {
            try{
                connection.setAutoCommit(true);
                logger.info("Auto commit activado.");
            } catch (SQLException e) {
                logger.error("Error en la activación del auto commit: ", e.fillInStackTrace());
            }
        }
        return resultado;
    }

    @Override
    public int eliminar(Integer id) {
        if (id == null) {
            logger.error("No se puede eliminar un código nulo");
            return GenericConstants.ERROR_PARAM_NULL;
        }

        int resultado = -1;
        Connection connection = MySQLConnection.getInstance().getConnection();
        try{
            connection.setAutoCommit(false);
            logger.info("Auto commit desactivado.");

            resultado = super.eliminar(id);
            connection.commit();
            logger.info("Factura eliminada correctamente");
        } catch (SQLException e){
            logger.error("Error en la eliminación de la factura: ", e.fillInStackTrace());
            try{
                connection.rollback();
                logger.info("Rollback en la eliminación de la factura realizado con éxito.");
            } catch (SQLException ex){
                logger.error("Error al hacer rollback en la eliminación de la factura: ", ex.fillInStackTrace());
            }
        } finally {
            try{
                connection.setAutoCommit(true);
                logger.info("Auto commit activado.");
            } catch (SQLException e) {
                logger.error("Error en la activación del auto commit: ", e.fillInStackTrace());
            }
        }
        return resultado;
    }

    private int comprobarFactura(Factura factura) {
        // Comprobar Factura
        if (factura == null) {
            logger.error("No se pueden insertar valores nulos");
            return GenericConstants.ERROR_PARAM_NULL;
        }

        // Comprobar el código
        if (factura.getCodigo() == null) {
            logger.error("No se admite un código nulo");
            return ERROR_CODIGO_NULO;
        }

        // Comprobar el destinatario
        if (factura.getDestinatario() == null
                || factura.getDestinatario().isEmpty()
                || factura.getDestinatario().isBlank()) {
            logger.error("El destinatario no puede estar vacío o ser nulo");
            return ERROR_DESTINATARIO_NULO;
        } else if (factura.getDestinatario().length() > TABLE_DESTINATARIO_MAX_LONG)
            factura.setDestinatario(GenericUtils.limitarString(factura.getDestinatario(),
                    TABLE_DESTINATARIO_MAX_LONG));

        // Comprobar la cuenta
        if (factura.getCuenta() == null) {
            logger.error("No se admite un número de cuenta nulo");
            return ERROR_CUENTA_NULO;
        }

        // Comprobar importe
        if (factura.getImporte() == null) {
            logger.error("No se admite importe nulo o vacío");
            return ERROR_IMPORTE_NULO;
        }
        else if (!GenericUtils.esValidoParaDecimal(factura.getImporte(), TABLE_IMPORTE_MAX_ENTEROS)) {
            logger.error("No se admiten importes con más de " + TABLE_IMPORTE_MAX_ENTEROS + " números enteros");
            return ERROR_IMPORTE_DEMASIADO_GRANDE;
        }

        // Comprobar fecha y hora
        if (factura.getFechaHora() == null) {
            logger.error("No se admiten fecha y hora nulos");
            return ERROR_FECHA_NULO;
        }

        return 0;
    }
}
