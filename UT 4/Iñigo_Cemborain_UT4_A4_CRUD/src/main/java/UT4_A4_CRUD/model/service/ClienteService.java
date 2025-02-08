package UT4_A4_CRUD.model.service;

import UT4_A4_CRUD.model.dao.IClienteDAO;
import UT4_A4_CRUD.model.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private IClienteDAO iClienteDAO;

    public Cliente getById(Long id)
    {
        if (id == null)
            return null;
        return iClienteDAO.getReferenceById(id);
    }
    public List<Cliente> findAll()
    {
        return iClienteDAO.findAll();
    }
    public Integer save(Cliente cliente)
    {
        iClienteDAO.save(cliente);
        return 0;
    }
    public void deleteById(Long id)
    {
        iClienteDAO.deleteById(id);
    }
}
