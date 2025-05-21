
import dao.ClienteDAO;
import dao.PedidoDAO;
import model.Cliente;
import model.Comercial;
import model.Pedido;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.ClienteService;
import service.ComercialService;
import service.PedidoService;

import java.util.ArrayList;
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
       // testCliente();
        //opcionesUsuario();
        testRelacion();
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
        Cliente cliente = new Cliente();
        cliente.setNombre("Eneko");
        cliente.setApellido1("No sabe");
        cliente.setApellido2("Nada");
        cliente.setCiudad("Pamplona");
        cliente.setCategoria(Long.valueOf(1));
        System.out.println("Cliente ANTES de create:\n" + cliente);

        clienteService.create(cliente);
        System.out.println("Cliente DESPUÉS de create:\n" + cliente);

        cliente.setNombre("Emilio");
        cliente.setCiudad("Granada");
        System.out.println("Cliente ANTES de update:\n" + cliente);

        clienteService.update(cliente);
        System.out.println("Cliente DESPUÉS de update:\n" + cliente);

        Cliente cliente2 = clienteService.findById(cliente.getClienteID());
        System.out.println("Cliente2 DESPUÉS de findById:\n" + cliente2);

        List<Cliente> clientes = clienteService.findAll();
        System.out.println("TODOS LOS CLIENTES PERSISTIDOS:");
        clientes.forEach(System.out::println);
        clienteService.delete(cliente);
    }

    public static void testComercial(){
        ComercialService comercialService = new ComercialService();
        Comercial comercial = new Comercial();
        comercial.setNombre("Eneko");
        comercial.setApellido1("No sabe");
        comercial.setApellido2("Nada");
        comercial.setComision(Float.valueOf(200));
        System.out.println("Comercial ANTES de create:\n" + comercial);

        comercialService.create(comercial);
        System.out.println("Comercial DESPUÉS de create:\n" + comercial);

        comercial.setNombre("Emilio");
        comercial.setComision(Float.valueOf(0));
        System.out.println("Comercial ANTES de update:\n" + comercial);

        comercialService.update(comercial);
        System.out.println("Comercial DESPUÉS de update:\n" + comercial);

        Comercial comercial2 = comercialService.findById(comercial.getComercialID());
        System.out.println("Comercial2 DESPUÉS de findById:\n" + comercial2);

        List<Comercial> comerciales = comercialService.findAll();
        System.out.println("TODOS LOS COMERCIALES PERSISTIDOS:");
        comerciales.forEach(System.out::println);
        comercialService.delete(comercial);
    }

    public static void testPedido(){
        PedidoService pedidoService = new PedidoService();
        Pedido pedido = new Pedido();
        pedido.setTotal(Double.valueOf(150));
        pedido.setFecha(new Date());
        System.out.println("Pedido ANTES de create:\n" + pedido);

        pedidoService.create(pedido);
        System.out.println("Pedido DESPUÉS de create:\n" + pedido);

        pedido.setTotal(Double.valueOf(20000));
        System.out.println("Pedido ANTES de update:\n" + pedido);

        pedidoService.update(pedido);
        System.out.println("Pedido DESPUÉS de update:\n" + pedido);

        Pedido pedido2 = pedidoService.findById(pedido.getpedidoID());
        System.out.println("Pedido2 DESPUÉS de findById:\n" + pedido2);

        List<Pedido> pedidos = pedidoService.findAll();
        System.out.println("TODOS LOS PEDIDOS PERSISTIDOS:");
        pedidos.forEach(System.out::println);
        pedidoService.delete(pedido);
    }
    private static void testRelacion(){
        ClienteService clienteService = new ClienteService();
        Cliente cliente = ClienteDAO;
//        cliente.setNombre("xxxxx");
//        cliente.setApellido1("XXXX");
//        cliente.setApellido2("XXXXX");
//        cliente.setCategoria(Long.valueOf(1));
//        cliente.setCiudad("XXXX");


        Pedido pedido = new Pedido();
//        pedido.setFecha(new Date());
//        pedido.setCliente(cliente);


//        Pedido pedido2 = new Pedido();
//        pedido.setFecha(new Date());
//        pedido2.setCliente(cliente);



//        cliente.getPedidos().add(pedido);
//        cliente.getPedidos().add(pedido2);

//        List<Cliente> asd = clienteService.findAll();
//        PedidoService pedidoservice = new PedidoService();
//        clienteService.findByIdWithPedidos(11);
//        List<Pedido> Pedidos = cliente.getPedidos();
//        for (Cliente cliente1 : asd) {
//
//            for ( Pedido pedido3 : cliente1.getPedidos() ) {
//                System.out.println(pedido3);
//            }
//
//        }

        // Añadir los pedidos a la lista de pedidos del cliente

        //Persistir cliente (los pedidos se guardarán automáticamente por el cascade)


        Cliente cliente3 = clienteService.findByIdWithPedidos(11);
        System.out.println("Cliente3: " + cliente3);
        System.out.println("Pedidos: ");
        cliente3.getPedidos().forEach(System.out::println);



    }
}