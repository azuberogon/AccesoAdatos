package UT4_A4_CRUD.model.dao;

import UT4_A4_CRUD.model.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete
public interface IClienteDAO extends JpaRepository<Cliente, Long> {
}
