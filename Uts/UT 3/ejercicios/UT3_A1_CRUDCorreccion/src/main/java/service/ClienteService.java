package service;
import dao.ClienteDAO;
import model.Cliente;


import dao.ClienteDAO;
import model.Cliente;

import java.util.List;

public class ClienteService {
    private final ClienteDAO clienteDAO;

    public ClienteService() {
        this.clienteDAO = new ClienteDAO();
    }

    /**
     * Obtener todos los clientes.
     */
    public List<Cliente> obtenerTodos() {
        return clienteDAO.obtenerTodos();
    }

    /**
     * Obtener todos los clientes con pedidos.
     */
    public List<Cliente> obtenerTodosConPedidos() {
        return clienteDAO.obtenerTodosConPedidos();
    }

    /**
     * Guardar un cliente.
     */
    public void guardar(Cliente cliente) {
        clienteDAO.guardar(cliente);
    }

    /**
     * Actualizar un cliente existente.
     */
    public void actualizar(Cliente cliente) {
        clienteDAO.actualizar(cliente);
    }

    /**
     * Eliminar un cliente por ID.
     */
    public void eliminar(Long id) {
        clienteDAO.eliminar(id);
    }
}
