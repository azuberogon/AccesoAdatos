package db.service;

import constants.ContieneConstants;
import constants.GenericConstants;
import constants.PedidoConstants;
import constants.VideojuegoConstants;
import db.connection.MySQLConnection;
import db.dao.PedidoDAO;
import db.model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static constants.VideojuegoConstants.ERROR_BORRANDO_VJ_NO_EXISTE;

public class PedidoService extends GenericServiceImpl<Pedido, Integer> {

    private static final Logger logger = LogManager.getLogger(PedidoService.class);

    private static final ClienteService clienteService = new ClienteService();
    private static final ContieneService contieneService = new ContieneService();

    public PedidoService() {
        super(new PedidoDAO());
    }

    @Override
    public int insertar(Pedido pedido) {
        int comprobarPedido = comprobarPedido(pedido);
        if (comprobarPedido != 0)
            return comprobarPedido;

        // Compruebo que tenga contienes el pedido
        if (pedido.getContienes() == null
                || pedido.getContienes().isEmpty()) {
            logger.warn("No se puede crear un pedido sin contienes asociados");
            return PedidoConstants.ERROR_PEDIDO_SIN_CONTENIDO;
        }

        Connection connection = MySQLConnection.getInstance().getConnection();
        int pedidoID = 0;
        try {
            connection.setAutoCommit(false);

            // Crear pedido
            int resultado = super.insertar(pedido);
            if (resultado <= 0) {
                logger.warn("No se ha podido crear el pedido");
                connection.rollback();
            } else {
                pedidoID = resultado;
                // Creo todos los contienes asociados al pedido
                for (Contiene contiene : pedido.getContienes()) {
                    contiene.getContieneID().setPedidoID(pedidoID);
                    resultado = contieneService.insertar(contiene);
                    if (resultado < 0) {
                        logger.warn("No se ha podido crear el contiene pedidoID[{}] videojuegoID[{}]: ",
                                contiene.getContieneID().getPedidoID(), contiene.getContieneID().getVideojuegoID());
                        break;
                    }
                }

                if (resultado <= 0) {
                    logger.warn("No se puede crear el pedido por fallos al crear los contiene asociados. Código error: {}", resultado);
                    connection.rollback();
                } else {
                    connection.commit();
                    logger.trace("Pedido creado correctamente");
                }
            }
        } catch (SQLException e) {
            logger.error("Ha ocurrido un error inesperado al crear el pedido. Se hace rollback: ", e.fillInStackTrace());
            pedidoID = GenericConstants.ERROR_SQL_EXCEPTION;
            try { connection.rollback(); } catch (SQLException ex) {
                logger.error("No se ha podido hacer rollback: ", ex.fillInStackTrace());
            }
        } finally {
            try { connection.setAutoCommit(true); } catch (SQLException e) {
                logger.error("No se ha podido volver a activar el autoCommit: ", e.fillInStackTrace());
            }
        }
        return pedidoID;
    }

    @Override
    public int actualizar(Pedido pedido) {
        int comprobarPedido = comprobarPedido(pedido);
        if (comprobarPedido != 0)
            return comprobarPedido;
        return super.actualizar(pedido);
    }

    @Override
    public int eliminar(Integer id) {
        int resultado;

        // TODO START
        Pedido pedido = obtenerPorId(id);
        if(comprobarPedido(pedido) != 0) return ERROR_BORRANDO_VJ_NO_EXISTE;

        // Compruebo si el videojuego está en algún pedido, ya que de ser así no se podrá borrar para mantener la integridad
        List<Contiene> contienes = contieneService.obtenerContienesPedido(id);
        if (contienes == null) {
            logger.warn("No se han podido comprobar los contiene asociados al videojuego por un error inesperado");
            return ContieneConstants.ERROR_CANTIDAD_NULA_O_NEGATIVA;
        }
        Connection connection = MySQLConnection.getInstance().getConnection();

        try {
            connection.setAutoCommit(false);


            // Eliminar pedido
            resultado = super.eliminar(id);
            if (resultado != 0) {
                // No se ha podido borrar el pedido
                logger.warn("No se ha podido borrar el pedido {}", id);
               return GenericConstants.ERROR_SQL_EXCEPTION;
            }

            if (resultado == 0){
                // Eliminar contienes asociados
                for (Contiene contiene : contienes) {
                    resultado = contieneService.eliminar(contiene.getContieneID());
                    if (resultado != 0) {
                        logger.warn("No se ha podido borrar los contienes asociados. Se hace rollback");
                        connection.rollback();
                    }
                }
            }else {
                connection.commit();
                logger.trace("Pedido borrado con éxito");
            }
        } catch (SQLException e) {
            logger.error("Ha ocurrido un error inesperado al borrar el videojuego. Se hace rollback: ", e.fillInStackTrace());
            resultado = GenericConstants.ERROR_SQL_EXCEPTION;
            try { connection.rollback(); } catch (SQLException ex) {
                logger.error("No se ha podido hacer rollback: ", ex.fillInStackTrace());
            }
        } finally {
            try { connection.setAutoCommit(true); } catch (SQLException e) {
                logger.error("No se ha podido volver a activar el autoCommit: ", e.fillInStackTrace());
            }
        }
        // TODO END

        return resultado;
    }

        private int comprobarPedido(Pedido pedido) {
        if (pedido == null) {
            logger.warn("No se pueden insertar pedidos nulos");
            return GenericConstants.ERROR_PARAM_NULL;
        }

        // Comprobar que el cliente no sea nulo
        if (pedido.getCliente() == null) {
            logger.warn("No se pueden insertar pedidos con clientes nulos");
            return PedidoConstants.ERROR_CLIENTE_NULO;
        }

        // Comprobar que el cliente existe
        Cliente cliente = clienteService.obtenerPorId(pedido.getCliente().getClienteID());
        if (cliente == null) {
            logger.warn("El cliente id {} asociado al pedido no existe en la tabla clientes",
                    pedido.getCliente().getClienteID());
            return PedidoConstants.ERROR_CLIENTE_NO_EXISTE;
        }

        return 0;
    }

    public List<Pedido> obtenerPedidosCliente(Integer clienteID) {
        if (clienteID == null) {
            logger.warn("No se pueden buscar pedidos con un cliente nulo");
            return null;
        }

        List<Pedido> pedidos = null;
        PedidoDAO pedidoDAO = (PedidoDAO) getGenericDAO();
        try {
            pedidos = pedidoDAO.obtenerPedidosCliente(clienteID);
        } catch (SQLException e) {
            logger.error("Error al obtenerPedidosCliente con el clienteID {} en la BD: ",
                    clienteID, e.fillInStackTrace());
            return null;
        }
        return pedidos;
    }
}
