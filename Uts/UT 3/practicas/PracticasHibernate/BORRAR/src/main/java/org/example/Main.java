package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.entity.ClienteEntity;
import org.example.entity.ComercialEntity;
import org.example.entity.PedidoEntity;
import org.example.service.ClienteService;
import org.example.service.ComercialService;
import org.example.service.PedidoService;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner sc = new Scanner(System.in);
    private static final Logger logger = LogManager.getLogger(Main.class.getName());
    public static void main(String[] args) {
        System.out.println("-----------------------------------------");
        System.out.println("-----------------------------------------");
        System.out.println("-------------- UT3 A1 - CRUD ------------");
        System.out.println("-----------------------------------------");
        System.out.println("-----------------------------------------");

        opcionesUsuario();
    }

    private static void opcionesUsuario() {
        String opcion = "0";

        while (!opcion.equals("4")) {
            opcion = mostrarMenu();
            switch (opcion) {
                case "1":
                    testCliente();
                    break;
                case "2":
                    testComercial();
                    break;
                case "3":
                    testPedido();
                    break;
                case "4":
                    System.out.println("Esperamos que la experiencia haya sido de su agrado. ¡Adios!");
                    break;
                default:
                    System.out.println("Opción no valida. Prueba otra vez...");
            }
        }
    }

    private static String mostrarMenu() {
        System.out.println("""
                
                **************************************************
                **************************************************
                *******************    MENÚ    *******************
                **************************************************
                **************************************************
                Elige una de las siguientes opciones:
                \t1 - Comprobar Cliente
                \t2 - Comprobar Comercial
                \t3 - Comprobar Pedido
                \t4 - Salir""");
        return sc.nextLine();
    }

    public static void testCliente(){
        ClienteService clienteService = new ClienteService();
        ClienteEntity cliente = new ClienteEntity();
        cliente.setNombre("Eneko");
        cliente.setApellido1("No sabe");
        cliente.setApellido2("Nada");
        cliente.setCiudad("Pamplona");
        cliente.setCategoría(Integer.valueOf(1));
        System.out.println("Cliente ANTES de create:\n" + cliente);

        clienteService.create(cliente);
        System.out.println("Cliente DESPUÉS de create:\n" + cliente);

        cliente.setNombre("Emilio");
        cliente.setCiudad("Granada");
        System.out.println("Cliente ANTES de update:\n" + cliente);

        clienteService.update(cliente);
        System.out.println("Cliente DESPUÉS de update:\n" + cliente);

        ClienteEntity cliente2 = clienteService.findById(cliente.getId());
        System.out.println("Cliente2 DESPUÉS de findById:\n" + cliente2);

        List<ClienteEntity> clientes = clienteService.findAll();
        System.out.println("TODOS LOS CLIENTES PERSISTIDOS:");
        clientes.forEach(System.out::println);
        clienteService.delete(cliente);
    }

    public static void testComercial(){
        ComercialService comercialService = new ComercialService();
        ComercialEntity comercial = new ComercialEntity();
        comercial.setNombre("Eneko");
        comercial.setApellido1("No sabe");
        comercial.setApellido2("Nada");
        comercial.setComisión(Double.valueOf(200));
        System.out.println("Comercial ANTES de create:\n" + comercial);

        comercialService.create(comercial);
        System.out.println("Comercial DESPUÉS de create:\n" + comercial);

        comercial.setNombre("Emilio");
        comercial.setComisión(Double.valueOf(0));
        System.out.println("Comercial ANTES de update:\n" + comercial);

        comercialService.update(comercial);
        System.out.println("Comercial DESPUÉS de update:\n" + comercial);

        ComercialEntity comercial2 = comercialService.findById(comercial.getId());
        System.out.println("Comercial2 DESPUÉS de findById:\n" + comercial2);

        List<ComercialEntity> comerciales = comercialService.findAll();
        System.out.println("TODOS LOS COMERCIALES PERSISTIDOS:");
        comerciales.forEach(System.out::println);
        comercialService.delete(comercial);
    }

    public static void testPedido(){
        PedidoService pedidoService = new PedidoService();
        PedidoEntity pedido = new PedidoEntity();
        pedido.setTotal(Double.valueOf(150));
        pedido.setFecha(new Date());
        System.out.println("Pedido ANTES de create:\n" + pedido);

        pedidoService.create(pedido);
        System.out.println("Pedido DESPUÉS de create:\n" + pedido);

        pedido.setTotal(Double.valueOf(20000));
        System.out.println("Pedido ANTES de update:\n" + pedido);

        pedidoService.update(pedido);
        System.out.println("Pedido DESPUÉS de update:\n" + pedido);

        PedidoEntity pedido2 = pedidoService.findById(pedido.getId());
        System.out.println("Pedido2 DESPUÉS de findById:\n" + pedido2);

        List<PedidoEntity> pedidos = pedidoService.findAll();
        System.out.println("TODOS LOS PEDIDOS PERSISTIDOS:");
        pedidos.forEach(System.out::println);
        pedidoService.delete(pedido);
    }
}