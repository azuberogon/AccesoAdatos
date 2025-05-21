package com.example.demo.mdoel.dao;

import com.example.demo.mdoel.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IPedidoRepository extends JpaRepository<Pedido, Long> {
}