package com.example.demo.Service;

import com.example.demo.Persona.Persona;
import com.example.demo.dao.IPersonaDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonaService {
    @Autowired//como la clase dao conector
    private IPersonaDao iPersonaDao;
    public Persona getById(String idPersona){
        if(idPersona == null){
            return null;
        } else if (idPersona.isEmpty() || idPersona.isBlank()) {
            return null;
        }
        return iPersonaDao.getReferenceById(idPersona);

    }

    public List<Persona> listarPersona(){
        return iPersonaDao.findAll();
    }
    public Integer save(Persona persona){
        if(persona!=null){
            iPersonaDao.save(persona);
            return 1;
        }
        return -1;
    }
    public void deleteById(String idPersona){
        iPersonaDao.deleteById(idPersona);
    }

   public Integer delete(Persona persona){
       if(persona!=null){
           iPersonaDao.delete(persona);
           return 1;
       }
       return -1;
   }



}
//consultas de sql para posibles resoluciones de consultas de datos =
// 1 or * podriamos conseguir todos los datos de personas como su nif en una condicion where que este mal
// 1 or select * from logins asi se hace los atcks basicos sqlInjection