package com.example.demo.dao;

import com.example.demo.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClinenteDAO extends JpaRepository<Cliente, Long > {
}
