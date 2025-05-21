package db.service;

import constants.GenericConstants;
import db.connection.MySQLConnection;
import db.dao.ClienteDAO;
import db.model.Cliente;
import db.model.Pedido;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.GenericUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static constants.ClienteConstants.*;

public class ClienteService extends GenericServiceImpl<Cliente, Integer> {

    private static final Logger logger = LogManager.getLogger(ClienteService.class);

    private static final PedidoService pedidoService = new PedidoService();

    public ClienteService() {
        super(new ClienteDAO());
    }

    @Override
    public List<Cliente> obtenerTodos() {
        List<Integer> pedidos = null;
        List<Cliente> clientes = super.obtenerTodos();
        for (Cliente cliente : clientes){
            for (Pedido pedidoInt : pedidoService.obtenerPedidosCliente(cliente.getClienteID())){
                pedidos.add(pedidoInt.getPedidoID());
            }
            pedidos.clear();
        }

        return clientes;
    }

    @Override
    public int insertar(Cliente cliente) {
        int comprobarCliente = comprobarCliente(cliente);
        if (comprobarCliente != 0)
            return comprobarCliente;
        return super.insertar(cliente);
    }

    @Override
    public int actualizar(Cliente cliente) {
        int comprobarCliente = comprobarCliente(cliente);
        if (comprobarCliente != 0)
            return comprobarCliente;
        return super.actualizar(cliente);
    }

    @Override
    public int eliminar(Integer id) {
        // Comprobar si el cliente existe
        Cliente cliente = obtenerPorId(id);
        if (cliente == null) {
            logger.warn("No se puede borrar un cliente que no existe");
            return ERROR_BORRANDO_CLIENTE_NO_EXISTE;
        }

        // Obtengo los pedidos asociados
        List<Pedido> pedidos = pedidoService.obtenerPedidosCliente(id);
        if (pedidos == null) {
            logger.warn("No se han podido comprobar los pedidos asociados al cliente por un error inesperado");
            return GenericConstants.ERROR_SQL_EXCEPTION;
        }

        // Si tiene pedidos no se puede borrar el cliente
        if (!pedidos.isEmpty()) {
            logger.warn("No se puede borrar un cliente con pedidos. Habría que revisar que los pedidos estén OK y borrar los pedidos primero");
            return ERROR_BORRANDO_CLIENTE_CON_PEDIDOS;
        }

        // Borrar cliente
        return super.eliminar(id);
    }

    private int comprobarCliente(Cliente cliente) {
        if (cliente == null) {
            logger.warn("No se pueden insertar clientes nulos");
            return GenericConstants.ERROR_PARAM_NULL;
        }

        // Comprobar el nombre
        if (cliente.getNombre() == null
                || cliente.getNombre().isEmpty()
                || cliente.getNombre().isBlank()) {
            logger.warn("No se admiten nombres nulos o vacíos");
            return ERROR_NOMBRE_NULO;
        } else if (cliente.getNombre().length() > TABLE_NOMBRE_MAX_LONG)
            cliente.setNombre(GenericUtils.limitarString(cliente.getNombre(), TABLE_NOMBRE_MAX_LONG));

        // Comprobar apellidos
        if (cliente.getApellidos() != null && cliente.getApellidos().length() > TABLE_APELLIDOS_MAX_LONG)
            cliente.setApellidos(GenericUtils.limitarString(cliente.getApellidos(), TABLE_APELLIDOS_MAX_LONG));

        return 0;
    }
}
