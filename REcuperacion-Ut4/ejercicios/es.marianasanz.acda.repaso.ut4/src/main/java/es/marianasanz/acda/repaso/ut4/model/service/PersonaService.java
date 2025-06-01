package es.marianasanz.acda.repaso.ut4.model.service;

import es.marianasanz.acda.repaso.ut4.model.dao.IPersonaDAO;
import es.marianasanz.acda.repaso.ut4.model.entity.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//DEBEMOS INDICAR QUE ES UN SERVICIO
@Service
public class PersonaService {
    //Creamos el DAO que usaremos para acceder a los metodos CRUD
    //Recuerda poner el @Autowired (inyección de dependencias) para que funcione
    @Autowired
    private IPersonaDAO iPersonaDAO;

    //Buscar por id
    public Persona getById(String id) {
        // Lógica de negocio
        if (id == null)
            return null;
        //Fijarse bien en el metodo DAO que usamos
        return iPersonaDAO.getReferenceById(id);
    }

    //Buscar todos
    public List<Persona> findAll() {
        return iPersonaDAO.findAll();
    }

    //Este metodo sirve tanto para insertar como para actualizar
    public Integer save(Persona persona) {
        // Lógica de negocio
        if (persona == null)
            return -1;
        iPersonaDAO.save(persona);
        return 0;
    }

    //Borrar por id
    public Integer deleteById(String id) {
        // Lógica de negocio
        if (id == null)
            return -1;
        iPersonaDAO.deleteById(id);
        return 0;
    }

    //También podríamos usar borrar por la clase persona
    public Integer delete(Persona persona) {
        if (persona == null)
            return -1;
        iPersonaDAO.delete(persona);
        return 0;
    }
}
