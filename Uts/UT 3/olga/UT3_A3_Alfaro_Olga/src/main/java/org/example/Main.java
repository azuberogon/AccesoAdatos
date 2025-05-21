package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.model.*;
import org.example.service.*;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static  final Logger logger = LogManager.getLogger(Main.class.getName());
    private static Scanner sc = new Scanner(System.in);
    private static final ClienteService ClienteService = new ClienteService();
    private static final ComercialService ComercialService = new ComercialService();
    private static final PedidoService PedidoService = new PedidoService();
    private static final ProductoService ProductoService = new ProductoService();
    private static final ContieneService ContieneService = new ContieneService();
    public static void main(String[] args) {
        System.out.println("-----------------------------------------------");
        System.out.println("-----------------------------------------------");
        System.out.println("----- UT3 A3 - App Completa - Olga Alfaro -----");
        System.out.println("-----------------------------------------------");
        System.out.println("-----------------------------------------------");
        mostrarOpciones();
    }

    private static void mostrarOpciones(){
        String opcion = "0";

        while(!opcion.equals("5")){
            opcion = mostrarMenuInicio();
            switch(opcion){
                case "1":
                    mostrarOperacionesCliente();
                    break;
                case "2":
                    mostrarOperacionesComercial();
                    break;
                case "3":
                    mostrarOperacionesPedido();
                    break;
                case "4":
                    mostrarOperacionesProducto();
                    break;
                case "5":
                    System.out.println("Esperamos que la experiencia haya sido de su agrado. !Adiós!");
                    break;
                default:
                    System.out.println("Opción no valida. Prueba otra vez...");

            }
        }
    }

    private static void mostrarOperacionesCliente(){
        String opcion = "0";
        while(!opcion.equals("6")){
            opcion = mostrarMenuCliente();
            switch(opcion){
                case "1":
                    crearCliente();
                    break;
                case "2":
                    actualizarCliente();
                    break;
                case "3":
                    eliminarCliente();
                    break;
                case "4":
                    buscarCliente();
                    break;
                case "5":
                    mostrarTodosClientes();
                    break;
                case "6":
                    mostrarOpciones();
                    break;
                default:
                    System.out.println("Opción no valida. Prueba otra vez...");
            }
        }
    }

    private static void mostrarOperacionesComercial(){
        String opcion = "0";
        while(!opcion.equals("6")){
            opcion = mostrarMenuComercial();
            switch(opcion){
                case "1":
                    crearComercial();
                    break;
                case "2":
                    actualizarComercial();
                    break;
                case "3":
                    eliminarComercial();
                    break;
                case "4":
                    buscarComercial();
                    break;
                case "5":
                    mostrarTodosComerciales();
                    break;
                case "6":
                    mostrarOpciones();
                    break;
                default:
                    System.out.println("Opción no valida. Prueba otra vez...");
            }
        }
    }

    private static void mostrarOperacionesProducto(){
        String opcion = "0";
        while(!opcion.equals("6")){
            opcion = mostrarMenuProducto();
            switch(opcion){
                case "1":
                    crearProducto();
                    break;
                case "2":
                    actualizarProducto();
                    break;
                case "3":
                    eliminarProducto();
                    break;
                case "4":
                    buscarProducto();
                    break;
                case "5":
                    mostrarTodosProductos();
                    break;
                case "6":
                    mostrarOpciones();
                    break;
                default:
                    System.out.println("Opción no valida. Prueba otra vez...");
            }
        }
    }

    private static void mostrarOperacionesPedido(){
        String opcion = "0";
        while(!opcion.equals("5")){
            opcion = mostrarMenuPedido();
            switch(opcion){
                case "1":
                    crearPedido();
                    break;
                case "2":
                    actualizarPedido();
                    break;
                case "3":
                    eliminarPedido();
                    break;
                case "4":
                    mostrarTodosPedidos();
                    break;
                case "5":
                    mostrarOpciones();
                    break;
                default:
                    System.out.println("Opción no valida. Prueba otra vez...");
            }
        }
    }



    private static String mostrarMenuInicio() {
        System.out.println("""
                
                *************************************************************
                *************************************************************
                **************************   MENÚ   *************************
                *************************************************************
                *************************************************************
                Elige una de las siguientes opciones:
                \t1 - Realizar operaciones - Cliente
                \t2 - Realizar operaciones - Comercial
                \t3 - Realizar operaciones - Pedido
                \t4 - Realizar operaciones - Producto
                \t5 - Salir""");
        return sc.nextLine();
    }

    private static String mostrarMenuCliente() {
        System.out.println("""
                
                *************************************************************
                *************************************************************
                **************************   MENÚ   *************************
                *************************************************************
                *************************************************************
                Elige una de las siguientes opciones:
                \t1 - Crear Cliente
                \t2 - Actualizar Cliente
                \t3 - Eliminar Cliente
                \t4 - Buscar Cliente
                \t5 - Ver todos los Clientes
                \t6 - Volver""");
        return sc.nextLine();
    }

    private static String mostrarMenuComercial(){
        System.out.println("""
                
                *************************************************************
                *************************************************************
                **************************   MENÚ   *************************
                *************************************************************
                *************************************************************
                Elige una de las siguientes opciones:
                \t1 - Crear Comercial
                \t2 - Actualizar Comercial
                \t3 - Eliminar Comercial
                \t4 - Buscar Comercial
                \t5 - Ver todos los Comerciales
                \t6 - Volver""");
        return sc.nextLine();
    }

    private static String mostrarMenuProducto(){
        System.out.println("""
                
                *************************************************************
                *************************************************************
                **************************   MENÚ   *************************
                *************************************************************
                *************************************************************
                Elige una de las siguientes opciones:
                \t1 - Crear Producto
                \t2 - Actualizar Producto
                \t3 - Eliminar Producto
                \t4 - Buscar Producto
                \t5 - Ver todos los Productos
                \t6 - Volver""");
        return sc.nextLine();
    }

    private static String mostrarMenuPedido(){
        System.out.println("""
                
                *************************************************************
                *************************************************************
                **************************   MENÚ   *************************
                *************************************************************
                *************************************************************
                Elige una de las siguientes opciones:
                \t1 - Crear Pedido
                \t2 - Actualizar Pedido
                \t3 - Eliminar Pedido
                \t4 - Ver todos los Pedidos
                \t5 - Volver""");
        return sc.nextLine();
    }

    private static Cliente crearCliente(){
        Cliente cliente = new Cliente();
        System.out.println("Introduzca el nombre del nuevo cliente: ");
        String nombre = sc.nextLine();
        cliente.setNombre(nombre);
        System.out.println("Introduzca el primer apellido del nuevo cliente: ");
        String apellido1 = sc.nextLine();
        cliente.setApellido1(apellido1);
        System.out.println("¿Desea introducir un segundo apellido?(S/N)");
        String opcionSegApell = sc.nextLine();
        if(opcionSegApell.equals("S")){
            System.out.println("Introduzca el segundo apellido del nuevo cliente: ");
            String apellido2 = sc.nextLine();
            cliente.setApellido2(apellido2);
        }
        System.out.println("Introduzca la ciudad del nuevo cliente: ");
        String ciudad = sc.nextLine();
        cliente.setCiudad(ciudad);

        System.out.println("Introduzca la categoría del nuevo cliente: ");
        int categoria = Integer.valueOf(sc.nextLine());
        cliente.setCategoria(categoria);

        System.out.println("Cliente ANTES de create:\n" + cliente);
        ClienteService.create(cliente);
        System.out.println("Cliente DESPUÉS de create:\n" + cliente);

        return cliente;
    }

    private static void actualizarCliente(){
        System.out.println("Introduzca el ID del cliente que desea actualizar: ");
        Cliente cliente = ClienteService.findById(Integer.valueOf(sc.nextLine()));
        System.out.println("Introduzca la nueva categoria: ");
        int categoria = Integer.valueOf(sc.nextLine());

        cliente.setCategoria(categoria);
        System.out.println("Cliente ANTES de update:\n" + cliente + cliente.getPedidos());
        ClienteService.update(cliente);
        System.out.println("Cliente DESPUÉS de update:\n" + cliente + cliente.getPedidos());

    }

    private static void eliminarCliente(){
        System.out.println("Introduzca el ID del cliente que desea actualizar: ");
        Cliente cliente = ClienteService.findById(Integer.valueOf(sc.nextLine()));
        ClienteService.delete(cliente);
    }

    private static void buscarCliente(){
        System.out.println("Introduzca el nombre o apellido por el que desee buscar al cliente: ");
        String txt = sc.nextLine();
        List<Cliente> clientes = ClienteService.findByNameApellidos(txt);
        System.out.println("CLIENTES QUE COINCIDEN:");
        for (Cliente c : clientes){
            System.out.println(c.toString() + c.getPedidos().toString() + "\n");
        }
    }


    private static void mostrarTodosClientes(){
        List<Cliente> clientes = ClienteService.findAll();
        System.out.println("TODOS LOS CLIENTES:");
        for (Cliente c : clientes){
            System.out.println(c.toString() + c.getPedidos().toString() + "\n");
        }
    }


    private static Comercial crearComercial(){
        Comercial comercial = new Comercial();
        System.out.println("Introduzca el nombre del nuevo comercial: ");
        String nombre = sc.nextLine();
        comercial.setNombre(nombre);
        System.out.println("Introduzca el primer apellido del nuevo comercial: ");
        String apellido1 = sc.nextLine();
        comercial.setApellido1(apellido1);
        System.out.println("¿Desea introducir un segundo apellido?(S/N)");
        String opcionSegApell = sc.nextLine();
        if(opcionSegApell.equals("S")){
            System.out.println("Introduzca el segundo apellido del nuevo comercial: ");
            String apellido2 = sc.nextLine();
            comercial.setApellido2(apellido2);
        }
        System.out.println("Introduzca la comisión del nuevo comercial: ");
        float comision = Float.parseFloat(sc.nextLine());
        comercial.setComision(comision);

        System.out.println("Comercial ANTES de create:\n" + comercial);
        ComercialService.create(comercial);
        System.out.println("Comercial DESPUÉS de create:\n" + comercial);
        return comercial;
    }

    private static void actualizarComercial(){
        System.out.println("Introduzca el ID del comercial que desea actualizar: ");
        Comercial comercial = ComercialService.findById(Integer.valueOf(sc.nextLine()));
        System.out.println("Introduzca la nueva comision: ");
        float comision = Float.parseFloat(sc.nextLine());

        comercial.setComision(comision);
        System.out.println("Comercial ANTES de update:\n" + comercial + comercial.getPedidos());
        ComercialService.update(comercial);
        System.out.println("Comercial DESPUÉS de update:\n" + comercial + comercial.getPedidos());

    }

    private static void eliminarComercial(){
        System.out.println("Introduzca el ID del comercial que desea actualizar: ");
        Comercial comercial = ComercialService.findById(Integer.valueOf(sc.nextLine()));
        ComercialService.delete(comercial);
    }

    private static void buscarComercial(){
        System.out.println("Introduzca el nombre o apellido por el que desee buscar al comercial: ");
        String txt = sc.nextLine();
        List<Comercial> comerciales = ComercialService.findByNameApellidos(txt);
        System.out.println("COMERCIALES QUE COINCIDEN:");
        for (Comercial c : comerciales){
            System.out.println(c.toString() + c.getPedidos().toString() + "\n");
        }
    }

    private static void mostrarTodosComerciales(){
        List<Comercial> comerciales = ComercialService.findAll();
        System.out.println("TODOS LOS COMERCIALES:");
        for (Comercial c : comerciales){
            System.out.println(c.toString() + c.getPedidos().toString() + "\n");
        }
    }

    private static Producto crearProducto(){
        Producto producto = new Producto();
        System.out.println("Introduzca el nombre del nuevo producto: ");
        String nombre = sc.nextLine();
        producto.setNombre(nombre);
        System.out.println("Introduzca el precio del nuevo producto: ");
        double precio = Double.parseDouble(sc.nextLine());
        producto.setPrecio(precio);

        System.out.println("Producto ANTES de create:\n" + producto);
        ProductoService.create(producto);
        System.out.println("Producto DESPUÉS de create:\n" + producto);

        return producto;
    }

    private static void actualizarProducto(){
        System.out.println("Introduzca el ID del producto que desea actualizar: ");
        Producto producto = ProductoService.findById(Integer.valueOf(sc.nextLine()));
        System.out.println("Introduzca el nuevo precio: ");
        double precio = Double.parseDouble(sc.nextLine());

        producto.setPrecio(precio);
        System.out.println("Producto ANTES de update:\n" + producto + producto.getContenidoEn());
        ProductoService.update(producto);
        System.out.println("Producto DESPUÉS de update:\n" + producto + producto.getContenidoEn());

    }

    private static void eliminarProducto(){
        System.out.println("Introduzca el ID del producto que desea actualizar: ");
        Producto producto = ProductoService.findById(Integer.valueOf(sc.nextLine()));
        ProductoService.delete(producto);
    }

    private static void buscarProducto(){
        System.out.println("Introduzca el nombre por el que desee buscar al producto: ");
        String txt = sc.nextLine();
        List<Producto> productos = ProductoService.findByName(txt);
        System.out.println("PRODUCTOS QUE COINCIDEN:");
        for (Producto c : productos){
            System.out.println(c.toString() + c.getContenidoEn().toString() + "\n");
        }
    }

    private static void mostrarTodosProductos(){
        List<Producto> productos = ProductoService.findAll();
        System.out.println("TODOS LOS PRODUCTOS:");
        for (Producto c : productos){
            System.out.println(c.toString() + c.getContenidoEn().toString() + "\n");
        }
    }

    private static void crearPedido(){
        Pedido pedido = new Pedido();
        System.out.println("Introduzca el total del nuevo producto: ");
        double total = Double.parseDouble(sc.nextLine());
        pedido.setTotal(total);
        Date date = new Date(Calendar.getInstance().getTime().getYear(), Calendar.getInstance().getTime().getMonth(), Calendar.getInstance().getTime().getDay());
        pedido.setFecha(date);

        Cliente cliente = new Cliente();
        System.out.println("¿Desea crear un nuevo cliente para este pedido?(S/N)");
        if (sc.nextLine().equals("S")){
            cliente = crearCliente();
        }
        else{
            System.out.println("Introduzca el id del cliente: ");
            cliente = ClienteService.findById(Integer.valueOf(sc.nextLine()));
        }

        pedido.setCliente(cliente);

        Comercial comercial = new Comercial();
        System.out.println("¿Desea añadir un comercial a este pedido?(S/N)");
        if (sc.nextLine().equals("S")){
            System.out.println("¿Desea crear un nuevo comercial para este pedido?(S/N)");
            if (sc.nextLine().equals("S")){
                comercial = crearComercial();
            }
            else{
                System.out.println("Introduzca el id del comercial: ");
                comercial = ComercialService.findById(Integer.valueOf(sc.nextLine()));
            }
            pedido.setComercial(comercial);
        }

        PedidoService.create(pedido);

        Producto producto = new Producto();
        System.out.println("¿Desea crear un nuevo producto para este pedido?(S/N)");
        if (sc.nextLine().equals("S")){
            producto = crearProducto();
        }
        else{
            System.out.println("Introduzca el id del producto: ");
            producto = ProductoService.findById(Integer.valueOf(sc.nextLine()));
        }

        System.out.println("Introduzca la cantidad del producto que desea añadir al pedido: ");
        int cant = Integer.parseInt(sc.nextLine());

        Contiene contiene = new Contiene(pedido, producto, cant);

        pedido.getContiene().add(contiene);

        System.out.println("Pedido ANTES de crearlo con los productos:\n" + pedido);
        PedidoService.update(pedido);
        System.out.println("Producto DESPUÉS de crearlo con los productos:\n" + pedido);
    }

    private static void actualizarPedido(){
        System.out.println("Introduzca el ID del pedido que desea actualizar: ");
        Pedido pedido = PedidoService.findById(Integer.valueOf(sc.nextLine()));
        System.out.println("Introduzca el nuevo total: ");
        double total = Double.parseDouble(sc.nextLine());

        pedido.setTotal(total);
        System.out.println("Pedido ANTES de update:\n" + pedido + pedido.getContiene());
        PedidoService.update(pedido);
        System.out.println("Pedido DESPUÉS de update:\n" + pedido + pedido.getContiene());

    }

    private static void eliminarPedido(){
        System.out.println("Introduzca el ID del pedido que desea actualizar: ");
        Pedido pedido = PedidoService.findById(Integer.valueOf(sc.nextLine()));
        PedidoService.delete(pedido);
    }

    private static void mostrarTodosPedidos(){
        List<Pedido> pedidos = PedidoService.findAll();
        System.out.println("TODOS LOS PEDIDOS:");
        for (Pedido c : pedidos){
            System.out.println(c.toString() + c.getContiene().toString() + "\n");
        }
    }

}