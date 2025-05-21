package com.example.demo.mdoel.service;

import com.example.demo.mdoel.dao.IClienteDAO;
import com.example.demo.mdoel.entity.Cliente;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClienteService {
    private final IClienteDAO clienteRepository;

    public ClienteService(IClienteDAO clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    public Cliente obtenerCliente(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    public Cliente guardarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public void eliminarCliente(Long id) {
        clienteRepository.deleteById(id);
    }
}
