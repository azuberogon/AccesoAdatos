package UT4_A4_CRUD.service;

import UT4_A4_CRUD.dao.IClienteDAO;
import UT4_A4_CRUD.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired // para que se autogenere
    private IClienteDAO clienteDAO;

    public Cliente getById(Long id){
        if (id == null) return null;
        return clienteDAO.getReferenceById(id);
    }

    public List<Cliente> FindAll(){
        return clienteDAO.findAll();

    }
    public Integer save(Cliente cliente){
        clienteDAO.save(cliente);
        return 0;

    }
    public void deleteById(Long id){
        clienteDAO.deleteById(id);
    }

}
