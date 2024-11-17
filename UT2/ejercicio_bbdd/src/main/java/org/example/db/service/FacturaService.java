package org.example.db.service;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.Utils.GenericUtils;
import org.example.constansts.GenericConstants;
import org.example.db.dao.FacturaDAO;
import org.example.db.model.Factura;
import org.example.constansts.FacturaConstants;





import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.db.conection.MYSQLConnection;
import org.example.db.dao.FacturaDAO;
import org.example.db.model.Factura;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FacturaService extends GenericServiceImpl<Factura, Integer> {

    private static final Logger logger = LogManager.getLogger(FacturaService.class);
    private final FacturaDAO facturaDAO;

    public FacturaService() {
        super(new FacturaDAO());
        this.facturaDAO = new FacturaDAO();
    }

    @Override
    public int insertar(Factura factura) {
        // Validación previa de los datos
        int validacionResultado = comprobarFactura(factura);
        if (validacionResultado != 0) {
            return validacionResultado;
        }

        Connection conexion = MYSQLConnection.getInstancia().getConnection();
        try {
            // Desactivar autocommit
            conexion.setAutoCommit(false);
            logger.info("Autocommit desactivado para la operación de inserción.");

            // Insertar factura
            int resultado = facturaDAO.insertar(factura);

            // Confirmar la transacción
            conexion.commit();
            logger.info("Transacción de inserción confirmada con éxito.");
            return resultado;

        } catch (SQLException e) {
            try {
                // Deshacer la transacción en caso de error
                if (conexion != null) {
                    conexion.rollback();
                    logger.error("Error en la inserción. Se realizó rollback.", e);
                }
            } catch (SQLException rollbackEx) {
                logger.error("Error al intentar hacer rollback de la transacción.", rollbackEx);
            }
            return -1; // Código de error (podrías definir un código específico en lugar de -1)
        } finally {
            try {
                if (conexion != null) {
                    // Restaurar autocommit al estado original
                    conexion.setAutoCommit(true);
                    logger.info("Autocommit restaurado a su estado original.");
                }
            } catch (SQLException e) {
                logger.error("Error al restaurar el autocommit.", e);
            }
        }
    }

    @Override
    public Factura obtenerPorId(Integer id) {
        if (id == null) {
            logger.error("No se puede buscar con valores nulos");
            return null;
        }
        return super.obtenerPorId(id);
    }

    // Método para actualizar una factura con manejo de transacciones
    public int actualizar(Factura factura) {
        int validacionResultado = comprobarFactura(factura);
        if (validacionResultado != 0) {
            return validacionResultado;
        }

        Connection conexion = MYSQLConnection.getInstancia().getConnection();
        try {
            conexion.setAutoCommit(false);
            logger.info("Autocommit desactivado para la operación de actualización.");

            int resultado = facturaDAO.actualizar(factura);
            conexion.commit();
            logger.info("Transacción de actualización confirmada con éxito.");
            return resultado;

        } catch (SQLException e) {
            try {
                if (conexion != null) {
                    conexion.rollback();
                    logger.error("Error en la actualización. Se realizó rollback.", e);
                }
            } catch (SQLException rollbackEx) {
                logger.error("Error al intentar hacer rollback de la transacción.", rollbackEx);
            }
            return -1;
        } finally {
            try {
                if (conexion != null) {
                    conexion.setAutoCommit(true);
                    logger.info("Autocommit restaurado a su estado original.");
                }
            } catch (SQLException e) {
                logger.error("Error al restaurar el autocommit.", e);
            }
        }
    }

    // Método para eliminar una factura con manejo de transacciones
    public int eliminar(Integer id) {
        if (id == null) {
            logger.error("No se puede eliminar con valores nulos");
            return -1;
        }

        Connection conexion = MYSQLConnection.getInstancia().getConnection();
        try {
            conexion.setAutoCommit(false);
            logger.info("Autocommit desactivado para la operación de eliminación.");

            int resultado = facturaDAO.eliminar(id);
            conexion.commit();
            logger.info("Transacción de eliminación confirmada con éxito.");
            return resultado;

        } catch (SQLException e) {
            try {
                if (conexion != null) {
                    conexion.rollback();
                    logger.error("Error en la eliminación. Se realizó rollback.", e);
                }
            } catch (SQLException rollbackEx) {
                logger.error("Error al intentar hacer rollback de la transacción.", rollbackEx);
            }
            return -1;
        } finally {
            try {
                if (conexion != null) {
                    conexion.setAutoCommit(true);
                    logger.info("Autocommit restaurado a su estado original.");
                }
            } catch (SQLException e) {
                logger.error("Error al restaurar el autocommit.", e);
            }
        }
    }

    // Método para buscar facturas por destinatario
   /* public List<Factura> buscarPorDestinatario(String destinatario) {
        if (destinatario == null || destinatario.isEmpty()) {
            logger.error("El destinatario no puede estar vacío o ser nulo para la búsqueda.");
            return null;
        }
        return facturaDAO.buscarPorDestinatario(destinatario);
    }*/

    private int comprobarFactura(Factura factura) {
        // Validación: el objeto `factura` no debe ser nulo
        if (factura == null) {
            logger.error("No se pueden insertar valores nulos en la factura.");
            return GenericConstants.ERROR_PARAM_NULL;
        }

        // Validación: `codigo` no debe ser nulo
        if (factura.getCodigo() == null) {
            logger.error("El código de la factura no puede ser nulo.");
            return FacturaConstants.ERROR_CODIGO_NULO;
        }

        // Validación: `destinatario` no debe ser nulo, vacío, o en blanco
        if (factura.getDestinatario() == null || factura.getDestinatario().trim().isEmpty()) {
            logger.error("El destinatario no puede estar vacío o ser nulo.");
            return FacturaConstants.ERROR_DESTINATARIO_NULO;
        } else if (factura.getDestinatario().length() > FacturaConstants.TABLE_DESTINATARIO_MAX_LONG) {
            // Limitar el tamaño del destinatario si excede la longitud máxima permitida
            factura.setDestinatario(GenericUtils.limitarString(factura.getDestinatario(), FacturaConstants.TABLE_DESTINATARIO_MAX_LONG));
        }

        // Validación: `cuenta` no debe ser nulo
        if (factura.getCuenta() == null) {
            logger.error("La cuenta no puede ser nula.");
            return FacturaConstants.ERROR_CUENTA_NULO;
        }

        // Validación: `importe` no debe ser nulo y debe estar dentro de los límites permitidos
        if (factura.getImporte() == null) {
            logger.error("El importe no puede ser nulo.");
            return FacturaConstants.ERROR_IMPORTE_NULO;
        } else if (!GenericUtils.esValidoParaDecimal(factura.getImporte(), FacturaConstants.ERROR_IMPORTE_DEMASIADO_GRANDE)) {
            logger.error("El importe supera el límite permitido de dígitos.");
            return FacturaConstants.ERROR_IMPORTE_DEMASIADO_GRANDE;
        }

        return 0; // Si pasa todas las validaciones, retorna 0 indicando éxito
    }

    public List<Factura> buscarPorDestinatario(String destinatario) {
        // Verificar si el destinatario es nulo o vacío antes de proceder
        if (destinatario == null || destinatario.trim().isEmpty()) {
            logger.error("El destinatario no puede estar vacío o ser nulo para la búsqueda.");
            return Collections.emptyList();
        }

        List<Factura> facturas = new ArrayList<>();
        try {
            // Llamar al método en FacturaDAO para realizar la búsqueda en la base de datos
            facturas = facturaDAO.buscarPorDestinatario(destinatario);
            logger.info("Se encontraron " + facturas.size() + " facturas para el destinatario: " + destinatario);
        } catch (SQLException e) {
            logger.error("Error al buscar facturas por destinatario: " + destinatario, e);
        }
        return facturas;
    }

}



