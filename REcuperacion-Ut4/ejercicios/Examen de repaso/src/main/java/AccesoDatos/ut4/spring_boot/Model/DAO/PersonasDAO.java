package AccesoDatos.ut4.spring_boot.Model.DAO;

import AccesoDatos.ut4.spring_boot.Model.Entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonasDAO extends JpaRepository<Persona,String> {
}
