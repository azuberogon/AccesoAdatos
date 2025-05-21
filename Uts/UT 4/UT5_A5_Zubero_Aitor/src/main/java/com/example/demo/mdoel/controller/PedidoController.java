package com.example.demo.mdoel.controller;

import com.example.demo.mdoel.entity.Pedido;
import com.example.demo.mdoel.service.PedidoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/pedidos")
public class PedidoController {
    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping
    public String listarPedidos(Model model) {
        List<Pedido> pedidos = pedidoService.listarPedidos();
        model.addAttribute("pedidos", pedidos);
        return "pedidos/lista";
    }

    @PostMapping
    public String guardarPedido(@ModelAttribute Pedido pedido) {
        pedidoService.guardarPedido(pedido);
        return "redirect:/pedidos";
    }

    @PostMapping("/{id}/eliminar")
    public String eliminarPedido(@PathVariable Long id) {
        pedidoService.eliminarPedido(id);
        return "redirect:/pedidos";
    }
}
