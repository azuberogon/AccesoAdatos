
import connection.HibernateUtil;
import model.Cliente;
import model.Historia;
import model.Pedido;
import model.Videojuego;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import service.ClienteService;
import service.HistoriaService;
import service.PedidoService;
import service.VideojuegoService;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Main {
    static VideojuegoService videojuegoService = new VideojuegoService();
    static HistoriaService historiaService = new HistoriaService();
    static ClienteService clienteService = new ClienteService();
    static PedidoService pedidoService = new PedidoService();

    public static void main(String[] args) {
        // Crear un cliente
        Cliente cliente = new Cliente();
        cliente.setNombre("Juan");
        cliente.setApellidos("Pérez");

        // Crear pedidos y asignarlos al cliente
        Pedido pedido1 = new Pedido();
        pedido1.setFechaHora(new Date());
        pedido1.setCliente(cliente); // Relacionamos el pedido con el cliente

        Pedido pedido2 = new Pedido();
        pedido2.setFechaHora(new Date());
        pedido2.setCliente(cliente);

        // Añadir los pedidos a la lista de pedidos del cliente
        cliente.getPedidos().add(pedido1);
        cliente.getPedidos().add(pedido2);

        // Persistir cliente (los pedidos se guardarán automáticamente por el cascade)
        clienteService.create(cliente);

        Cliente cliente3 = clienteService.findByIdWithPedidos(cliente.getClienteID());
        System.out.println("Cliente3: " + cliente3);
        System.out.println("Pedidos: ");
        cliente3.getPedidos().forEach(System.out::println);

//        // TODO START
//        Pedido pedido = pedidoService.findById(pedido1.getPedidoID());
//        // TODO END
//        System.out.println("Pedido: " + pedido);
//        System.out.println("Cliente del pedido: " + pedido.getCliente());
    }

    static void testHistoriaYVideojuego() {
        // Creamos la historia primero
        Historia historia = new Historia();
        historia.setTitulo("Harry Potter");
        historia.setDescripcion("La piedra filosofal...");
        historiaService.create(historia);

        // Creamos el videojuego después con la historia asociada
        Videojuego videojuego = new Videojuego();
        videojuego.setTitulo("Super Mario Bross");
        videojuego.setPlataforma("Game Boy");
        videojuego.setPrecio(BigDecimal.valueOf(5.99));
        videojuego.setAnioLanzamiento(1985);
        videojuego.setDisponible(false);
        videojuego.setHistoria(historia);
        videojuegoService.create(videojuego);

        Videojuego videojuego2 = videojuegoService.findById(videojuego.getVideojuegoID());
        System.out.println("Videojuego2: " + videojuego2);
        System.out.println("Historia: " + videojuego2.getHistoria());
    }

    static void testHistoria() {
        Historia historia = new Historia();
        historia.setTitulo("Titulo historia");
        historia.setDescripcion("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus sit amet libero at dui euismod pellentesque");
        System.out.println("Historia ANTES de create:\n" + historia);

        historiaService.create(historia);
        System.out.println("Historia DESPUÉS de create:\n" + historia);

        historia.setTitulo("Titulo historia 222");
        System.out.println("Historia ANTES de update:\n" + historia);
        historiaService.update(historia);
        System.out.println("Historia DESPUÉS de update:\n" + historia);

        Historia historia2 = historiaService.findById(historia.getHistoriaID());
        System.out.println("historia2 DESPUÉS de findById:\n" + historia2);

        List<Historia> historias = historiaService.findAll();
        System.out.println("TODAS LAS HISTORIAS PERSISTIDAS:");
        historias.forEach(System.out::println);

        historiaService.delete(historia);
    }

    static void testVideojuego() {
        Videojuego videojuego = new Videojuego();
        videojuego.setTitulo("Super Mario Bross");
        videojuego.setPlataforma("Game Boy");
        videojuego.setPrecio(BigDecimal.valueOf(5.99));
        videojuego.setAnioLanzamiento(1985);
        videojuego.setDisponible(false);
        System.out.println("Videojuego ANTES de create:\n" + videojuego);

        videojuegoService.create(videojuego);
        System.out.println("Videojuego DESPUÉS de create:\n" + videojuego);

        videojuego.setFechaActualizacion(new Date());
        System.out.println("Videojuego ANTES de update:\n" + videojuego);
        videojuegoService.update(videojuego);
        System.out.println("Videojuego DESPUÉS de update:\n" + videojuego);

        Videojuego videojuego2 = videojuegoService.findById(videojuego.getVideojuegoID());
        System.out.println("Videojuego2 DESPUÉS de findById:\n" + videojuego2);

        List<Videojuego> videojuegos = videojuegoService.findAll();
        System.out.println("TODOS LOS VIDEOJUEGOS PERSISTIDOS:");
        videojuegos.forEach(System.out::println);

        videojuegoService.delete(videojuego);
    }
}
