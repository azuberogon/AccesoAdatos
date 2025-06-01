package AccesoDatos.ut4.spring_boot.model.dao;

import AccesoDatos.ut4.spring_boot.model.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaDAO extends JpaRepository<Persona,String> {
}
