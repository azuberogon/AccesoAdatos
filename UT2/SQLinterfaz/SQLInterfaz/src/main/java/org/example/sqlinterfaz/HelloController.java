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
    private TableColumn<Producto, Integer> colCantidad; // Añadido correctamente con @FXML

    @FXML
    private TextField nombreInput;

    @FXML
    private TextField precioInput;

    @FXML
    private TextField cantidadInput; // Campo para la entrada de cantidad

    @FXML
    private TextField filtroPrecioInput;

    @FXML
    private Label errorLabel;  // Etiqueta para mostrar errores

    private ObservableList<Producto> productosList;

    @FXML
    public void initialize() {
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        colCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));  // Nueva columna cantidad
        actualizarTabla();
    }

    @FXML
    private void onAddProduct() {
        String nombre = nombreInput.getText();
        double precio;
        int cantidad;

        // Validar el campo de precio
        try {
            precio = Double.parseDouble(precioInput.getText());
        } catch (NumberFormatException e) {
            errorLabel.setText("Error: El precio debe ser un número.");
            return;
        }

        // Validar el campo de cantidad
        try {
            cantidad = Integer.parseInt(cantidadInput.getText());
        } catch (NumberFormatException e) {
            errorLabel.setText("Error: La cantidad debe ser un número entero.");
            return;
        }

        // Añadir producto a la base de datos
        ProductoDao.addProducto(nombre, precio, cantidad);

        // Actualizar la tabla después de añadir el producto
        actualizarTabla();

        // Limpiar los campos de texto
        nombreInput.clear();
        precioInput.clear();
        cantidadInput.clear();
        errorLabel.setText("");  // Limpiar el mensaje de error
    }

    @FXML
    private void onDeleteProduct() {
        Producto productoSeleccionado = table.getSelectionModel().getSelectedItem();
        if (productoSeleccionado != null) {
            ProductoDao.deleteProducto(productoSeleccionado.getId());
            actualizarTabla();
        } else {
            errorLabel.setText("Error: Selecciona un producto para eliminar.");
        }
    }

    @FXML
    private void onRefreshTable() {
        actualizarTabla();
        errorLabel.setText("");  // Limpiar el mensaje de error
    }

    private void actualizarTabla() {
        List<Producto> productos = ProductoDao.getAllProductos();
        productosList = FXCollections.observableArrayList(productos);
        table.setItems(productosList);
    }

    @FXML
    private void onFilterByPrecio() {
        double precioMax;
        try {
            precioMax = Double.parseDouble(filtroPrecioInput.getText());
        } catch (NumberFormatException e) {
            errorLabel.setText("Error: El precio máximo debe ser un número.");
            return;
        }

        List<Producto> productosFiltrados = ProductoDao.getAllProductos().stream()
                .filter(producto -> producto.getPrecio() < precioMax)
                .toList();

        productosList = FXCollections.observableArrayList(productosFiltrados);
        table.setItems(productosList);
        errorLabel.setText("");  // Limpiar el mensaje de error
    }
}
