package AccesoDatos.ut4.spring_boot.Dao;


import AccesoDatos.ut4.spring_boot.Entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPersonaDao extends JpaRepository<Persona,String> {
}
