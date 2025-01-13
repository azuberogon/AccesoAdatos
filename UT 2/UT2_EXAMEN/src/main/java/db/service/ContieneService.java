package db.service;

import constants.ContieneConstants;
import constants.GenericConstants;
import db.dao.ContieneDAO;
import db.model.Contiene;
import db.model.ContieneID;
import db.model.Pedido;
import db.model.Videojuego;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

public class ContieneService extends GenericServiceImpl<Contiene, ContieneID> {

    private static final Logger logger = LogManager.getLogger(ContieneService.class);

    private static final PedidoService pedidoService = new PedidoService();
    private static final VideojuegoService videojuegoService = new VideojuegoService();

    public ContieneService() {
        super(new ContieneDAO());
    }

    @Override
    public int insertar(Contiene contiene) {
        int comprobarContiene = comprobarContiene(contiene);
        if (comprobarContiene != 0)
            return comprobarContiene;
        return super.insertar(contiene);
    }

    @Override
    public int actualizar(Contiene contiene) {
        int comprobarContiene = comprobarContiene(contiene);
        if (comprobarContiene != 0)
            return comprobarContiene;
        return super.actualizar(contiene);
    }

    private int comprobarContiene(Contiene contiene) {
        if (contiene == null
                || contiene.getContieneID() == null) {
            logger.warn("No se pueden insertar 'contiene' nulos");
            return GenericConstants.ERROR_PARAM_NULL;
        }

        // Comprobar pedido
        Pedido pedido = pedidoService.obtenerPorId(contiene.getContieneID().getPedidoID());
        if (pedido == null) {
            logger.warn("El pedido id {} asociado al 'contiene' no existe en la tabla pedidos",
                    contiene.getContieneID().getPedidoID());
            return ContieneConstants.ERROR_PEDIDO_NO_EXISTE;
        }

        // Comprobar videojuego
        Videojuego videojuego = videojuegoService.obtenerPorId(contiene.getContieneID().getVideojuegoID());
        if (videojuego == null) {
            logger.warn("El videojuego id {} asociado al 'contiene' no existe en la tabla videojuegos",
                    contiene.getContieneID().getVideojuegoID());
            return ContieneConstants.ERROR_VIDEOJUEGO_NO_EXISTE;
        }

        // Comprobar cantidad
        if (contiene.getCantidad() == null
                || contiene.getCantidad() <= 0) {
            logger.warn("No se pueden insertar cantidades, nulas, negativas o 0");
            return ContieneConstants.ERROR_CANTIDAD_NULA_O_NEGATIVA;
        }

        return 0;
    }

    public List<Contiene> obtenerContienesPedido(Integer pedidoID) {
        if (pedidoID == null) {
            logger.warn("No se puede obtenerContienesPedido con pedidoID nulo");
            return null;
        }

        List<Contiene> contienes = null;
        ContieneDAO contieneDAO = (ContieneDAO) getGenericDAO();
        try {
            contienes = contieneDAO.obtenerContienesPedido(pedidoID);
        } catch (SQLException e) {
            logger.error("Error al obtenerContienesPedido con el pedidoID '{}' en la BD: ", pedidoID, e.fillInStackTrace());
            return null;
        }
        return contienes;
    }
}
