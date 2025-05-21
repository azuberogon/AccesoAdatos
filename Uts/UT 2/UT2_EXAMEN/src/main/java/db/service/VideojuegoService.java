package db.service;

import constants.ContieneConstants;
import constants.GenericConstants;
import constants.VideojuegoConstants;
import db.connection.MySQLConnection;
import db.dao.VideojuegoDAO;
import db.model.Historia;
import db.model.Pedido;
import db.model.Videojuego;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.GenericUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static constants.VideojuegoConstants.*;

public class VideojuegoService extends GenericServiceImpl<Videojuego, Integer> {

    // Crea una instancia del Logger
    private static final Logger logger = LogManager.getLogger(VideojuegoService.class);

    private static final HistoriaService historiaService = new HistoriaService();
    private static final PedidoService pedidoService = new PedidoService();

    public VideojuegoService() {
        super(new VideojuegoDAO());
    }

    @Override
    public int insertar(Videojuego videojuego) {
        int resultado = -1;

        // TODO START

        if(comprobarVideojuego(videojuego) != 0){
            return comprobarVideojuego(videojuego);
        }

        Connection connection = MySQLConnection.getInstance().getConnection();

        try {
            connection.setAutoCommit(false);
            logger.info("AutoCommit desactivado");

            int historiaID = historiaService.insertar(videojuego.getHistoria());


            if(historiaID <= 0){
                return ERROR_DURANTE_CREACION_HISTORIA;
            }

            videojuego.getHistoria().setHistoriaID(historiaID);

            resultado = super.insertar(videojuego);

            if (resultado <= 0) {
                logger.warn("No se ha podido insertar el videojuego");
            } else {
                connection.commit();
            }
        } catch (SQLException e) {
            try {
                connection.rollback();
                logger.error("Rollback con exito");
            } catch (SQLException ex) {
                logger.error("No se pudo realizar el rollback");
            }
        }finally {
            try {
                connection.setAutoCommit(true);
                logger.info("AutoCommit Reactivado");
            } catch (SQLException e) {
                logger.error("Error al reactivar el AutoCommit");
            }
        }
        //TODO END

        return resultado;
    }


    @Override
    public int actualizar(Videojuego videojuego) {
        // TODO START

        if (videojuego == null) {
            logger.error("El video juego no existe");
            return ContieneConstants.ERROR_VIDEOJUEGO_NO_EXISTE;
        }else if(comprobarHistoria(videojuego) != 0){
            return comprobarHistoria(videojuego);
        }
        Connection connection = MySQLConnection.getInstance().getConnection();
        int resultado;

        try {
            connection.setAutoCommit(false);


            // Guardo la historia asociada para actualizarla a continuación
            Historia historia = videojuego.getHistoria();
            historiaService.actualizar(historia);

            resultado = super.actualizar(videojuego);
            if (resultado != 0) {
                logger.warn("No se ha podido actualizar la historia");
            } else {
                    connection.commit();
                    logger.trace("Videojuego actualizado con éxito");
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
        return resultado;
        // TODO END
    }

    public int comprobarVideojuego(Videojuego videojuego){

        // Compruebo si el videojuego existe

        if(videojuego.getTitulo() == null || videojuego.getTitulo().isBlank() || videojuego.getTitulo().isEmpty()){
            logger.error("Titulo nulo");
            return ERROR_TITULO_NULO;
        }else if( videojuego.getAnioLanzamiento() == null){
            logger.error("Año nulo");
            return ERROR_ANIO_NULO;
        }else if(videojuego.getPrecio() > TABLE_PRECIO_MAX_ENTEROS){
            logger.error("precio de enteros superior al permitido");
            return ERROR_PRECIO_DEMASIADO_GRANDE;
        }else if(videojuego.getHistoria() == null){
            logger.error("Historia nula.");
            return ERROR_HISTORIA_NULA;
        }
        return 0;
    }
    public int comprobarHistoria(Videojuego historia){

        // Compruebo si el videojuego existe
        if (historia == null) {
            logger.warn("La historia no existe");
            return ERROR_HISTORIA_NO_EXISTE;
        }
        return 0;
    }

    @Override
    public List<Videojuego> obtenerTodos() {
        List<Videojuego> videojuegos = super.obtenerTodos();
        for (Videojuego videojuego : videojuegos)
            videojuego.setHistoria(historiaService.obtenerPorId(videojuego.getHistoria().getHistoriaID()));

        return videojuegos;
    }

    @Override
    public Videojuego obtenerPorId(Integer id) {
        Videojuego videojuego = super.obtenerPorId(id);
        videojuego.setHistoria(historiaService.obtenerPorId(videojuego.getHistoria().getHistoriaID()));
        return videojuego;
    }

    @Override
    public int eliminar(Integer id) {
        Videojuego videojuego = obtenerPorId(id);
        if(comprobarVideojuego(videojuego) != 0) return ERROR_BORRANDO_VJ_NO_EXISTE;

        // Compruebo si el videojuego está en algún pedido, ya que de ser así no se podrá borrar para mantener la integridad
        List<Pedido> pedidos = pedidoService.obtenerPedidosCliente(id);
        if (pedidos == null) {
            logger.warn("No se han podido comprobar los pedidos asociados al videojuego por un error inesperado");
            return GenericConstants.ERROR_SQL_EXCEPTION;
        }

        if (!pedidos.isEmpty()) {
            logger.warn("No se pueden borrar videojuegos que ya estén en algún pedido");
            return VideojuegoConstants.ERROR_BORRANDO_VJ_EN_PEDIDOS;
        }

        Connection connection = MySQLConnection.getInstance().getConnection();
        int resultado;
        try {
            connection.setAutoCommit(false);

            // Guardo la historia asociada para borrarla a continuación
            Historia historia = videojuego.getHistoria();

            // Eliminar videojuego
            resultado = super.eliminar(id);
            if (resultado != 0) {
                // No se ha podido borrar el videojuego
                logger.warn("No se ha podido borrar el videojuego {}", id);
            } else {
                // Eliminar historia asociada
                resultado = historiaService.eliminar(historia.getHistoriaID());
                if (resultado != 0) {
                    // No se ha podido borrar la historia
                    logger.warn("No se ha podido borrar la historia asociada al videojuego. Se hace rollback");
                    connection.rollback();
                } else {
                    connection.commit();
                    logger.trace("Videojuego borrado con éxito");
                }
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
        return resultado;
    }
}
