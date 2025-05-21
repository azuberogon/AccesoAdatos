package service;
import dao.PedidoDAO;
import model.Pedido;


import dao.PedidoDAO;
import model.Pedido;

import java.util.List;

public class PedidoService {
    private final PedidoDAO pedidoDAO;

    public PedidoService() {
        this.pedidoDAO = new PedidoDAO();
    }

    /**
     * Obtener todos los pedidos.
     */
    public List<Pedido> obtenerTodos() {
        return pedidoDAO.obtenerTodos();
    }

    /**
     * Obtener todos los pedidos con cliente y comercial relacionados.
     */
    public List<Pedido> obtenerTodosConRelaciones() {
        return pedidoDAO.obtenerTodosConRelaciones();
    }

    /**
     * Guardar un pedido.
     */
    public void guardar(Pedido pedido) {
        pedidoDAO.guardar(pedido);
    }

    /**
     * Actualizar un pedido existente.
     */
    public void actualizar(Pedido pedido) {
        pedidoDAO.actualizar(pedido);
    }

    /**
     * Eliminar un pedido por ID.
     */
    public void eliminar(Long id) {
        pedidoDAO.eliminar(id);
    }
}
