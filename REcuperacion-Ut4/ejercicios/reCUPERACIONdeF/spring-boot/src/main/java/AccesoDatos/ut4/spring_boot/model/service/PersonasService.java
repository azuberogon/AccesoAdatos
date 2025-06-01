package AccesoDatos.ut4.spring_boot.model.service;

import AccesoDatos.ut4.spring_boot.model.dao.PersonaDAO;
import AccesoDatos.ut4.spring_boot.model.entity.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonasService {
    @Autowired
    private PersonaDAO personaDAO;

    public Integer save(Persona persona){
        if (persona != null){
            personaDAO.save(persona);
            return 1;
        }
        return -1;
    }

    public Integer eliminarPorId(String nss){
        if (nss != null && !nss.isBlank()){
            personaDAO.deleteById(nss);
            return 1;
        }
        return -1;
    }

    public List<Persona> personasList(){
        return personaDAO.findAll();
    }
    public Persona getById(String id){
        if (id == null || id.isEmpty()  ){
            return personaDAO.getReferenceById(id);
        }
        return null;
    }

    public Integer borrarPorPersona(Persona persona){
       if (persona == null){
           return -1;
       }else{
           personaDAO.delete(persona);
            return 1;
       }
    }

//    public Integer editar(String id) {
//        Persona Persona = personaDAO.getReferenceById(id);
//
//        return -1;
//    }

}
