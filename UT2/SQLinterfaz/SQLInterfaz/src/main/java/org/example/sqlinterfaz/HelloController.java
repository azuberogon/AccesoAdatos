package org.example.sqlinterfaz;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.sqlinterfaz.dao.ProductoDao;
import org.example.sqlinterfaz.dto.Producto;

import java.util.List;

public class HelloController {

    @FXML
    private TableView<Producto> table;

    @FXML
    private TableColumn<Producto, String> colNombre;

    @FXML
    private TableColumn<Producto, Double> colPrecio;

    @FXML
    private TextField nombreInput;

    @FXML
    private TextField precioInput;

    @FXML
    private Label errorLabel;  // Etiqueta para mostrar errores

    private ObservableList<Producto> productosList;

    @FXML
    public void initialize() {
        // Vincular las columnas con los atributos del producto
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));

        // Cargar los productos en la tabla al iniciar
        actualizarTabla();
    }

    @FXML
    private void onAddProduct() {
        String nombre = nombreInput.getText();
        double precio;

        // Manejo de excepciones para el campo de precio
        try {
            precio = Double.parseDouble(precioInput.getText());
        } catch (NumberFormatException e) {
            errorLabel.setText("Error: El precio debe ser un número.");
            return; // Termina el método si hay un error
        }

        // Añadir producto a la base de datos
        ProductoDao.addProducto(nombre, precio);

        // Actualizar la tabla después de añadir el producto
        actualizarTabla();

        // Limpiar los campos de texto
        nombreInput.clear();
        precioInput.clear();
        errorLabel.setText("");  // Limpiar el mensaje de error
    }

    @FXML
    private void onDeleteProduct() {
        // Obtener el producto seleccionado de la tabla
        Producto productoSeleccionado = table.getSelectionModel().getSelectedItem();
        if (productoSeleccionado != null) {
            // Eliminar el producto de la base de datos
            ProductoDao.deleteProducto(productoSeleccionado.getId());

            // Actualizar la tabla después de eliminar el producto
            actualizarTabla();
        } else {
            errorLabel.setText("Error: Selecciona un producto para eliminar.");
        }
    }

    @FXML
    private void onRefreshTable() {
        // Actualizar la tabla manualmente
        actualizarTabla();
        errorLabel.setText("");  // Limpiar el mensaje de error
    }

    private void actualizarTabla() {
        // Obtener la lista de productos de la base de datos
        List<Producto> productos = ProductoDao.getAllProductos();
        System.out.println("Productos obtenidos de la base de datos: " + productos.size());

        // Depuración: imprimir los productos
        for (Producto producto : productos) {
            System.out.println(producto.toString());
        }

        // Convertir la lista de productos en un ObservableList para la tabla
        productosList = FXCollections.observableArrayList(productos);

        // Depuración: verificar el tamaño de la lista observable antes de cargarla en la tabla
        System.out.println("Elementos en productosList: " + productosList.size());

        // Cargar los productos en la tabla
        table.setItems(productosList);

        // Depuración: verificar el número de elementos en la tabla después de cargar los datos
        System.out.println("Elementos en la tabla: " + table.getItems().size());
    }

}
