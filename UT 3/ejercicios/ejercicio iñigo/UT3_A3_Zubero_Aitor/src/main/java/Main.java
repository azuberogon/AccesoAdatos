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
    private static final Scanner sc = new Scanner(System.in);
    private static final Logger logger = LogManager.getLogger(Main.class.getName());

    public static void main(String[] args) {
        System.out.println("-----------------------------------------");
        System.out.println("----------- UT3 A2 - Relaciones ---------");
        System.out.println("-----------------------------------------");

        opcionesUsuario();
    }

    private static void opcionesUsuario() {
        String opcion = "0";

        while (!opcion.equals("4")) {
            opcion = mostrarMenu();
            switch (opcion) {
                case "1":
                    menuCliente();
                    break;
                case "2":
                    menuComercial();
                    break;
                case "3":
                    menuPedido();
                    break;
                case "4":
                    System.out.println("Gracias por usar el programa. ¡Adiós!");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, intenta de nuevo.");
            }
        }
    }

    private static String mostrarMenu() {
        System.out.println("\n**************************************************");
        System.out.println("*******************    MENÚ    *******************");
        System.out.println("**************************************************");
        System.out.println("Elige una de las siguientes opciones:");
        System.out.println("\t1 - Gestionar Cliente");
        System.out.println("\t2 - Gestionar Comercial");
        System.out.println("\t3 - Gestionar Pedido");
        System.out.println("\t4 - Salir");
        return sc.nextLine();
    }

    private static void menuCliente() {
        ClienteService clienteService = new ClienteService();
        String opcion;

        do {
            System.out.println("\n--- Gestión de Clientes ---");
            System.out.println("1 - Crear Cliente");
            System.out.println("2 - Buscar Cliente por ID");
            System.out.println("3 - Actualizar Cliente");
            System.out.println("4 - Eliminar Cliente");
            System.out.println("5 - Listar todos los Clientes");
            System.out.println("6 - Volver al menú principal");
            opcion = sc.nextLine();

            switch (opcion) {
                case "1":
                    // TODO: Crear un cliente con los datos proporcionados por el usuario
                    Cliente nuevoCliente = new Cliente();
                    System.out.println("Introduce el nombre del cliente:");
                    nuevoCliente.setNombre(sc.nextLine());
                    System.out.println("Introduce el primer apellido del cliente:");
                    nuevoCliente.setApellido1(sc.nextLine());
                    System.out.println("Introduce el segundo apellido del cliente:");
                    nuevoCliente.setApellido2(sc.nextLine());
                    System.out.println("Introduce la ciudad del cliente:");
                    nuevoCliente.setCiudad(sc.nextLine());
                    System.out.println("Introduce la categoría del cliente:");
                    nuevoCliente.setCategoria(Long.parseLong(sc.nextLine()));
                    clienteService.create(nuevoCliente);
                    System.out.println("Cliente creado con éxito: " + nuevoCliente);
                    break;
                case "2":
                    // TODO: Buscar un cliente por ID
                    System.out.println("Introduce el ID del cliente:");
                    Long clienteId = Long.parseLong(sc.nextLine());
                    Cliente cliente = clienteService.findById(clienteId);
                    System.out.println(cliente != null ? cliente : "Cliente no encontrado.");
                    break;
                case "3":
                    // TODO: Actualizar los datos de un cliente
                    System.out.println("Introduce el ID del cliente a actualizar:");
                    clienteId = Long.parseLong(sc.nextLine());
                    cliente = clienteService.findById(clienteId);
                    if (cliente != null) {
                        System.out.println("Introduce el nuevo nombre (actual: " + cliente.getNombre() + "):");
                        cliente.setNombre(sc.nextLine());
                        System.out.println("Introduce la nueva ciudad (actual: " + cliente.getCiudad() + "):");
                        cliente.setCiudad(sc.nextLine());
                        clienteService.update(cliente);
                        System.out.println("Cliente actualizado con éxito: " + cliente);
                    } else {
                        System.out.println("Cliente no encontrado.");
                    }
                    break;
                case "4":
                    // TODO: Eliminar un cliente por ID
                    System.out.println("Introduce el ID del cliente a eliminar:");
                    clienteId = Long.parseLong(sc.nextLine());
                    cliente = clienteService.findById(clienteId);
                    if (cliente != null) {
                        clienteService.delete(cliente);
                        System.out.println("Cliente eliminado con éxito.");
                    } else {
                        System.out.println("Cliente no encontrado.");
                    }
                    break;
                case "5":
                    // TODO: Listar todos los clientes
                    List<Cliente> clientes = clienteService.findAll();
                    System.out.println("Clientes registrados:");
                    for (Cliente c : clientes) {
                        System.out.println(c);
                    }
                    break;
                case "6":
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
            }
        } while (!opcion.equals("6"));
    }

    private static void menuComercial() {
        ComercialService comercialService = new ComercialService();
        String opcion;

        do {
            System.out.println("\n--- Gestión de Comerciales ---");
            System.out.println("1 - Crear Comercial");
            System.out.println("2 - Buscar Comercial por ID");
            System.out.println("3 - Actualizar Comercial");
            System.out.println("4 - Eliminar Comercial");
            System.out.println("5 - Listar todos los Comerciales");
            System.out.println("6 - Volver al menú principal");
            opcion = sc.nextLine();

            switch (opcion) {
                case "1":
                    // TODO: Crear un comercial con los datos proporcionados por el usuario
                    Comercial nuevoComercial = new Comercial();
                    System.out.println("Introduce el nombre del comercial:");
                    nuevoComercial.setNombre(sc.nextLine());
                    System.out.println("Introduce el primer apellido del comercial:");
                    nuevoComercial.setApellido1(sc.nextLine());
                    System.out.println("Introduce el segundo apellido del comercial:");
                    nuevoComercial.setApellido2(sc.nextLine());
                    System.out.println("Introduce la comisión del comercial:");
                    nuevoComercial.setComision(Float.parseFloat(sc.nextLine()));
                    comercialService.create(nuevoComercial);
                    System.out.println("Comercial creado con éxito: " + nuevoComercial);
                    break;
                case "2":
                    // TODO: Buscar un comercial por ID
                    System.out.println("Introduce el ID del comercial:");
                    Long comercialId = Long.parseLong(sc.nextLine());
                    Comercial comercial = comercialService.findById(comercialId);
                    System.out.println(comercial != null ? comercial : "Comercial no encontrado.");
                    break;
                case "3":
                    // TODO: Actualizar los datos de un comercial
                    System.out.println("Introduce el ID del comercial a actualizar:");
                    comercialId = Long.parseLong(sc.nextLine());
                    comercial = comercialService.findById(comercialId);
                    if (comercial != null) {
                        System.out.println("Introduce el nuevo nombre (actual: " + comercial.getNombre() + "):");
                        comercial.setNombre(sc.nextLine());
                        System.out.println("Introduce la nueva comisión (actual: " + comercial.getComision() + "):");
                        comercial.setComision(Float.parseFloat(sc.nextLine()));
                        comercialService.update(comercial);
                        System.out.println("Comercial actualizado con éxito: " + comercial);
                    } else {
                        System.out.println("Comercial no encontrado.");
                    }
                    break;
                case "4":
                    // TODO: Eliminar un comercial por ID
                    System.out.println("Introduce el ID del comercial a eliminar:");
                    comercialId = Long.parseLong(sc.nextLine());
                    comercial = comercialService.findById(comercialId);
                    if (comercial != null) {
                        comercialService.delete(comercial);
                        System.out.println("Comercial eliminado con éxito.");
                    } else {
                        System.out.println("Comercial no encontrado.");
                    }
                    break;
                case "5":
                    // TODO: Listar todos los comerciales
                    List<Comercial> comerciales = comercialService.findAll();
                    System.out.println("Comerciales registrados:");
                    for (Comercial c : comerciales) {
                        System.out.println(c);
                    }
                    break;
                case "6":
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
            }
        } while (!opcion.equals("6"));
    }

    private static void menuPedido() {
        PedidoService pedidoService = new PedidoService();
        String opcion;

        do {
            System.out.println("\n--- Gestión de Pedidos ---");
            System.out.println("1 - Crear Pedido");
            System.out.println("2 - Buscar Pedido por ID");
            System.out.println("3 - Actualizar Pedido");
            System.out.println("4 - Eliminar Pedido");
            System.out.println("5 - Listar todos los Pedidos");
            System.out.println("6 - Volver al menú principal");
            opcion = sc.nextLine();

            switch (opcion) {
                case "1":
                    // TODO: Crear un nuevo pedido
                    Pedido nuevoPedido = new Pedido();
                    System.out.println("Introduce el total del pedido:");
                    nuevoPedido.setTotal(Double.parseDouble(sc.nextLine()));
                    nuevoPedido.setFecha(new Date());
                    pedidoService.create(nuevoPedido);
                    Cliente cliente = new Cliente();
                    cliente.setNombre("aurelio");
                    Comercial comercial = new Comercial();
                    comercial.setNombre("marco antonio");
                    nuevoPedido.setComercial(comercial);
                    nuevoPedido.setCliente(cliente);
                    System.out.println("Pedido creado con éxito: " + nuevoPedido);
                    break;
                case "2":
                    // TODO: Buscar un pedido por ID
                    System.out.println("Introduce el ID del pedido:");
                    Long pedidoId = Long.parseLong(sc.nextLine());
                    Pedido pedido = pedidoService.findById(pedidoId);
                    System.out.println(pedido != null ? pedido : "Pedido no encontrado.");
                    break;
                case "3":
                    // TODO: Actualizar los detalles de un pedido
                    System.out.println("Introduce el ID del pedido a actualizar:");
                    pedidoId = Long.parseLong(sc.nextLine());
                    pedido = pedidoService.findById(pedidoId);
                    if (pedido != null) {
                        System.out.println("Introduce el nuevo total (actual: " + pedido.getTotal() + "):");
                        pedido.setTotal(Double.parseDouble(sc.nextLine()));
                        pedidoService.update(pedido);
                        System.out.println("Pedido actualizado con éxito: " + pedido);
                    } else {
                        System.out.println("Pedido no encontrado.");
                    }
                    break;
                case "4":
                    // TODO: Eliminar un pedido por ID
                    System.out.println("Introduce el ID del pedido a eliminar:");
                    pedidoId = Long.parseLong(sc.nextLine());
                    pedido = pedidoService.findById(pedidoId);
                    if (pedido != null) {
                        pedidoService.delete(pedido);
                        System.out.println("Pedido eliminado con éxito.");
                    } else {
                        System.out.println("Pedido no encontrado.");
                    }
                    break;
                case "5":
                    // TODO: Listar todos los pedidos
                    List<Pedido> pedidos = pedidoService.findAll();
                    System.out.println("Pedidos registrados:");
                    for (Pedido p : pedidos) {
                        System.out.println(p);
                    }
                    break;
                case "6":
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
            }
        } while (!opcion.equals("6"));
    }
}
