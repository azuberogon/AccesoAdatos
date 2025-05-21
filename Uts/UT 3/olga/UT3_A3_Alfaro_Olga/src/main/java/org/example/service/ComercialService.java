package org.example.service;

import org.example.dao.ComercialDAO;
import org.example.model.Comercial;

import java.util.List;


public class ComercialService extends GenericServiceImpl<Comercial, Integer> {
    public ComercialService() {
        super(new ComercialDAO());
    }

    public List<Comercial> findByNameApellidos(String txt){
        ComercialDAO comercialDAO = (ComercialDAO) getDao();
        return comercialDAO.findByNameApellidos(txt);
    }
}
