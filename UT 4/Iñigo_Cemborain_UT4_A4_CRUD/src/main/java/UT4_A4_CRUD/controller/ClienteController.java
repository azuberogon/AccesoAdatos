package UT4_A4_CRUD.controller;

import UT4_A4_CRUD.model.entity.Cliente;
import UT4_A4_CRUD.model.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigInteger;

@Controller
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/clientes")
    public String cliente(
            @RequestParam(name = "nombre", required = false) String nombre,
            @RequestParam(name = "apellido1", required = false) String apellido1,
            @RequestParam(name = "apellido2", required = false) String apellido2,
            @RequestParam(name = "ciudad", required = false) String ciudad,
            @RequestParam(name = "categoria", required = false) Long categoria,
            Model model){
        if (nombre == null)
            nombre= "Anonimo";
        if (apellido1 == null)
            apellido1= "";

        Cliente cliente = new Cliente();
        cliente.setNombre(nombre);
        cliente.setApellido1(apellido1);
        cliente.setApellido2(apellido2);
        cliente.setCiudad(ciudad);
        cliente.setCategoria(categoria);
        clienteService.save(cliente);

        model.addAttribute("nombre", nombre);
        model.addAttribute("apellido1", apellido1);
        model.addAttribute("apellido2", apellido2);
        model.addAttribute("ciudad", ciudad);
        model.addAttribute("categoria", categoria);
        return "clientes";

    }
    @GetMapping("/clientes/all")
    public String all(Model model)
    {
        model.addAttribute("clientes", clienteService.findAll());
        return "mostrar_clientes";
    }

    @GetMapping("/clientes/add")
    public String add(Model model)
    {
        model.addAttribute("cliente", new Cliente());
        return "cliente_add";
    }

    @PostMapping("/clientes/add")
    public String add(@ModelAttribute Cliente cliente, Model model)
    {
        clienteService.save(cliente);
        return all(model);
    }

    @GetMapping("/clientes/delete")
    public String delete(@RequestParam(name = "id") Long id, Model model)
    {
        clienteService.deleteById(id);
        return all(model);
    }

    @GetMapping("clientes/edit")
    public String edit(@RequestParam("id") Long id, Model model)
    {
        Cliente cliente = clienteService.getById(id);
        model.addAttribute("cliente", cliente);
        return "cliente_edit";
    }

    @PostMapping("/clientes/edit")
    public String edit(@ModelAttribute Cliente cliente, Model model)
    {
        clienteService.save(cliente);
        return all(model);
    }
}
