package org.example.service;

import org.example.dao.ProductoDAO;
import org.example.model.Producto;

import java.util.List;

public class ProductoService extends GenericServiceImpl<Producto, Integer>{
    public ProductoService(){
        super((new ProductoDAO()));
    }

    public List<Producto> findByName(String txt){
        ProductoDAO productoDAO = (ProductoDAO) getDao();
        return productoDAO.findByName(txt);
    }
}
