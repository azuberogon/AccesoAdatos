package AccesoDatos.ut4.spring_boot.Model.Services;

import AccesoDatos.ut4.spring_boot.Model.DAO.PersonasDAO;
import AccesoDatos.ut4.spring_boot.Model.Entity.Persona;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonasService {
    @Autowired
    private PersonasDAO personasDAO;

    public Integer crearPersonas(Persona persona){
        if (persona != null){
            personasDAO.save(persona);
            return 1;
        }
        return -1;
    }

    public List<Persona> findAll(){
        return personasDAO.findAll();
    }

    public Persona getByID(String nss){
        if (nss != null && !nss.isEmpty()){
            return personasDAO.getReferenceById(nss);
        }
        return null;
    }

    public Integer deletePorId(String nss){
        Persona persona = getByID(nss);
        if (persona != null ){
             personasDAO.delete(persona);
             return 1;
        }
        return -1;
    }

    public Integer delete(Persona persona){
        if (persona != null ){
            personasDAO.delete(persona);
            return 1;
        }
        return -1;
    }


}
