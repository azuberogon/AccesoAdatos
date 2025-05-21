package com.example.demo.mdoel.controller;

import com.example.demo.mdoel.entity.Cliente;
import com.example.demo.mdoel.service.ClienteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/clientes")
public class ClienteController {
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public String listarClientes(Model model) {
        List<Cliente> clientes = clienteService.listarClientes();
        model.addAttribute("clientes", clientes);
        return "clientes/lista";
    }

    @GetMapping("/{id}")
    public String detallesCliente(@PathVariable Long id, Model model) {
        Cliente cliente = clienteService.obtenerCliente(id);
        model.addAttribute("cliente", cliente);
        return "clientes/detalle";
    }

    @PostMapping
    public String guardarCliente(@ModelAttribute Cliente cliente) {
        clienteService.guardarCliente(cliente);
        return "redirect:/clientes";
    }

    @PostMapping("/{id}/eliminar")
    public String eliminarCliente(@PathVariable Long id) {
        clienteService.eliminarCliente(id);
        return "redirect:/clientes";
    }
}