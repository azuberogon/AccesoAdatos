package es.marianasanz.acda.repaso.ut4.model.dao;

import es.marianasanz.acda.repaso.ut4.model.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

//Spring implementará automáticamente el DAO a partir de esto
public interface IPersonaDAO extends JpaRepository<Persona, String> {
}
