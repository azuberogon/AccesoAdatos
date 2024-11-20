package org.example.sqlinterfaz;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.sqlinterfaz.DataBase.Database;
import org.example.sqlinterfaz.dao.ProductoDao;
import org.example.sqlinterfaz.dto.Producto;

import java.io.IOException;
import java.util.List;

public class HelloApplication extends Application {

    private TableView<Producto> table;

    @Override
    public void start(Stage stage) throws IOException {
        // Crear la tabla si no existe
        Database.eliminarTablaProductos();
        Database.createTable();

        // Inicializar la tabla
        table = new TableView<>();

        // Crear columnas para la tabla
        TableColumn<Producto, String> colNombre = new TableColumn<>("Nombre");
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre")); // Vincular columna al campo nombre

        TableColumn<Producto, Double> colPrecio = new TableColumn<>("Precio");
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio")); // Vincular columna al campo precio

        TableColumn<Producto, Integer> colCantidad = new TableColumn<>("Cantidad");
        colCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad")); // Vincular columna al campo cantidad

        // Añadir columnas a la tabla
        table.getColumns().addAll(colNombre, colPrecio, colCantidad);

        // Cargar los datos en la tabla
        actualizarTabla();

        // Inputs para añadir productos
        TextField nombreInput = new TextField();
        nombreInput.setPromptText("Nombre del producto");

        TextField precioInput = new TextField();
        precioInput.setPromptText("Precio del producto");

        TextField cantidadInput = new TextField();
        cantidadInput.setPromptText("Cantidad del producto");

        // Botón para añadir productos
        Button addButton = new Button("Añadir Producto");
        addButton.setOnAction(e -> {
            ProductoDao.addProducto(
                    nombreInput.getText(),
                    Double.parseDouble(precioInput.getText()),
                    Integer.parseInt(cantidadInput.getText())
            );
            actualizarTabla();
            nombreInput.clear();
            precioInput.clear();
            cantidadInput.clear();
        });

        // Botón para eliminar productos
        Button deleteButton = new Button("Eliminar Producto");
        deleteButton.setOnAction(e -> {
            Producto productoSeleccionado = table.getSelectionModel().getSelectedItem();
            if (productoSeleccionado != null) {
                ProductoDao.deleteProducto(productoSeleccionado.getId());
                actualizarTabla();
            }
        });

        // Disposición en VBox
        VBox vbox = new VBox();
        vbox.getChildren().addAll(table, nombreInput, precioInput, cantidadInput, addButton, deleteButton);

        // Crear escena y mostrar ventana
        Scene scene = new Scene(vbox, 600, 400);
        stage.setScene(scene);
        stage.show();

        // Imprimir los productos de la base de datos en la consola
        List<Producto> productos = ProductoDao.getAllProductos();
        for (Producto producto : productos) {
            System.out.println(producto.toString());
        }
    }

    // Método para actualizar la tabla con los datos de la base de datos
    private void actualizarTabla() {
        List<Producto> productos = ProductoDao.getAllProductos();
        System.out.println(productos.toString());
        table.setItems(FXCollections.observableArrayList(productos)); // Convertir la lista en ObservableList y cargarla en la tabla
    }

    public static void main(String[] args) {
        launch(args);
    }
}
