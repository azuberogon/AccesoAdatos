package com.example.demo.mdoel.service;

import com.example.demo.mdoel.dao.IPedidoRepository;
import com.example.demo.mdoel.entity.Pedido;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PedidoService {
    private final IPedidoRepository pedidoRepository;

    public PedidoService(IPedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public List<Pedido> listarPedidos() {
        return pedidoRepository.findAll();
    }

    public Pedido obtenerPedido(Long id) {
        return pedidoRepository.findById(id).orElse(null);
    }

    public Pedido guardarPedido(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    public void eliminarPedido(Long id) {
        pedidoRepository.deleteById(id);
    }
}