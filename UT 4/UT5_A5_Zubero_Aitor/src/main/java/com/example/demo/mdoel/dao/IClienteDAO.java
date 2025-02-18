package com.example.demo.mdoel.dao;


import com.example.demo.mdoel.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClienteDAO extends JpaRepository<Cliente, Long> {
}

