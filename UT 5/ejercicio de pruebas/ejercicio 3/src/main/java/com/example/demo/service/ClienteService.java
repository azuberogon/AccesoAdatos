package com.example.demo.service;

import com.example.demo.dao.IClinenteDAO;
import com.example.demo.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {
    @Autowired
    private IClinenteDAO clinenteDAO;


    public Cliente getById(Long id) {
        if (id == null) return null;
        return clinenteDAO.getReferenceById(id);
    }

    public List<Cliente> getAll() {
        return clinenteDAO.findAll();
    }
    public void save(Cliente cliente) {
        clinenteDAO.save(cliente);
    }
}




