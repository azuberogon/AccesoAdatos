
import model.Cliente;
import model.Comercial;
import model.Pedido;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.ClienteService;
import service.ComercialService;
import service.PedidoService;

import java.util.Date;
import java.util.List;
import java.util.Scanner;


public class Main {
    private static Scanner sc = new Scanner(System.in);
    private static final Logger logger = LogManager.getLogger(Main.class.getName());
    public static void main(String[] args) {
        System.out.println("-----------------------------------------");
        System.out.println("-----------------------------------------");
        System.out.println("----------- UT3 A2 - Relaciones ---------");
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
                *******************    MENÚ    *******************
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
        //TODO Creamos el cliente (aun sin pedido)
        Cliente cliente = new Cliente();
        cliente.setNombre("Eneko");
        cliente.setApellido1("Eres mal");
        cliente.setApellido2("Cliente");
        cliente.setCiudad("Pamplona");
        cliente.setCategoria(Long.valueOf(1));

        //TODO Creamos el pedido que hace el cliente
        Pedido pedido = new Pedido();
        pedido.setTotal(Double.valueOf(150));
        pedido.setFecha(new Date());

        //TODO Añadimos el pedido en la lista de pedidos del cliente y viceversa.
        // Tanto cliente como pedido no tienen identificadores ya que no los hemos persistido
        pedido.setCliente(cliente);
        cliente.getPedidos().add(pedido);
        System.out.println("Cliente ANTES de create:\n" + cliente);
        System.out.println("Pedido ANTES de create:\n" + pedido);

        //TODO Persistimos el cliente.
        // CASCADE.ALL garantiza que Hibernate nos cree en la base de datos el pedido correspondiente
        // Vemos cómo ya le ha asignado identificadores a los objetos
        clienteService.create(cliente);
        System.out.println("Cliente DESPUÉS de create:\n" + cliente + cliente.getPedidos());


        cliente.setNombre("Emilio");
        cliente.setCiudad("Granada");

        System.out.println("Cliente ANTES de update:\n" + cliente + cliente.getPedidos());
        clienteService.update(cliente);
        System.out.println("Cliente DESPUÉS de update:\n" + cliente + cliente.getPedidos());

        Cliente cliente2 = clienteService.findById(cliente.getClienteID());
        System.out.println("Cliente2 DESPUÉS de findById:\n" + cliente2 + cliente2.getPedidos() + "\n");

        List<Cliente> clientes = clienteService.findAll();
        System.out.println("TODOS LOS CLIENTES PERSISTIDOS:");
        for (Cliente c : clientes){
            System.out.println(c.toString() + c.getPedidos().toString() + "\n");
        }

        clienteService.delete(cliente);

    }

    public static void testComercial(){
        ComercialService comercialService = new ComercialService();
        //TODO Creamos el comercial (aun sin pedido)
        Comercial comercial = new Comercial();
        comercial.setNombre("Eneko");
        comercial.setApellido1("No sabes");
        comercial.setApellido2("nada de comercio");
        comercial.setComision(Float.valueOf(200));

        //todo Creamos el cliente (aun sin pedido) ya que todo pedido necesita un cliente
        Cliente cliente = new Cliente();
        cliente.setNombre("Eneko");
        cliente.setApellido1("Eres mal");
        cliente.setApellido2("Cliente");
        cliente.setCiudad("Pamplona");
        cliente.setCategoria(Long.valueOf(1));

        //TODO Creamos el pedido que hace el cliente
        Pedido pedido = new Pedido();
        pedido.setTotal(Double.valueOf(230));
        pedido.setFecha(new Date());

        //TODO Añadimos el pedido en la lista de pedidos del cliente y el cliente en la del pedido.
        pedido.setCliente(cliente);
        cliente.getPedidos().add(pedido);

        //TODO Añadimos el pedido en la lista de pedidos del comercial.
        // Tanto comercial como pedido no tienen identificadores ya que no los hemos persistido
        pedido.setComercial(comercial);
        comercial.getPedidos().add(pedido);
        System.out.println("Comercial ANTES de create:\n" + comercial);
        System.out.println("Pedido ANTES de create:\n" + pedido);


        //TODO Persistimos el comercial.
        // CASCADE.ALL garantiza que Hibernate nos actualice en la base de datos el pedido correspondiente
        // Vemos cómo ya le ha asignado identificadores a los objetos
        comercialService.create(comercial);
        System.out.println("Comercial DESPUÉS de create:\n" + comercial + comercial.getPedidos());


        comercial.setNombre("Emilio");
        comercial.setComision(Float.valueOf(0));
        System.out.println("Comercial ANTES de update:\n" + comercial + comercial.getPedidos());

        comercialService.update(comercial);
        System.out.println("Comercial DESPUÉS de update:\n" + comercial + comercial.getPedidos());

        Comercial comercial2 = comercialService.findById(comercial.getComercialID());
        System.out.println("Comercial2 DESPUÉS de findById:\n" + comercial2 + comercial2.getPedidos());

        List<Comercial> comerciales = comercialService.findAll();
        System.out.println("TODOS LOS COMERCIALES PERSISTIDOS:");
        for (Comercial c : comerciales){
            System.out.println(c.toString() + c.getPedidos().toString() + "\n");
        }

        comercialService.delete(comercial);
    }

    public static void testPedido(){
        //TODO Creamos un nuevo pedido
        PedidoService pedidoService = new PedidoService();
        Pedido pedido = new Pedido();
        pedido.setTotal(Double.valueOf(150));
        pedido.setFecha(new Date());

        //TODO Creamos el cliente (aun sin pedido)
        Cliente cliente = new Cliente();
        cliente.setNombre("Eneko");
        cliente.setApellido1("Eres mal");
        cliente.setApellido2("Cliente");
        cliente.setCiudad("Pamplona");
        cliente.setCategoria(Long.valueOf(1));

        //TODO Añadimos el pedido en la lista de pedidos del cliente y viceversa.
        // Tanto cliente como pedido no tienen identificadores ya que no los hemos persistido
        pedido.setCliente(cliente);
        cliente.getPedidos().add(pedido);

        System.out.println("Pedido ANTES de create:\n" + pedido + pedido.getCliente()
                + pedido.getComercial());

        pedidoService.create(pedido);
        System.out.println("Pedido DESPUÉS de create:\n" + pedido + pedido.getCliente()
                + pedido.getComercial());

        pedido.setTotal(Double.valueOf(20000));
        System.out.println("Pedido ANTES de update:\n" + pedido + pedido.getCliente()
                + pedido.getComercial());

        pedidoService.update(pedido);
        System.out.println("Pedido DESPUÉS de update:\n" + pedido + pedido.getCliente()
                + pedido.getComercial());

        Pedido pedido2 = pedidoService.findById(pedido.getpedidoID());
        System.out.println("Pedido2 DESPUÉS de findById:\n" + pedido2
                + pedido2.getCliente() + pedido2.getComercial());

        List<Pedido> pedidos = pedidoService.findAll();
        System.out.println("TODOS LOS PEDIDOS PERSISTIDOS:");
        for (Pedido p : pedidos){
            System.out.println(p.toString() + p.getCliente() + p.getComercial() + "\n");
        }

        pedidoService.delete(pedido);
    }
}