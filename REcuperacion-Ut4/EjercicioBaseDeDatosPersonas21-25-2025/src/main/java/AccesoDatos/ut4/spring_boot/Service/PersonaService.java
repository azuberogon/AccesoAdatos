package AccesoDatos.ut4.spring_boot.Service;

import AccesoDatos.ut4.spring_boot.Dao.IPersonaDao;
import AccesoDatos.ut4.spring_boot.Entity.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonaService {
    @Autowired
    private IPersonaDao personaDao;

    public Persona getById(String id){
        if (id != null){
            return personaDao.getReferenceById(id);
        }
        System.out.println("El id es nullo ");
        return null;
    }

    public List<Persona> obetenerTodasLasPersonas(){
        return personaDao.findAll();
    }

    public void guardar(Persona persona){
        personaDao.save(persona);
    }


    public void EliminarConPersona(Persona persona){
        personaDao.delete(persona);
    }

    public void EliminarPersonaId(String id){
        personaDao.deleteById(id);
    }






}
