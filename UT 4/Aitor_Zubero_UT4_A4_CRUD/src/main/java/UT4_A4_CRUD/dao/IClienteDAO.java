package UT4_A4_CRUD.dao;


import UT4_A4_CRUD.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClienteDAO extends JpaRepository<Cliente, Long> {


}
