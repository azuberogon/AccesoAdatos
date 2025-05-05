package UT4_A4_CRUD.controller;

import UT4_A4_CRUD.model.entity.Cliente;
import UT4_A4_CRUD.model.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ClienteController {
    //RECORDAD PONER EL @AUTOWIRED
    @Autowired
    private ClienteService clienteService;

    //Al acceder a /clientes nos va a crear y persistir un cliente con valores por defecto
    @GetMapping("/clientes")
    public String clientes(
            @RequestParam(name = "nombre", required = false) String nombre,
            @RequestParam(name = "apellido1", required = false) String apellido1,
            @RequestParam(name = "apellido2", required = false) String apellido2,
            @RequestParam(name = "ciudad", required = false) String ciudad,
            @RequestParam(name = "categoria", required = false) Long categoria,
            Model model) {

        //Comprobación sobre las colunas que no admiten nulos
        if (nombre == null)
            nombre = "Anonimo";
        if (apellido1 == null)
            apellido1 = "";

        Cliente cliente = new Cliente();
        cliente.setNombre(nombre);
        cliente.setApellido1(apellido1);
        cliente.setApellido2(apellido2);
        cliente.setCiudad(ciudad);
        cliente.setCategoria(categoria);
        clienteService.save(cliente);

        //Añadimos cada atributo de la entidad como un atributo del modelo diferenciado
        model.addAttribute("nombre", nombre);
        model.addAttribute("apellido1", apellido1);
        model.addAttribute("apellido2", apellido2);
        model.addAttribute("ciudad", ciudad);
        model.addAttribute("categoria", categoria);
        //Llamamos a la plantilla (template) correspondiente
        return "clientes";
    }

    //Controlador para mostrar el listado de los registros
    @GetMapping("/clientes/all")
    public String all(Model model) {
        //Añadimos como atributo del modelo la lista de clientes, accesible en el modelo a traves de 'clientes'
        model.addAttribute("clientes", clienteService.findAll());
        return "mostrar_clientes";
    }

    //Controlador para la petición GET de añadir un registro
    @GetMapping("/clientes/add")
    public String add(Model model) {
        //Añadimos al modelo un cliente nuevo en 'blanco' que será el que rellene el formulario de la plantilla
        model.addAttribute("cliente", new Cliente());
        return "cliente_add";
    }

    //Controlador para la petición POST de añadir un registro, la realiza el formulario al hacer submit
    //El metodo recibe como parámetro de entrada un cliente como atributo del modelo, no como argumento de la URL
    //El cliente viene sin ID (null) y el metodo lo que hace es asignarle una nueva automáticamente
    //Recarga la página del listado tras la operación
    @PostMapping("/clientes/add")
    public String add(@ModelAttribute Cliente cliente, Model model) {
        clienteService.save(cliente);
        return all(model);
    }

    //Controlador para borrar un registro, recarga la página con el listado tras la operación
    @GetMapping("/clientes/delete")
    public String delete(@RequestParam(name = "id") Long id, Model model) {
        clienteService.deleteById(id);
        return all(model);
    }

    //Controlador para mostrar el formulario de edición
    //Primero recuperamos el registro a actualizar
    // y lo añadimos como atributo al modelo que lo mostrará en el formulario
    @GetMapping("/clientes/edit")
    public String edit(@RequestParam(name = "id") Long id, Model model) {
        Cliente cliente = clienteService.getById(id);
        model.addAttribute("cliente", cliente);
        return "cliente_edit";
    }

    //Controlador para el POST remitodo por el formulario de edición
    //Como al crear uno nuevo recibe los datos del cliente como un atributo del modelo y no por URL
    //En este caso el cliente trae su ID y el metodo lo que hace es modificar el registro con ese ID
    @PostMapping("/clientes/edit")
    public String edit(@ModelAttribute Cliente cliente, Model model) {
        clienteService.save(cliente);
        return all(model);
    }
}
